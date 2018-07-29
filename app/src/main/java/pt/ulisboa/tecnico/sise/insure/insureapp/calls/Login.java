package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.ListMessagesActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.LoginActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimRecord;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class Login extends AsyncTask <String, Void, Integer> {

    private static final String TAG = "Login";
    Context _context;
    String username;
    String password;
    Customer customer;
    ArrayList<String> credentials = new ArrayList<>();
    int sessionId;
    private GlobalState _globalState;


    public Login(Context context, GlobalState globalState){
        _context = context;
        _globalState = globalState;
    }

    protected Integer doInBackground(String... params) {
        //publishProgress("Testing method call login right...");
        username = params[0];
        password = params[1];
        try {
            sessionId = WSHelper.login(username, password);
            if (sessionId > 0) {
                credentials.add(username);
                credentials.add(password);
                _globalState.writeCredentialsFile(credentials);

                customer = WSHelper.getCustomerInfo(sessionId);
                customer.setSessionId(sessionId);
                GlobalState.inSureMsgPerClaim = getNumberAutoInSureMessages(sessionId);
                _globalState.setCustomer(customer);
                _globalState.writeCustomerFile(customer);
            }
            return sessionId;
        } catch (Exception e) {
            Log.d(TAG, "You are offline");
            Log.d(TAG, e.toString());
            try {
                credentials = _globalState.readCredentialsFile();
                Log.d(TAG, credentials.toString());
                Log.d("user é",credentials.get(0));
                Log.d("pass é",credentials.get(0));
                Log.d("username", username);
                Log.d("pass", password);
                if (username.equals(credentials.get(0)) && password.equals(credentials.get(1))) {
                    Log.d("username", username);
                    Log.d("pass", password);
                    customer = _globalState.readCustomerFile();
                    Log.d("CUSTOMER", String.valueOf(customer.getSessionId()));
                    return customer.getSessionId();
                }
                Log.d("RETORNO ====","OOOOOOO");
                return 0;
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
                return 0;
            }
        }
        /*
        if (sessionId > 0) {
                customer = WSHelper.getCustomerInfo(sessionId);
                customer.setSessionId(sessionId);
                GlobalState.inSureMsgPerClaim = getNumberAutoInSureMessages(sessionId);
                _globalState.setCustomer(customer);
                _globalState.writeCustomerFile(customer);
                Log.d(TAG,customer.getClaimRecordList().toString());
            }
            return sessionId;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            try {
                customer = _globalState.readCustomerFile();
                return customer.getSessionId();
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
            }
        }*/

    }

    //to get the number of messages sent by InSure for each claim
    private HashMap getNumberAutoInSureMessages(int sessionId) throws Exception {
        HashMap inSureMsgPerClaim = new HashMap();
        List<ClaimItem> claimItems = WSHelper.listClaims(sessionId);
        for (ClaimItem clm : claimItems) {
            List<ClaimMessage> msgs = WSHelper.listClaimMessages(sessionId, clm.getId());
            int autoInSureMsg = 0;
            for (ClaimMessage msg : msgs) {
                if (msg.getSender().equals("AutoInSure")) {
                    autoInSureMsg++;
                }
            }
            inSureMsgPerClaim.put(clm.getId(), autoInSureMsg);
            //Log.d(String.valueOf(clm.getId()), String.valueOf(autoInSureMsg));
        }
        return inSureMsgPerClaim;
    }

    @Override
    protected void onPostExecute(Integer sessionId) {
        if (sessionId == 0){
            Toast.makeText(_context, "Unsuccessful login", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, LoginActivity.class);
            _context.startActivity(intent);
        }else if (sessionId > 0){
            _context.startActivity(new Intent(_context,MainActivity.class));

        }
    }
}
