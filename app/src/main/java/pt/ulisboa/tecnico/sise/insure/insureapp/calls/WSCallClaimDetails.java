package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimRecord;

public class WSCallClaimDetails extends AsyncTask<Integer, Void, ClaimRecord> {
    private static final String TAG = "ClaimDetails";
    Context _context;
    ListView _listView;
    int _claimId;

    public WSCallClaimDetails(Context context, ListView listView, int claimId) {
        _context = context;
        _listView = listView;
        _claimId = claimId;
    }

    protected ClaimRecord doInBackground(Integer... params) {
        try {
            ClaimRecord claimRecord = WSHelper.getClaimInfo(params[0], _claimId);
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
    }
}
