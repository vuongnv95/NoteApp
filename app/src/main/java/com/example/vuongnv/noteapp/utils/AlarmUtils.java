package com.example.vuongnv.noteapp.utils;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.service.AlarmBroadcast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmUtils {
    public static final String FORMAT_TIME= "MM/dd/yyyy HH:mm";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void createAlarm(Context context, Note note) {
        if (note.getmIsAlarm() == NoteUtils.NO_ALARM){
            cancelAlarm(context,note);
            return;
        }
        Intent intent = new Intent(context, AlarmBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, note.getmIdNode(), intent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long time = formatDate(note);
        am.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        Log.d("Vuong", "createAlarm() called with: context = [" + context + "], note = [" + note + "]");
    }

    private static long formatDate(Note note) {
        String time = note.getmDate() + " " + note.getmTime();
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIME);
        try {
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(format.parse(time));
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void cancelAlarm(Context context, Note note) {
        Intent intent = new Intent(context, AlarmBroadcast.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, note.getmIdNode(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
