package com.example.vuongnv.noteapp.ui.addnote;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.vuongnv.noteapp.data.db.NoteDatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;


public class NoteChangeIndicator {
    private Context mContext;

    public NoteChangeIndicator(Context mContext) {
        this.mContext = mContext;
    }

    public void updateNode(Note note, CallBackChangeNoteListenner callBackChangeNoteListenner) {
        NoteDatabaseManager.getInstance(mContext).updateNote(note);
        callBackChangeNoteListenner.updateNoteFinish(note);
    }

    public void addNote(Note note, CallBackChangeNoteListenner callBackChangeNoteListenner) {
        long index = NoteDatabaseManager.getInstance(mContext).addNote(note);
        Log.d("Vuong", "addNote() called with:index = [" + index + "]");
        note.setmIdNode((int) index);
        callBackChangeNoteListenner.addNoteFinish(note);
        Log.d("Vuong", "index [" + note.getmIdNode() + "]");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<NoteImage> getAllNoteImages(Note note){
        return NoteDatabaseManager.getInstance(mContext).getAllNoteImages(note);
    }

    public void updateNoteImages(Note note,List<NoteImage> arrNoteImages){
        NoteDatabaseManager.getInstance(mContext).updateNoteImage(note,arrNoteImages);
    }

    public void addNoteImages(Note note,List<NoteImage> arrNoteImages){
        NoteDatabaseManager.getInstance(mContext).addNoteImage(note,arrNoteImages);
    }
    public interface CallBackChangeNoteListenner {
        void updateNoteFinish(Note note);

        void addNoteFinish(Note note);
    }
}
