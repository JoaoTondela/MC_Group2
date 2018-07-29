package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import pt.ulisboa.tecnico.sise.insure.insureapp.activities.MainActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.activities.NewClaimActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimWrapper;

public class WSCallSubmitNewClaim extends AsyncTask <ClaimWrapper, Void, Boolean> {
    private static final String TAG = "SubmitClaim";
    Context _context;

    public WSCallSubmitNewClaim (Context context) {_context= context;}


    @Override
    protected Boolean doInBackground( ClaimWrapper... params) {

        try {
            int sessionId = params[0]._id;
            String title = params[0]._title;
            String occurrenceDate = params[0]._occurrenceDate;
            String plate = params[0]._plate;
            String description = params[0]._description;

            boolean r = WSHelper.submitNewClaim( sessionId,title,occurrenceDate,plate,description );
            return r;
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }


    public void onPostExecute(  Boolean bolean) {
        try {
            if (bolean) {
                Toast.makeText(_context, "Claim submited!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(_context, MainActivity.class);
                _context.startActivity(intent);
            }
        } catch (Exception ee) {
            Toast.makeText(_context, "Unable to submit claims offline", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
            Log.d(TAG, ee.toString());
            }
        }
}
