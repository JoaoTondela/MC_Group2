package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class Login extends AsyncTask <String, Void, Integer> {

    private static final String TAG = "Login";
    Context _context;
    String username;
    String password;
    Customer customer;
    private GlobalState _globalState;


    public Login(Context context, GlobalState globalState){
        _context = context;
        _globalState = globalState;
    }

    protected Integer doInBackground(String... params) {
        //publishProgress("Testing method call login right...");
        Log.d("test", "test");
        try {
            username = params[0];
            password = params[1];
            Log.d(TAG, username);
            Log.d(TAG, password);
            int sessionId = WSHelper.login(username, password);        // exists and password correct
            customer = WSHelper.getCustomerInfo(sessionId);
            customer.setSessionId(sessionId);
            Log.d(TAG, "Login result => " + sessionId);
            _globalState.setSessionId(sessionId);
            return sessionId;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            try {
                Log.d(TAG, "esta offline por isso entrou aqui");
                customer = _globalState.readCustomerFile();
                Log.d(TAG, "fez readClaimsListFile");
                return customer.getSessionId();
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer sessionId) {
        if(sessionId == 0){
            Toast.makeText(_context, "Invalid arguments", Toast.LENGTH_SHORT).show();
        }else if (sessionId > 0){
            try {
                _globalState.setCustomer(customer);
                _globalState.writeCustomerFile(customer);
                Log.d(TAG, "fez o write file");
            } catch (Exception e) {
                Log.d(TAG, "nao fez o write");
                Log.d(TAG, e.toString());
            }
            _context.startActivity(new Intent(_context,MainActivity.class));

        }
    }
}
