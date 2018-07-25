package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class Login extends AsyncTask <String, Void, Integer> {

    private static final String TAG = "Login";
    Context _context;

    public Login(Context context){
        _context = context;
    }

    protected Integer doInBackground(String... params) {
        //publishProgress("Testing method call login right...");
        Log.d("test", "test");
        try {
           // String username = params[0];
            //String password = params[1];
            //Log.d(TAG, username);
            //Log.d(TAG, password);
            int sessionId = WSHelper.login("j", "j");        // exists and password correct
            Log.d(TAG, "Login result => " + sessionId);
            return sessionId;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            //publishProgress("failed.\n");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        //startActivty(new Intent());

    }
}
