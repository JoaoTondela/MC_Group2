package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.ListMessagesActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;

public class WSCallNewMessage extends AsyncTask <String, Void, Boolean> {
    private static final String TAG = "nNewMessage";
    private Context _context;
    private Integer _claimId;

    public WSCallNewMessage (Context context, Integer claimId) {
        _claimId = claimId;
        _context = context;
    }

    protected Boolean doInBackground( String... params ) {

        try {
        int claimId = 1;
        String message = "Test Message from mobile app";
        boolean r = WSHelper.submitNewMessage(GlobalState.getSessionId(), claimId, params[0]);
        Log.d(TAG, "Submit New Message claimId " + claimId + " message:" + message + " result => " + r);
        return r;
    } catch (Exception e) {
        Log.d(TAG, e.toString());
    } return null;
    }

    public void onPostExecute (Boolean r){
        if (r){
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
        }
    }



}
