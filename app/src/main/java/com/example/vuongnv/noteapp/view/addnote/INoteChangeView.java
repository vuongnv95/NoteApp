package com.example.vuongnv.noteapp.view.addnote;


import com.example.vuongnv.noteapp.data.db.model.Note;

public interface INoteChangeView {
    void updateNoteEdit(Note note);
    void updateNoteAdd(Note note);
}
