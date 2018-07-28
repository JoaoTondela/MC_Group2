package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCALLCustomerInfo;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallLogOut;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallNewClaim;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSCallListClaims;

public class MainActivity extends AppCompatActivity {

    Context _context = this;
    private Button btnCustomerInfo;
    private Button btnListClaims;
    private Button btnListPlates;
    private Button buttonlogOut;
    public Intent notIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notIntent = new Intent(this, NotificationService.class);
        startService(notIntent);

        setContentView(R.layout.activity_main);
        buttonlogOut = (Button) findViewById(R.id.logoutButton);

        btnCustomerInfo = (Button) findViewById(R.id.btn_customer_info);
        btnCustomerInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                Intent intent = new Intent(_context, CustomerInfoActivity.class);
                _context.startActivity(intent);
            }
        });

        btnListClaims = (Button) findViewById(R.id.btn_claims_list);
        btnListClaims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(_context, ListClaimsActivity.class);
                _context.startActivity(intent);
            }
        });

        btnListPlates = (Button) findViewById(R.id.btn_new_claim);
        btnListPlates.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                Intent intent = new Intent(_context, NewClaimActivity.class);
                _context.startActivity(intent);
            }
        });

        buttonlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                new WSCallLogOut(MainActivity.this).execute(GlobalState.getCustomer().getSessionId());

            }
        });
    }
/*
    private NotificationManager notifManager;

    public void notification() {
        final int NOTIFY_ID = 1002;
        String name = "my_package_channel";
        String id = "my_package_channel_1"; // The user-visible name of the channel.
        String description = "my_package_first_channel";
        String title = "titulo fixo";

        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if (notifManager == null) {
            notifManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);

            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(title)  // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setTicker(title)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    //.setContentIntent(pendingIntent)
        } else {
            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(title)                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(title)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }

        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }*/
/*
    public Timer timer = new Timer();
    public TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.d("timer","timer actuou");
        }
    };*/


}
