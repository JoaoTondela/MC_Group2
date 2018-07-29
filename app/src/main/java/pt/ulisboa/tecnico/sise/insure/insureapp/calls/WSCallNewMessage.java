package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.AdapterActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.ClaimDetailsActivity;
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
        boolean r = WSHelper.submitNewMessage(GlobalState.getCustomer().getSessionId(), _claimId, params[0]);
            if (r) {
                List<ClaimMessage> claimMessagesList = WSHelper.listClaimMessages(GlobalState.getCustomer().getSessionId(),_claimId);
                return claimMessagesList;
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        } return null;
    }

    public void onPostExecute (List<ClaimMessage> r){
        try {
            final AdapterActivity adapterActivity = new AdapterActivity( _context, 1, r);
            _listView.setAdapter(adapterActivity);
        } catch (Exception e) {
            Toast.makeText(_context, "Message not sent: you are offline!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, MainActivity.class);
            intent.putExtra("claimId", _claimId);
            _context.startActivity(intent);
            Log.d(TAG, e.toString());
        }
    }



}
