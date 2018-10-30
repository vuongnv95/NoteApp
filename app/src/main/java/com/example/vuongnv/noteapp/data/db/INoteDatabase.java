package com.example.vuongnv.noteapp.data.db;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;

public interface INoteDatabase {
    List<Note> getAllNotes();

    List<NoteImage> getAllNoteImages(Note note);

    int updateNote(Note note);

    void updateNoteImage(Note note, List<NoteImage> arrNoteImage);

    void deleteNote(Note note);

    void deleteNoteImages(Note note);

    long addNote(Note note);

    void addNoteImage(Note note, List<NoteImage> arrNoteImage);
}
