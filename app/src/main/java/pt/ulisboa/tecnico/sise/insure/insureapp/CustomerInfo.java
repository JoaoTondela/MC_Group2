package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import pt.ulisboa.tecnico.sise.insure.insureapp.activities.WSHelper;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

import static android.content.ContentValues.TAG;

public class CustomerInfo extends AsyncTask <Integer, String, Customer> {

    private static final String TAG = "CustomerInfo";
    Context _context;

    public CustomerInfo(Context context) {
        _context = context;
    }

    protected Customer doInBackground(Integer ...params) {

        publishProgress("Testing method call getCustomerInfo...");
        try {
            Customer customer = WSHelper.getCustomerInfo(params[0]);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
                //Log.d(TAG, get().getFiscalNumber().toString());
                Log.d(TAG, get().getUsername().toString());
                Log.d(TAG, get().getAddress().toString());
                Log.d(TAG, get().getDateOfBirth().toString());
                //Log.d(TAG, get().getPolicyNumber().toString());
            }
            publishProgress("ok.\n");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            publishProgress("failed.\n");
        }
        return null;
    }

}