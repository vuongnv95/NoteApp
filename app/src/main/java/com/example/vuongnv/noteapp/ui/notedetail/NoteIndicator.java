package com.example.vuongnv.noteapp.ui.notedetail;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.DatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public class NoteIndicator {
    private DatabaseManager mDatabaseManager;

    public NoteIndicator(Context context) {
        mDatabaseManager = DatabaseManager.getInstance(context);
    }

    public void getAllNotes(CallBackNoteListenner callBackNoteListenner){
        List<Note> arrNote = mDatabaseManager.getAllNotes();
        callBackNoteListenner.onLoadListNoteFinish(arrNote);
    }
    public interface CallBackNoteListenner{
        void onLoadListNoteFinish(List<Note> arrNote);
    }
}
