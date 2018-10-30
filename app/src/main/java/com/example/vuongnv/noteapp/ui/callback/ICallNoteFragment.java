package com.example.vuongnv.noteapp.ui.callback;


import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public interface ICallNoteFragment {
    void updateViewAddNote(Note note);

    void updateViewEditNote(Note note);

    List<Note> getListNote();
}
