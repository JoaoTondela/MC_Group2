package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void customerInfo (View view) {

        // Create an Intent to start the second activity
        Intent infoIntent = new Intent(this, CustomerInfoActivity.class);

        // Start the new activity.
        startActivity(infoIntent);
    }

    public void logOut (View view) {

        // Create an Intent to start the second activity
        Intent logOutIntent = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOutIntent);
    }

    public void claimInfo (View view) {

        // Create an Intent to start the second activity
        Intent claimInfo = new Intent(this, ListClaimsActivity.class);

        // Start the new activity.
        startActivity(claimInfo);
    }

    public void newClaim (View view) {

        // Create an Intent to start the second activity
        Intent newClaim = new Intent(this, NewClaim.class);

        // Start the new activity.
        startActivity(newClaim);
    }

    public void messageInfo (View view) {

        // Create an Intent to start the second activity
        Intent messageInfo = new Intent(this, ListMessages.class);

        // Start the new activity.
        startActivity(messageInfo);
    }
}
