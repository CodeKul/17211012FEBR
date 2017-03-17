package com.codekul.statusbarnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTI_SIMPLE = 1235;
    private static final int REQ_SAMPLE = 4569;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notification(View view) {

        Intent intent = new Intent(this, SampleActivity.class);
        intent.putExtra("notiId", NOTI_SIMPLE);

        PendingIntent pendingAction =
            PendingIntent.getActivity(this, REQ_SAMPLE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentInfo("Info")
                .setContentText("Text")
                .setContentTitle("Title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowWhen(true)
                .setContentIntent(pendingAction)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .addAction(R.mipmap.ic_launcher, "Action 1", pendingAction)
                .build();
//         notification.vibrate = new long[]{};

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTI_SIMPLE, notification);
    }
}
