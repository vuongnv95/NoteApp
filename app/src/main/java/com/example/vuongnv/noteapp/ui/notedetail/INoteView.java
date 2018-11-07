package com.example.vuongnv.noteapp.ui.notedetail;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.MvpView;

import java.util.List;

public interface INoteView extends MvpView {
    void updateAllNotes(List<Note> arrNote);
}
