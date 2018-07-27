package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallClaimDetails;

public class ClaimDetailsActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private ListView listView;
    private  int claimId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        listView = findViewById(R.id.ClaimDetailsListView);
        claimId = getIntent().getIntExtra("claimId", -1);

        new WSCallClaimDetails(this, listView, claimId).execute(GlobalState.getSessionId());
    }

    public void logOut (View view) {

        // Create an Intent to start the second activity
        Intent logOutIntent = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOutIntent);
    }

    public void newMessage (View view) {

        // Create an Intent to start the second activity
        Intent newMessage = new Intent(this, ListMessagesActivity.class);

        // Start the new activity.
        startActivity(newMessage);
    }

    public void backButton (View view) {

        // Create an Intent to start the second activity
        Intent backButton = new Intent(this, ListClaimsActivity.class);

        // Start the new activity.
        startActivity(backButton);
    }
}
