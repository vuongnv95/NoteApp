package com.example.vuongnv.noteapp.ui.notedetail;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.NoteDatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public class NoteIndicator {
    private NoteDatabaseManager mDatabaseManager;

    public NoteIndicator(Context context) {
        mDatabaseManager = NoteDatabaseManager.getInstance(context);
    }

    public void getAllNotes(CallBackNoteListenner callBackNoteListenner){
        List<Note> arrNote = mDatabaseManager.getAllNotes();
        callBackNoteListenner.onLoadListNoteFinish(arrNote);
    }
    public interface CallBackNoteListenner{
        void onLoadListNoteFinish(List<Note> arrNote);
    }
}
