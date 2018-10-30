package com.example.vuongnv.noteapp.ui.editnote;

import com.example.vuongnv.noteapp.data.db.model.Note;

public interface EditNoteMVPPresenter {
    void requestListNode();

    void requestDeleteNote(Note note);
}
