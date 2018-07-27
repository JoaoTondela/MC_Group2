package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class WSCallMessages extends AsyncTask<Integer, Void, List<ClaimMessage>> {
    private static final String TAG = "ListClaims";
    Context _context;
    RecyclerView _recyclerView;
    int _claimId;

    public WSCallMessages(Context context, RecyclerView recyclerView, int claimId) {
        _context = context;
        _recyclerView = recyclerView;
        _claimId = claimId;
    }

    protected List<ClaimMessage> doInBackground(Integer... params) {
        try {
            List<ClaimMessage> claimMessagesList = WSHelper.listClaimMessages(params[0], _claimId);
            if (claimMessagesList != null) {
                String m = claimMessagesList.size() > 0 ? "" : "empty array";
                for (ClaimMessage cm : claimMessagesList ) {

                    m += " (Sender:" + cm.getSender() +
                            ", Date: " + cm.getDate() +
                            ", Message: " + cm.getMessage() + ")";
                }
                Log.d(TAG, "List claim messages for claimId " + _claimId + " result =>" + m);
            } else {
                Log.d(TAG, "List claim messages for claimId " + _claimId + " result => null.");
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

}
