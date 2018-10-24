package com.example.vuongnv.noteapp.model.prenter;

import android.content.Context;

import com.example.vuongnv.noteapp.db.DatabaseManager;
import com.example.vuongnv.noteapp.model.Note;

import java.util.List;

public class NoteIndicator {
    private DatabaseManager mDatabaseManager;

    public NoteIndicator(Context context) {
        mDatabaseManager = new DatabaseManager(context);
    }

    public void getAllNotes(CallBackNoteListenner callBackNoteListenner){
        List<Note> arrNote = mDatabaseManager.getAllNotes();
        callBackNoteListenner.onLoadListNoteFinish(arrNote);
    }
    public interface CallBackNoteListenner{
        void onLoadListNoteFinish(List<Note> arrNote);
    }
}
