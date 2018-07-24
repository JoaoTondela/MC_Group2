package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListClaimsActivity extends AppCompatActivity {

    private ListView listViewID;
    private ListView listViewTitle;
    private ArrayList<String> claimid;
    private ArrayList<String> claimtitle;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_claims);

        claimtitle = new ArrayList<String>();
        claimid = new ArrayList<String>();

        claimid.add("0001");
        claimtitle.add("Car Insurance");
        claimid.add("0002");
        claimtitle.add("Car Insurance");
        claimid.add("0003");
        claimtitle.add("Car Insurance");
        claimid.add("0003");
        claimtitle.add("Car Insurance");

        listViewID = (ListView) findViewById(R.id.ClaimsHistoryIDListView);
        String[] itemsID = claimid.toArray(new String[claimid.size()]);
        ArrayAdapter<String> adapterID = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemsID);
        listViewID.setAdapter(adapterID);


        listViewTitle = (ListView) findViewById(R.id.ClaimsHistoryTitlesListView);
        String[] itemsTitle = claimtitle.toArray(new String[claimtitle.size()]);
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemsTitle);
        listViewTitle.setAdapter(adapterTitle);

        Button backButton = (Button) findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void logOut (View view) {

        // Create an Intent to start the second activity
        Intent logOut = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOut);
    }

    public void claimDetails (View view) {

        // Create an Intent to start the second activity
        Intent claimDetails = new Intent(this, ClaimDetails.class);

        // Start the new activity.
        startActivity(claimDetails);
    }
}
