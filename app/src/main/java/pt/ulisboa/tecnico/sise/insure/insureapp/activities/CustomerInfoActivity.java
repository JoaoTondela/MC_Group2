package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCALLCustomerInfo;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;

public class CustomerInfoActivity extends AppCompatActivity {

    private ArrayList<String> customerInfo;
    private ListView listView;
    private Button buttonlogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);
        listView = findViewById(R.id.customerInfo);
        buttonlogOut = (Button) findViewById(R.id.LogoutButton);
        GlobalState globalState = (GlobalState) getApplicationContext();

        new WSCALLCustomerInfo(this, globalState, listView).execute(GlobalState.getCustomer().getSessionId());

        Button backButton = (Button) findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(CustomerInfoActivity.this).execute(GlobalState.getCustomer().getSessionId());

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
