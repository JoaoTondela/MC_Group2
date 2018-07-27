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

public class ListClaimsActivity extends AppCompatActivity {
    private ListView listViewId;
    private ListView listViewTitle;
    private Bundle savedInstanceState;
    private Button buttonlogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_claims);
        listViewId = findViewById(R.id.ClaimsHistoryIDListView);
        listViewTitle = findViewById(R.id.ClaimsHistoryTitlesListView);
        buttonlogOut = (Button) findViewById(R.id.LogoutButton) ;

        new WSCallListClaims(this, listViewId, listViewTitle).execute(GlobalState.getSessionId());

        // attach click listener to list view items
        listViewId.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create the read note activity, passing to it the index position as parameter
                Log.d("position", position + "");
                Intent intent = new Intent(ListClaimsActivity.this, ClaimDetailsActivity.class);
                startActivity(intent);

                // if instead of string, we pass a list with notes, we can retrieve the original Note object this way
                //Note note = (Note)parent.getItemAtPosition(position);
            }
        });

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(ListClaimsActivity.this).execute(GlobalState.getSessionId());

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
