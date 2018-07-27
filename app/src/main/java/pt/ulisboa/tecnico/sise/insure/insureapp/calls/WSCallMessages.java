package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class WSCallMessages extends AsyncTask<Integer, Void, List<ClaimMessage>> {
    private static final String TAG = "ListClaims";
    Context _context;
    ScrollView _scrollview;
    LinearLayout _linearLayout;
    int _claimId;

    public WSCallMessages(Context context, ScrollView scrollView, LinearLayout linearLayout, int claimId) {
        _context = context;
        _scrollview = scrollView;
        _linearLayout = linearLayout;
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
                return claimMessagesList;
            } else {
                Log.d(TAG, "List claim messages for claimId " + _claimId + " result => null.");
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    protected void onPostExecute(List<ClaimMessage> claimMessagesList) {
        super.onPostExecute(claimMessagesList);
        _linearLayout.removeAllViews();
        for(ClaimMessage claimMessage: claimMessagesList){
            TextView msg = new TextView(_context);
            msg.setText(claimMessage.getMessage());

            _linearLayout.addView(msg);
        }
        /*
        String[] messages = allMessages.toArray(new String[allMessages.size()]);
        ArrayAdapter<String> adapterMessages = new ArrayAdapter<String>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, messages);
        _scrollview.setAdapter(adapterMessages);*/
    }

}
