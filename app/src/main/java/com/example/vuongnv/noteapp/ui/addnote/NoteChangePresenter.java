package com.example.vuongnv.noteapp.ui.addnote;


import com.example.vuongnv.noteapp.data.db.model.Note;

public class NoteChangePresenter implements NoteChangeIndicator.CallBackChangeNoteListenner{

    private NoteChangeIndicator mNoteChangeIndicator;
    private INoteChangeView mINoteChangeView;

    public NoteChangePresenter(NoteChangeIndicator mNoteChangeIndicator, INoteChangeView mINoteChangeView) {
        this.mNoteChangeIndicator = mNoteChangeIndicator;
        this.mINoteChangeView = mINoteChangeView;
    }

    public void requestUpdateNode(Note note) {
        mNoteChangeIndicator.updateNode(note,this);
    }

    public void requestAddNote(Note note){
        mNoteChangeIndicator.addNote(note,this);
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