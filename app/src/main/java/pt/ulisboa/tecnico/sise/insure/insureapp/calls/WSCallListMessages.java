package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.AdapterActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class WSCallListMessages extends AsyncTask <Integer, String, List<ClaimMessage>>{
    private final static String TAG = "ClaimMessages";
    public Context _context;
    public int _claimId;
    public ListView _listView;

    public WSCallListMessages( Context context, int claimId, ListView listView ) {
        _context = context;
        _claimId = claimId;
        _listView = listView;
        Log.d("claim", String.valueOf(claimId));
    }

    @Override
    protected List <ClaimMessage> doInBackground( Integer... integers ) {
        try {
            List<ClaimMessage> claimMessageList = WSHelper.listClaimMessages(GlobalState.getSessionId(), _claimId);
            if (claimMessageList != null) {
                String m = claimMessageList.size() > 0 ? "" : "empty array";
                for (ClaimMessage claimMessage : claimMessageList) {
                    m += " (" + claimMessage.toString() + ")";
                }
                Log.d(TAG, "List claim message result => " + m);
                return claimMessageList;
            } else {
                Log.d(TAG, "List claim message result => null.");
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    protected void onPostExecute ( List<ClaimMessage> claimMessageList ){
        AdapterActivity adapterTitle = new AdapterActivity(_context,1,claimMessageList);
        _listView.setAdapter(adapterTitle);
    }

    }

