package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomerInfoActivity extends AppCompatActivity {

    private ArrayList<String> customerInfo;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerInfo = new ArrayList<String>();
        customerInfo.add("Joao Tondela");
        customerInfo.add("123456789");
        customerInfo.add("INESC SISE");
        customerInfo.add("27-07-1990");
        customerInfo.add("A0001");


        setContentView(R.layout.activity_customer_info);

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
/*
    public void getCustomerInfo() {
        new CustomerInfo().execute();
    }
    */


}
