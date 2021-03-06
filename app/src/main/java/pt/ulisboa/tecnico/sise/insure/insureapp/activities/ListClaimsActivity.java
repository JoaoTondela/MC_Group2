package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallListClaims;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;

public class ListClaimsActivity extends AppCompatActivity {
    private static final String TAG = "ListClaimsActivity";
    Context _context = this;
    private ListView listViewId;
    private ListView listViewTitle;
    private Bundle savedInstanceState;
    private Button buttonlogOut;
    int claimId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_claims);
        listViewId = findViewById(R.id.ClaimsHistoryIDListView);
        listViewTitle = findViewById(R.id.ClaimsHistoryTitlesListView);
        buttonlogOut = (Button) findViewById(R.id.LogoutButton);
        GlobalState globalState = (GlobalState) getApplicationContext();

        new WSCallListClaims(this, globalState, listViewId, listViewTitle).execute(GlobalState.getCustomer().getSessionId());

        // attach click listener to list view items
        listViewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the claim details activity, passing to it the claimId as parameter
                String claimIdString = listViewId.getAdapter().getItem(position).toString();
                claimId = Integer.parseInt(claimIdString);
                Intent intent = new Intent(_context, ClaimDetailsActivity.class);
                intent.putExtra("claimId", claimId);
                _context.startActivity(intent);
            }
        });

        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the claim details activity, passing to it the claimId as parameter
                String claimIdString = listViewId.getAdapter().getItem(position).toString();
                claimId = Integer.parseInt(claimIdString);
                Intent intent = new Intent(_context, ClaimDetailsActivity.class);
                intent.putExtra("claimId", claimId);
                _context.startActivity(intent);
            }
        });

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(ListClaimsActivity.this).execute(GlobalState.getCustomer().getSessionId());

            }
        });
    }

    public void logOut(View view) {

        // Create an Intent to start the second activity
        Intent logOut = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOut);
    }

    public void claimDetails(View view) {

        // Create an Intent to start the second activity
        Intent claimDetails = new Intent(this, ClaimDetailsActivity.class);

        // Start the new activity.
        startActivity(claimDetails);
    }

    public void backButton(View view) {

        // Create an Intent to start the second activity
        Intent backButton = new Intent(this, MainActivity.class);

        // Start the new activity.
        startActivity(backButton);
    }
}
