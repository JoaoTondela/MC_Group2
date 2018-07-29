package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallListMessages;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallNewMessage;

public class ListMessagesActivity extends AppCompatActivity {
    private EditText _messageContent;
    private TextView _sendMessage;
    private long messageTime;
    private int claimId;
    private Button _btnToMessage;

    private Context _context = this;
    private ListView _listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);
        claimId = getIntent().getIntExtra("claimId",-1 );
        _listView = findViewById(R.id.text_message_body_message_Detais);

        GlobalState globalState = (GlobalState) getApplicationContext();

        new WSCallListMessages(_context, globalState, claimId,_listView).execute(globalState.getCustomer().getSessionId());



        final Button btn = (Button) findViewById(R.id.button_chatbox_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView msg = findViewById(R.id.edittext_chatbox);
                String message = msg.getText().toString();
                new WSCallNewMessage(_context, claimId, _listView).execute(message);
                msg.setText("");
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
