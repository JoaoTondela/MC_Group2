package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimRecord;

public class WSCallClaimDetails extends AsyncTask<Integer, Void, ClaimRecord> {
    private static final String TAG = "ClaimDetails";
    Context _context;
    ListView _listView;

    public WSCallClaimDetails(Context context, ListView listView) {
        _context = context;
        _listView = listView;
    }

    //protected ClaimRecord doInBackground(Integer sessionId, Integer claimId) {

    protected ClaimRecord doInBackground(Integer... params) {
        try {
            ClaimRecord claimRecord = WSHelper.getClaimInfo(params[0], params[1]);
            Log.d(TAG, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Log.d(TAG, claimRecord.toString());
            return claimRecord;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    protected void onPostExecute(ClaimRecord claimRecord) {
        super.onPostExecute(claimRecord);
        String claimId = String.valueOf(claimRecord.getId());
        String claimTitle = claimRecord.getTitle();
        String plate = claimRecord.getPlate();
        String submissionDate = claimRecord.getSubmissionDate();
        String ocurrenceDate = claimRecord.getOccurrenceDate();
        String description = claimRecord.getDescription();
        String status = claimRecord.getStatus();

        ArrayList<String> claimDetails = new ArrayList<>();

        claimDetails.add(claimId);
        claimDetails.add(claimTitle);
        claimDetails.add(plate);
        claimDetails.add(submissionDate);
        claimDetails.add(ocurrenceDate);
        claimDetails.add(description);
        claimDetails.add(status);

        String[] itemsInfo = claimDetails.toArray(new String[claimDetails.size()]);
        ArrayAdapter<String> adapterInfo = new ArrayAdapter<>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, itemsInfo);
        _listView.setAdapter(adapterInfo);

        /*
        ArrayList<String> customerInfo = new ArrayList<>();

        customerInfo.add(name.toString());
        customerInfo.add(NIF.toString());
        customerInfo.add(address.toString());
        customerInfo.add(birthDate.toString());
        customerInfo.add(policyNumber.toString());

        String[] itemsInfo = customerInfo.toArray(new String[customerInfo.size()]);
        ArrayAdapter<String> adapterInfo = new ArrayAdapter<String>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, itemsInfo);
        _listView.setAdapter(adapterInfo);
        */

        /*
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
        */
    }
}
