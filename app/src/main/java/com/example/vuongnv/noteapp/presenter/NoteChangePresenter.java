package com.example.vuongnv.noteapp.presenter;


import com.example.vuongnv.noteapp.model.Note;
import com.example.vuongnv.noteapp.model.prenter.NoteChangeIndicator;
import com.example.vuongnv.noteapp.view.presenter.INoteChangeView;

public class NoteChangePresenter implements NoteChangeIndicator.CallBackChangeNoteListenner{

    private NoteChangeIndicator mNoteChangeIndicator;
    private INoteChangeView mINoteChangeView;

    public NoteChangePresenter(NoteChangeIndicator mNoteChangeIndicator, INoteChangeView mINoteChangeView) {
        this.mNoteChangeIndicator = mNoteChangeIndicator;
        this.mINoteChangeView = mINoteChangeView;
    }

    public void updateNode(Note note) {
        mNoteChangeIndicator.updateNode(note,this);
    }

    public void addNote(Note note){
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
