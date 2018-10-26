package com.example.vuongnv.noteapp.ui.editnote;

import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.ArrayList;
import java.util.List;

public interface IEditNoteView {
    void updateListNote(List<Note> arr);
    void responeDeleteNote(boolean b);
}
