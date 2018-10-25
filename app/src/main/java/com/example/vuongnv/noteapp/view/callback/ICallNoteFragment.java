package com.example.vuongnv.noteapp.view.callback;


import com.example.vuongnv.noteapp.model.Note;

import java.util.List;

public interface ICallNoteFragment {
    void updateViewAddNote(Note note);
    void updateViewEditNote(Note note);
    List<Note> getListNote();
}
