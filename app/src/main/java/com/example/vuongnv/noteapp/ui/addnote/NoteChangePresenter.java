package com.example.vuongnv.noteapp.ui.addnote;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;

public class NoteChangePresenter implements NoteChangMVPPresenter, NoteChangeIndicator.CallBackChangeNoteListenner {

    private NoteChangeIndicator mNoteChangeIndicator;
    private INoteChangeView mINoteChangeView;

    public NoteChangePresenter(NoteChangeIndicator mNoteChangeIndicator, INoteChangeView mINoteChangeView) {
        this.mNoteChangeIndicator = mNoteChangeIndicator;
        this.mINoteChangeView = mINoteChangeView;
    }

    @Override
    public void requestUpdateNode(Note note) {
        mNoteChangeIndicator.updateNode(note, this);
    }

    @Override
    public void requestAddNote(Note note) {
        mNoteChangeIndicator.addNote(note, this);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<NoteImage> requestNoteImages(Note note) {
        return mNoteChangeIndicator.getAllNoteImages(note);
    }

    @Override
    public void requestAddNoteImages(Note note, List<NoteImage> arrNoteImages) {
        mNoteChangeIndicator.addNoteImages(note, arrNoteImages);
    }

    @Override
    public void requestUpdateNoteImages(Note note, List<NoteImage> arrNoteImages) {
        mNoteChangeIndicator.updateNoteImages(note, arrNoteImages);
    }

    @Override
    public void clickImageItem(int position) {
        Log.d("Vuong", "clickImageItem() called with: position = [" + position + "]");
        mINoteChangeView.clickDeleteNoteImage(position);
    }

    @Override
    public void updateNoteFinish(Note note) {
        mINoteChangeView.updateNoteEdit(note);
    }

    @Override
    public void addNoteFinish(Note note) {
        mINoteChangeView.updateNoteAdd(note);
    }
}
