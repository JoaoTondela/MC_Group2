package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;

public class WSCallListClaims extends AsyncTask <Integer, Void, List<ClaimItem>> {
    private static final String TAG = "ListClaims";
    Context _context;
    ListView _listViewId;
    ListView _listViewTitle;
    List claimsList;
    GlobalState _globalState;

    public WSCallListClaims(Context context, GlobalState globalState, ListView listViewId, ListView listViewTitle) {
        _context = context;
        _listViewId = listViewId;
        _listViewTitle = listViewTitle;
        _globalState = globalState;
    }

    protected List<ClaimItem> doInBackground(Integer... params) {
        try {
            claimsList = WSHelper.listClaims(params[0]);
            _globalState.writeListClaimsFile(claimsList);
            Log.d(TAG, claimsList.toString());
            return claimsList;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            try {
                claimsList = _globalState.readListClaimsFile();
                return claimsList;
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
            }
        }
        return null;
    }

    protected void onPostExecute(List<ClaimItem> claimsList) {
        super.onPostExecute(claimsList);
        ArrayList<String> claimid = new ArrayList<String>();
        ArrayList<String> claimtitle = new ArrayList<String>();

        for (ClaimItem claimItem: claimsList) {
            claimid.add(String.valueOf(claimItem.getId()));
            claimtitle.add(claimItem.getTitle());
        }

        String[] itemsID = claimid.toArray(new String[claimid.size()]);
        ArrayAdapter<String> adapterID = new ArrayAdapter<String>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, itemsID);
        _listViewId.setAdapter(adapterID);

        String[] itemsTitle = claimtitle.toArray(new String[claimtitle.size()]);
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<String>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, itemsTitle);
        _listViewTitle.setAdapter(adapterTitle);

    }
}
