package com.example.daliborkram.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, NotificationService.class);
        intent1.putExtra(MainActivity.TASK_NAME, intent.getStringExtra(MainActivity.TASK_NAME));
        context.startService(intent1);
    }
}
