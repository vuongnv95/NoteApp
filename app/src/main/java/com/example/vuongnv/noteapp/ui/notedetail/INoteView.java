package com.example.vuongnv.noteapp.ui.notedetail;


import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public interface INoteView {
    void updateAllNotes(List<Note> arrNote);
}