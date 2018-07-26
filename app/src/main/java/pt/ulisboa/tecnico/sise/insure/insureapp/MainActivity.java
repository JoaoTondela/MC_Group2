package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context _context = this;
    private Button btnCustomerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCustomerInfo = (Button) findViewById(R.id.btn_customer_info);
        btnCustomerInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                new WSCALLCustomerInfo(_context).execute(GlobalState.getSessionId());
            }
        });

        Button btn = (Button) findViewById(R.id.btn_notification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification();
            }
        });
    }

    public void notification() {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.insure_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.insure_logo))
                .setContentTitle("Just a random notification!")
                .setContentText("Well done!");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
/*
    public void customerInfo (View view) {

        //new WSCALLCustomerInfo(_context).execute(GlobalState.getSessionId());

        // Create an Intent to start the second activity
        Intent infoIntent = new Intent(this, CustomerInfoActivity.class);

        // Start the new activity.
        startActivity(infoIntent);
    }
    */

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
