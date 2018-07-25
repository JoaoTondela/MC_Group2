package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.Customer;

public class CustomerInfoActivity extends AppCompatActivity {

    private ArrayList<String> customerInfo;
    //Context context;
    private ListView listView;
    //Integer sessionId = ((GlobalState) this.getApplication()).getSessionId();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dsa", "tolo");
        setContentView(R.layout.activity_customer_info);
        String name = getIntent().getExtras().getString("name");
        String NIF = String.valueOf(getIntent().getExtras().getInt("NIF"));
        String address = getIntent().getExtras().getString("address");
        String birthDate = getIntent().getExtras().getString("birthDate");
        String policyNumber = String.valueOf(getIntent().getExtras().getInt("policyNumber"));
        customerInfo = new ArrayList<String>();

        customerInfo.add(name.toString());
        customerInfo.add(NIF.toString());
        customerInfo.add(address.toString());
        customerInfo.add(birthDate.toString());
        customerInfo.add(policyNumber.toString());

       // clientDetailsServer(sessionId);
       // new WSCALLCustomerInfo(this.context).execute(GlobalState.getSessionId());
        setContentView(R.layout.activity_customer_info);


        Log.d("name", name);
        listView = (ListView) findViewById(R.id.customerInfo);
        String[] itemsInfo = customerInfo.toArray(new String[customerInfo.size()]);
        ArrayAdapter<String> adapterInfo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemsInfo);
        listView.setAdapter(adapterInfo);


        Button backButton = (Button) findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void logOut (View view) {

        // Create an Intent to start the second activity
        Intent logOut = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOut);
    }
}
