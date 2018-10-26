package com.example.vuongnv.noteapp.ui.notedetail;


import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public class NotePresenter implements NoteIndicator.CallBackNoteListenner{
    private INoteView mINoteView;
    private NoteIndicator mNoteIndicator;

    public NotePresenter(INoteView mINoteView, NoteIndicator mNoteIndicator) {
        this.mINoteView = mINoteView;
        this.mNoteIndicator = mNoteIndicator;
    }

    public void getAllNotes(){
        mNoteIndicator.getAllNotes(this);
    }

    @Override
    public void onLoadListNoteFinish(List<Note> arrNote) {
        mINoteView.updateAllNotes(arrNote);
    }
}
