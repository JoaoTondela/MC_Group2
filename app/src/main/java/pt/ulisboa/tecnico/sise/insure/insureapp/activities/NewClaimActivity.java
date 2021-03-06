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
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;
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
    private Button buttonlogOut;
    private ArrayList <String> plates;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);

        buttonSubmit = (Button) findViewById(R.id.submitClaim);
        buttonCancel = (Button) findViewById(R.id.BackButton);
        platesSpinner = (Spinner) findViewById(R.id.listPlates);
        buttonlogOut = (Button) findViewById(R.id.logoutButton2);


        new WSCallNewClaim(this, platesSpinner).execute(GlobalState.getCustomer().getSessionId());

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick( View v ) {

                int id = GlobalState.getCustomer().getSessionId();
                String title = getTitleFromText();
                String occurrenceDate = getDateFromText();
                String plate = getPlateFromText();
                String description = getDescripitonFromText();

                ClaimWrapper claimWrapper = new ClaimWrapper(id, title, occurrenceDate, plate, description);

                new WSCallSubmitNewClaim(NewClaimActivity.this).execute(claimWrapper);
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
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

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(NewClaimActivity.this).execute(GlobalState.getCustomer().getSessionId());

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
        String plate = rawPlate.getSelectedItem().toString();
        return plate;
    }
}

