package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ulisboa.tecnico.sise.insure.insureapp.R;

public class ClaimDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);


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
