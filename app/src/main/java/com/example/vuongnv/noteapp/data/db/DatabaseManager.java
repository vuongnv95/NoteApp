package com.example.vuongnv.noteapp.data.db;

import android.content.Context;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.utils.DatabaseUtils;

import java.util.List;

public class DatabaseManager implements IDatabase {

    private Context mContext;
    private NoteDatabaseHelper mNoteDatabase;
    private static DatabaseManager instance = null;

    public static DatabaseManager getInstance(Context context) {
        if(instance == null)
            instance = new DatabaseManager(context);

        return instance;
    }

    public DatabaseManager(Context context) {
        this.mContext = context;
        mNoteDatabase = new NoteDatabaseHelper(mContext,DatabaseUtils.DATABASE_NAME, null, DatabaseUtils.DATABASE_VERSION);
    }

    @Override
    public List<Note> getAllNotes() {
        return mNoteDatabase.getAllNotes();
    }

    @Override
    public int updateNote(Note note) {
        return mNoteDatabase.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        mNoteDatabase.deleteNote(note);
    }

    @Override
    public void addNote(Note note) {
        mNoteDatabase.addNote(note);
    }
}
