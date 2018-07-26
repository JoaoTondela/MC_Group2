package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallNewClaim;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallSubmitNewClaim;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimRecord;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimWrapper;

public class NewClaimActivity extends AppCompatActivity {

    private static final String LOG_TAG = "inSure - New Claim";
    private Button buttonSubmit;
    private Button buttonCancel;
    private Spinner platesSpinner;
    private ArrayList <String> plates;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);

        buttonSubmit = (Button) findViewById(R.id.submitClaim);
        buttonCancel = (Button) findViewById(R.id.BackButton);
        platesSpinner = (Spinner) findViewById(R.id.listPlates);

        //for (String plate : getIntent().getStringArrayListExtra(listOfPlates))

        new WSCallNewClaim(this, platesSpinner).execute(GlobalState.getSessionId());

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick( View v ) {

                int id = GlobalState.getSessionId();
                String title = getTitleFromText();
                String occurrenceDate = getDateFromText();
                String plate = getPlateFromText();
                String description = getDescripitonFromText();

                ClaimWrapper claimWrapper = new ClaimWrapper(id, title, occurrenceDate, plate, description);

                new WSCallSubmitNewClaim(NewClaimActivity.this).execute(claimWrapper);
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                // write a toast message
                Toast.makeText(v.getContext(), "Claim submited!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick( View v ) {
                // return the return code only; no intent message is required
                setResult(Activity.RESULT_CANCELED);
                // write a toast message
                Toast.makeText(v.getContext(), "Changes not saved!", Toast.LENGTH_SHORT).show();
                // finish activity
                finish();
            }
        });
    }

    private String getDescripitonFromText() {
        TextView rawDescription = (TextView) findViewById(R.id.editText4);
        String description = rawDescription.getText().toString();
        return description;
    }

    public String getTitleFromText() {
        TextView rawTitle = (TextView) findViewById(R.id.editText6);
        String title = rawTitle.getText().toString();
        return title;
    }

    public String getDateFromText() {
        TextView rawDate = (TextView) findViewById(R.id.editText5);
        String date = rawDate.getText().toString();
        return date;
    }

    public String getPlateFromText() {
        Spinner rawPlate = (Spinner) findViewById(R.id.listPlates);
        String plate = rawPlate.getTransitionName();
        return plate;
    }

    public void submitMe( View view ) {
        Toast mySubmit = Toast.makeText(this, "Claim submited!", Toast.LENGTH_SHORT);
        mySubmit.show();
    }

    public void logOut( View view ) {

        // Create an Intent to start the second activity
        Intent logOutIntent = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOutIntent);
    }



}

