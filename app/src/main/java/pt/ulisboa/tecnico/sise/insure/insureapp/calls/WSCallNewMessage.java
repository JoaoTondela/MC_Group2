package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.AdapterActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.ListMessagesActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class WSCallNewMessage extends AsyncTask <String, Void, List<ClaimMessage>> {
    private static final String TAG = "nNewMessage";
    private Context _context;
    private Integer _claimId;
    private ListView _listView;

    public WSCallNewMessage (Context context, Integer claimId, ListView listView) {
        _claimId = claimId;
        _context = context;
        _listView = listView;
    }

    protected List<ClaimMessage> doInBackground( String... params ) {

        try {
        int claimId = 1;
        String message = "Test Message from mobile app";
        boolean r = WSHelper.submitNewMessage(GlobalState.getSessionId(), claimId, params[0]);
            if (r) {
                List<ClaimMessage> claimMessagesList = WSHelper.listClaimMessages(GlobalState.getSessionId(),claimId );
                return claimMessagesList;
            }
        Log.d(TAG, "Submit New Message claimId " + claimId + " message:" + message + " result => " + r);

    } catch (Exception e) {
        Log.d(TAG, e.toString());
    } return null;
    }

    public void onPostExecute (List<ClaimMessage> r){
        AdapterActivity adapterActivity = new AdapterActivity( _context, 1, r);
        _listView.setAdapter(adapterActivity);
    }



}
