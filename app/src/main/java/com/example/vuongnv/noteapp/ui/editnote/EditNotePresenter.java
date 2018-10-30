package com.example.vuongnv.noteapp.ui.editnote;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.notedetail.NoteIndicator;

import java.util.ArrayList;
import java.util.List;

public class EditNotePresenter implements EditNoteMVPPresenter, EditNoteIndicator.CallBackNoteListenner {
    private EditNoteIndicator mEditNoteIndicator;
    private IEditNoteView mIEditNoteView;

    public EditNotePresenter(EditNoteIndicator mEditNoteIndicator, IEditNoteView iEditNoteView) {
        this.mEditNoteIndicator = mEditNoteIndicator;
        this.mIEditNoteView = iEditNoteView;
    }

    @Override
    public void requestListNode() {
        mEditNoteIndicator.getAllNotes(this);
    }

    @Override
    public void requestDeleteNote(Note note) {
        mEditNoteIndicator.deleteNote(note, this);
    }

    @Override
    public void onLoadListNoteFinish(List<Note> arrNote) {
        mIEditNoteView.updateListNote(arrNote);
    }

    @Override
    public void deleteNodeFinish() {
        mIEditNoteView.responeDeleteNote(true);
    }
}
