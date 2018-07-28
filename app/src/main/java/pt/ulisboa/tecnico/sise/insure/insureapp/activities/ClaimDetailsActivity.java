package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallClaimDetails;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;

public class ClaimDetailsActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private ListView listView;
    private  int claimId;
    private Button buttonlogOut;
    private Button buttonMessage;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        listView = findViewById(R.id.ClaimDetailsListView);
        claimId = getIntent().getIntExtra("claimId", -1);
        Log.d("name", String.valueOf(claimId));
        buttonlogOut = (Button) findViewById(R.id.LogoutButton);
        buttonMessage = (Button) findViewById(R.id.SupportChatButton);
        _context = this;
        GlobalState globalState = (GlobalState) getApplicationContext();

        new WSCallClaimDetails(this, globalState, listView, claimId).execute(GlobalState.getSessionId());

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(ClaimDetailsActivity.this).execute(GlobalState.getSessionId());

            }
        });

        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_context, ListMessagesActivity.class);
                intent.putExtra("claimId", claimId);
                _context.startActivity(intent);
            }
        });
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
