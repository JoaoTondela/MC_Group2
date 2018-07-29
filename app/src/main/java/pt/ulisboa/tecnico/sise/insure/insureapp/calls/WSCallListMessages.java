package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.AdapterActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class WSCallListMessages extends AsyncTask <Integer, String, List<ClaimMessage>>{
    private final static String TAG = "ClaimMessages";
    public Context _context;
    public int _claimId;
    public ListView _listView;
    public GlobalState _globalState;
    List<ClaimMessage> claimMessageList;

    public WSCallListMessages( Context context, GlobalState globalState, int claimId, ListView listView ) {
        _context = context;
        _claimId = claimId;
        _listView = listView;
        _globalState = globalState;
    }

    @Override
    protected List <ClaimMessage> doInBackground( Integer... integers ) {
        try {
            Customer customer = GlobalState.getCustomer();
            claimMessageList = WSHelper.listClaimMessages(customer.getSessionId(), _claimId);
            if (claimMessageList != null) {
                String m = claimMessageList.size() > 0 ? "" : "empty array";
                for (ClaimMessage claimMessage : claimMessageList) {
                    m += " (" + claimMessage.toString() + ")";
                }
            }
            customer.getClaimRecordById(_claimId).setClaimMessageList(claimMessageList);
            _globalState.writeCustomerFile(customer);
            return claimMessageList;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            try {
                Customer customer = _globalState.readCustomerFile();
                claimMessageList = customer.getClaimRecordById(_claimId).getClaimMessageList();
                return claimMessageList;
            } catch (Exception ee) {
                Log.d(TAG, ee.toString());
            }

        }
        return null;
    }

    protected void onPostExecute ( List<ClaimMessage> claimMessageList ){
        try {
            AdapterActivity adapterTitle = new AdapterActivity(_context,1,claimMessageList);
            _listView.setAdapter(adapterTitle);
        } catch (Exception e) {
            Toast.makeText(_context, "Messages for this claim not loaded for offline mode!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
            Log.d(TAG, e.toString());
        }
    }

    }

