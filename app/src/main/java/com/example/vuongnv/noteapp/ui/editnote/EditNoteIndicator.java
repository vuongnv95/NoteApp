package com.example.vuongnv.noteapp.ui.editnote;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.NoteDatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public class EditNoteIndicator {
    private NoteDatabaseManager mDatabaseManager;

    public EditNoteIndicator(Context context) {
        mDatabaseManager = NoteDatabaseManager.getInstance(context);
    }

    public void getAllNotes(CallBackNoteListenner callBackNoteListenner){
        List<Note> arrNote = mDatabaseManager.getAllNotes();
        callBackNoteListenner.onLoadListNoteFinish(arrNote);
    }

    public void deleteNote(Note note,CallBackNoteListenner callBackNoteListenner){
        mDatabaseManager.deleteNote(note);
        callBackNoteListenner.deleteNodeFinish();
    }

    interface CallBackNoteListenner{
        void onLoadListNoteFinish(List<Note> arr);
        void deleteNodeFinish();
    }
}
