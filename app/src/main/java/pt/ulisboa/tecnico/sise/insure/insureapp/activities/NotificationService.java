package pt.ulisboa.tecnico.sise.insure.insureapp.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pt.ulisboa.tecnico.sise.insure.insureapp.GlobalState;
import pt.ulisboa.tecnico.sise.insure.insureapp.R;
import pt.ulisboa.tecnico.sise.insure.insureapp.calls.WSHelper;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimItem;
import pt.ulisboa.tecnico.sise.insure.insureapp.datamodel.ClaimMessage;

public class NotificationService extends Service {

    private static final String TAG = "NotificationService";
    private static Timer _timer;
    private TimerTask _timerTask;
    private static boolean FLAG_RUNNING;

    public void onCreate() {
        super.onCreate();
        _timer = new Timer();
        _timerTask = new TimerTask() {
            HashMap currentInSureMsgPerClaim = new HashMap();
            @Override
            public void run() {
                try {
                    currentInSureMsgPerClaim = getNumberAutoInSureMessages(GlobalState.getCustomer().getSessionId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!currentInSureMsgPerClaim.equals(GlobalState.inSureMsgPerClaim)) {
                    for (Object key: currentInSureMsgPerClaim.keySet()) {
                        try {
                            boolean isDifferent = !GlobalState.inSureMsgPerClaim.get(key).equals(currentInSureMsgPerClaim.get(key));
                            if (isDifferent) { createNotification(key); }
                            GlobalState.diffInSureMsgPerClaim.put(key, isDifferent);
                        } catch (Exception e) {
                            Log.d(TAG, e.toString());
                        }
                    }
                    GlobalState.inSureMsgPerClaim = currentInSureMsgPerClaim;
                }
            }
        };

        if(!FLAG_RUNNING) {
            FLAG_RUNNING = true;
            customRun();
        }
    }

    private void customRun() {
        _timer.schedule(_timerTask, 1000, 3000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //_timer.cancel();
        super.onDestroy();
    }



    private NotificationManager notifManager;
    public void createNotification(Object key) {
        final int NOTIFY_ID = 1002;
        String name = "my_package_channel";
        String id = "my_package_channel_1"; // The user-visible name of the channel.
        String description = "Claim " + key.toString();
        String title = "New message";

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

            intent = new Intent(this, ListClaimsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(title)  // required
                    .setSmallIcon(R.drawable.insure_logo) // required
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.insure_logo))
                    .setContentText(description)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setTicker(title)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setContentIntent(pendingIntent);
        } else {
            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, ListClaimsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(title)                           // required
                    .setSmallIcon(R.drawable.insure_logo) // required
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.insure_logo))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(title)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }

        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    private HashMap getNumberAutoInSureMessages(int sessionId) throws Exception {
        HashMap inSureMsgPerClaim = new HashMap();
        List<ClaimItem> claimItems = WSHelper.listClaims(sessionId);
        for (ClaimItem clm : claimItems) {
            List<ClaimMessage> msgs = WSHelper.listClaimMessages(sessionId, clm.getId());
            int autoInSureMsg = 0;
            for (ClaimMessage msg : msgs) {
                if (msg.getSender().equals("AutoInSure")) {
                    autoInSureMsg++;
                }
            }
            inSureMsgPerClaim.put(clm.getId(), autoInSureMsg);
        }
        return inSureMsgPerClaim;
    }
}
