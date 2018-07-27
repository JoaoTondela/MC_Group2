package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.activities.LoginActivity;

public class WSCallLogOut extends AsyncTask <Integer, Void, Boolean> {
    private static final String TAG = "ListClaims";
    Context _context;

    public WSCallLogOut( Context context ) {
        _context = context;
    }

    protected Boolean doInBackground( Integer... params ) {
        try {
            boolean result = WSHelper.logout(params[0]);
            Log.d(TAG, "Logout result => " + result);
            return result;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    public void onPostExecute( Boolean result ) {
        if (result) {
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }

    }
}
