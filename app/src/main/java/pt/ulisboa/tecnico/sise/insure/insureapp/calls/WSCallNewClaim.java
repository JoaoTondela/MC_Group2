package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.activities.AdapterActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.NewClaimActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class WSCallNewClaim extends AsyncTask<Integer, Void, List<String>> {
    private static final String TAG = "ListPlates";
    Context _context;
    Spinner _spinner;


    public WSCallNewClaim( Context context, Spinner spinner ) { _context = context; _spinner = spinner; }

    protected List<String> doInBackground( Integer... params) {

        try {
            List <String> plateList = WSHelper.listPlates(params[0]);
            if (plateList != null) {
                String m = plateList.size() > 0 ? "" : "empty array";
                for (String plate : plateList) {
                    m += " (" + plate + ")";
                }
                return plateList;
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }return null;
    }

    public void onPostExecute(List<String> plateList ) {
        try {
            ArrayAdapter<String > adapter = new ArrayAdapter <>(_context, android.R.layout.select_dialog_item, plateList);
            _spinner.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(_context, "Unable to submit claims offline", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
            Log.d(TAG, e.toString());
        }
    }
}
