package com.example.vuongnv.noteapp.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.ui.activitis.MainActivity;

public class AlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Vuong", "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
        Intent intentStart = new Intent(context, MainActivity.class);
        intentStart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intentStart, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_camera)
                        .setContentTitle("Note")
                        .setContentText("Hello!")
                        .setContentIntent(pendingIntent);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }
}
