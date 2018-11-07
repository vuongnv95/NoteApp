package com.example.vuongnv.noteapp.data;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {
    Observable<List<Note>> getAllNotes();

    Observable<List<NoteImage>> getAllNoteImages(Note note);

    Observable<Integer> updateNote(Note note);

    Observable<Long> updateNoteImage(Note note, List<NoteImage> arrNoteImage);

    Observable<Integer> deleteNote(Note note);

    Observable<Integer> deleteNoteImages(Note note);

    Observable<Long> addNote(Note note);

    Observable<Long> addNoteImage(Note note, List<NoteImage> arrNoteImage);
}
