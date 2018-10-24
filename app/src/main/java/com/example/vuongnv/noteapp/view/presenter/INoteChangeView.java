package com.example.vuongnv.noteapp.view.presenter;


import com.example.vuongnv.noteapp.model.Note;

public interface INoteChangeView {
    void updateNoteEdit(Note note);
    void updateNoteAdd(Note note);
}
