package com.example.vuongnv.noteapp.ui.editnote;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.DatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.notedetail.NoteIndicator;

import java.util.ArrayList;
import java.util.List;

public class EditNoteIndicator {
    private DatabaseManager mDatabaseManager;

    public EditNoteIndicator(Context context) {
        mDatabaseManager = DatabaseManager.getInstance(context);
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
