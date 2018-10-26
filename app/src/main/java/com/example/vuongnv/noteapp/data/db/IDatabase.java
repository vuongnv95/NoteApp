package com.example.vuongnv.noteapp.data.db;


import com.example.vuongnv.noteapp.data.db.model.Note;

import java.util.List;

public interface IDatabase {
    List<Note> getAllNotes();
    int updateNote(Note note);
    void deleteNote(Note note);
    void addNote(Note note);
}
