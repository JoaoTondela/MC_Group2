package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.os.AsyncTask;
import android.util.Log;

public class CustomerInfo extends AsyncTask <Void, String, Void {

    protected Void doInBackground(Void... params) {

        publishProgress("Testing method call getCustomerInfo...");
        try {
            Customer customer = WSHelper.getCustomerInfo(sessionId);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
    }
}
