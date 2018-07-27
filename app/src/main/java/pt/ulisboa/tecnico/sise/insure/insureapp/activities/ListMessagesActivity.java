package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallMessages;

public class ListMessagesActivity extends AppCompatActivity {
    private int claimId;
    private TextView textView;
    private RecyclerView recyclerView;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);
        claimId = getIntent().getIntExtra("claimId", -1);
        recyclerView = findViewById(R.id.reyclerview_message_list);

        new WSCallMessages(this, recyclerView, claimId).execute(GlobalState.getSessionId());

        buttonSend = (Button) findViewById(R.id.button_chatbox_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
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
                .setContentTitle("Claim Received")
                .setContentText("We've received your claim and we will review it!");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notificationBuilder.build());
    }

    public void logOut(View view) {

        // Create an Intent to start the second activity
        Intent logOut = new Intent(this, LoginActivity.class);

        // Start the new activity.
        startActivity(logOut);
    }
}
