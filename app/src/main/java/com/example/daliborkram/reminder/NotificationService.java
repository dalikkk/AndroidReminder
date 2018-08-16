package com.example.daliborkram.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationService extends IntentService {
    public NotificationService(){
        super("NotificationService");
    }

    protected void onHandleIntent(Intent intent) {
        // create notification
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Task was not completed");
        builder.setContentText("Task name: " + intent.getStringExtra(MainActivity.TASK_NAME));
        builder.setSmallIcon(R.drawable.ic_task);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(0, builder.build());
    }
}
