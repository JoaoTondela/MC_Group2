package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimRecord;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class WSCallClaimDetails extends AsyncTask<Integer, Void, ClaimRecord> {
    private static final String TAG = "ClaimDetails";
    Context _context;
    ListView _listView;
    int _claimId;
    GlobalState _globalState;
    ClaimRecord claimRecord;
    Customer customer = GlobalState.getCustomer();

    public WSCallClaimDetails(Context context, GlobalState globalState, ListView listView, int claimId) {
        _context = context;
        _listView = listView;
        _claimId = claimId;
        _globalState = globalState;
    }

    protected ClaimRecord doInBackground(Integer... params) {
        try {
            claimRecord = WSHelper.getClaimInfo(params[0], _claimId);
            customer.addClaim(claimRecord);
            return claimRecord;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            try {
                Log.d(TAG, "esta offline por isso entrou aqui");
                customer = _globalState.readCustomerFile();
                claimRecord = customer.getClaimRecordById(_claimId);
                return claimRecord;
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
            }
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

        _globalState.writeCustomerFile(customer);
    }
}
