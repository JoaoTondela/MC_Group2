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
    public GlobalState globalState = new GlobalState();


    public Login(Context context){
        _context = context;
    }

    protected Integer doInBackground(String... params) {
        //publishProgress("Testing method call login right...");
        Log.d("test", "test");
        try {
            String username = params[0];
            String password = params[1];
            Log.d(TAG, username);
            Log.d(TAG, password);
            int sessionId = WSHelper.login(username, password);        // exists and password correct
            Log.d(TAG, "Login result => " + sessionId);
            globalState.setSessionId(sessionId);
            //globalState.setUserName(Customer.class.getName());
            return sessionId;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            //publishProgress("failed.\n");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer sessionId) {
        Customer customer = null;
        if(sessionId == 0){
            Toast.makeText(_context, "Invalid arguments", Toast.LENGTH_SHORT).show();
        }else if (sessionId > 0){
            //try {
            //    OutputStream outputStream;
            //  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            //  ObjectOutputStream out = new ObjectOutputStream(outputStream);
            //  out.writeObject(customer);
            //} catch (Exception e) {
            //  e.printStackTrace();
            //}
            _context.startActivity(new Intent(_context,MainActivity.class));
        }


    }
}
