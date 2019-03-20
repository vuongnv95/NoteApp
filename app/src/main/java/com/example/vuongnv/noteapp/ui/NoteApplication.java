package com.example.vuongnv.noteapp.ui;

import android.app.Activity;
import android.app.Application;

import com.example.vuongnv.noteapp.di.component.DaggerNoteHelperComponent;
import com.example.vuongnv.noteapp.di.component.NoteHelperComponent;
import com.example.vuongnv.noteapp.di.modul.ApplicationContextModul;
import com.example.vuongnv.noteapp.di.modul.NoteHelperModul;

public class NoteApplication extends Application {
    private NoteHelperComponent mNoteHelperComponent;
    public static int index = 0;

    public static NoteApplication get(Activity activity) {
        return (NoteApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNoteHelperComponent = DaggerNoteHelperComponent.builder().noteHelperModul(new NoteHelperModul()).applicationContextModul(new ApplicationContextModul(this)).build();
    }

    public NoteHelperComponent getNoteHelperComponent() {
        return mNoteHelperComponent;
    }
}
