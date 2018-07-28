package pt.ulisboa.tecnico.sise.insure.insureapp.calls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class WSCALLCustomerInfo extends AsyncTask <Integer, Void, Customer> {
    private static final String TAG = "ClientDetails";
    Context _context;
    ListView _listView;
    Customer customer;
    GlobalState _globalState;

    public WSCALLCustomerInfo( Context context, GlobalState globalState, ListView listView ){
        _context = context;
        _listView = listView;
        _globalState = globalState;
    }

    protected Customer doInBackground(Integer... params) {
        try {
            customer = WSHelper.getCustomerInfo(params[0]);
            if (customer == null) {
                Log.d(TAG, "Get customer info result => null");
            } else {
                return customer;
            }

        } catch (Exception e) {
            Log.d(TAG, "esta offline no Customer Info por isso entrou aqui");
            customer = _globalState.readCustomerFile();
            Log.d(TAG, "fez readCustomerFile no Customer Info");
            Log.d(TAG, e.toString());
            return customer;
        }

        return null;
    }

    protected void onPostExecute( Customer customer) {
        super.onPostExecute(customer);

        String name = customer.getName();
        String NIF = String.valueOf(customer.getFiscalNumber());
        String address = customer.getAddress();
        String birthDate = customer.getDateOfBirth();
        String policyNumber = String.valueOf(customer.getPolicyNumber());

        ArrayList<String> customerInfo = new ArrayList<>();


        customerInfo.add(name.toString());
        customerInfo.add(NIF.toString());
        customerInfo.add(address.toString());
        customerInfo.add(birthDate.toString());
        customerInfo.add(policyNumber.toString());



        String[] itemsInfo = customerInfo.toArray(new String[customerInfo.size()]);
        ArrayAdapter<String> adapterInfo = new ArrayAdapter<String>(_context, android.R.layout.simple_list_item_1, android.R.id.text1, itemsInfo);
        _listView.setAdapter(adapterInfo);

    }

}
