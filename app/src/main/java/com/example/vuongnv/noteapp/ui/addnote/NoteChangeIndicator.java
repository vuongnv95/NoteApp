package com.example.vuongnv.noteapp.ui.addnote;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.DatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;


public class NoteChangeIndicator {
    private Context mContext;

    public NoteChangeIndicator(Context mContext) {
        this.mContext = mContext;
    }

    public void updateNode(Note note, CallBackChangeNoteListenner callBackChangeNoteListenner) {
        DatabaseManager.getInstance(mContext).updateNote(note);
        callBackChangeNoteListenner.updateNoteFinish(note);
    }

    public void addNote(Note note, CallBackChangeNoteListenner callBackChangeNoteListenner) {
        long index = DatabaseManager.getInstance(mContext).addNote(note);
        note.setmIdNode((int) index);
        callBackChangeNoteListenner.addNoteFinish(note);
    }

    public interface CallBackChangeNoteListenner {
        void updateNoteFinish(Note note);

        void addNoteFinish(Note note);
    }
}
