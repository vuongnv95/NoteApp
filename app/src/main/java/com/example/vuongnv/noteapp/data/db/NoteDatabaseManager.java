package com.example.vuongnv.noteapp.data.db;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.utils.DatabaseUtils;

import java.util.List;

public class NoteDatabaseManager implements INoteDatabase {

    private Context mContext;
    private NoteDatabaseHelper mNoteDatabase;
    private NoteImageDatabaseHelper mNoteImageDatabaseHelper;
    private static NoteDatabaseManager instance = null;

    public static NoteDatabaseManager getInstance(Context context) {
        if(instance == null)
            instance = new NoteDatabaseManager(context);

        return instance;
    }

    public NoteDatabaseManager(Context context) {
        this.mContext = context;
        mNoteDatabase = new NoteDatabaseHelper(mContext,DatabaseUtils.DATABASE_NAME, null, DatabaseUtils.DATABASE_VERSION);
        mNoteImageDatabaseHelper = new NoteImageDatabaseHelper(mContext,DatabaseUtils.DATABASE_NAME_NOTE_IMAGE, null, DatabaseUtils.DATABASE_VERSION);
    }

    @Override
    public List<Note> getAllNotes() {
        return mNoteDatabase.getAllNotes();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public List<NoteImage> getAllNoteImages(Note note) {
        return mNoteImageDatabaseHelper.getAllImageOfNote(note);
    }

    @Override
    public int updateNote(Note note) {
        return mNoteDatabase.updateNote(note);
    }

    @Override
    public void updateNoteImage(Note note,List<NoteImage> arrNoteImage) {
        mNoteImageDatabaseHelper.updateAllNoteImage(note,arrNoteImage);
    }

    @Override
    public void deleteNote(Note note) {
        mNoteDatabase.deleteNote(note);
    }

    @Override
    public void deleteNoteImages(Note note) {
            mNoteImageDatabaseHelper.deleteAllNoteImage(note.getmIdNode());
    }

    @Override
    public long addNote(Note note) {
       return mNoteDatabase.addNote(note);
    }

    @Override
    public void addNoteImage(Note note,List<NoteImage> arrNoteImage) {
        mNoteImageDatabaseHelper.addNoteImages(note,arrNoteImage);
    }
}
