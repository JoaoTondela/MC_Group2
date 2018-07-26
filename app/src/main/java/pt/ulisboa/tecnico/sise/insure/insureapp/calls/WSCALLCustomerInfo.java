package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import pt.ulisboa.tecnico.sise.insure.insureapp.activities.CustomerInfoActivity;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class WSCALLCustomerInfo extends AsyncTask <Integer, Void, Customer> {
    private static final String TAG = "ClientDetails";
    Context _context;

    public WSCALLCustomerInfo(Context context){
        _context = context;
    }

    protected Customer doInBackground(Integer... params) {
        Log.d("test", "test");
        try {
            Customer customer = WSHelper.getCustomerInfo(params[0]);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                Log.d(TAG, "Get customer info result => " + customer.toString());
                return customer;
            }

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        return null;
    }

    protected void onPostExecute( Customer customer) {
        super.onPostExecute(customer);
        Intent intent = new Intent(_context, CustomerInfoActivity.class);
        intent.putExtra("name", customer.getName());
        intent.putExtra("NIF", customer.getFiscalNumber());
        intent.putExtra("address", customer.getAddress());
        intent.putExtra("birthDate", customer.getDateOfBirth());
        intent.putExtra("policyNumber", customer.getPolicyNumber());
        _context.startActivity(intent);


    }

}
