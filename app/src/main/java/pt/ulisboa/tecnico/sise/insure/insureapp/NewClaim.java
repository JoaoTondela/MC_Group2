package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewClaim extends AppCompatActivity {

    private static final String LOG_TAG = "inSure - New Claim";
    private Button buttonSubmit;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_claim);

        buttonSubmit = (Button) findViewById(R.id.submitClaim);
        buttonCancel = (Button) findViewById(R.id.BackButton);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // return an intent containing the title and body of the new note
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                // write a toast message
                Toast.makeText(v.getContext(), "Claim submited!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // return the return code only; no intent message is required
                setResult(Activity.RESULT_CANCELED);
                // write a toast message
                Toast.makeText(v.getContext(), "Changes not saved!", Toast.LENGTH_SHORT).show();
                // finish activity
                finish();
            }
        });
    }

    public void submitMe(View view) {
        Toast mySubmit = Toast.makeText(this, "Claim submited!", Toast.LENGTH_SHORT);
        mySubmit.show();
    }

    public void logOut (View view) {

        // Create an Intent to start the second activity
        Intent logOutIntent = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOutIntent);
    }
}
