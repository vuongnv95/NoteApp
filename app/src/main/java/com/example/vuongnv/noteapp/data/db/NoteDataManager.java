package com.example.vuongnv.noteapp.data.db;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NoteDataManager implements DataManager {
    private NoteDbHelper mNoteDbHelper;

    @Inject
    public NoteDataManager(NoteDbHelper mNoteDbHelper) {
        this.mNoteDbHelper = mNoteDbHelper;
    }

    @Override
    public Observable<List<Note>> getAllNotes() {
        return this.mNoteDbHelper.getAllNotes();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Observable<List<NoteImage>> getAllNoteImages(Note note) {
        return this.mNoteDbHelper.getAllNoteImages(note);
    }

    @Override
    public Observable<Integer> updateNote(Note note) {
        return this.mNoteDbHelper.updateNote(note);
    }

    @Override
    public Observable<Long> updateNoteImage(Note note, List<NoteImage> arrNoteImage) {
        return this.mNoteDbHelper.updateNoteImage(note,arrNoteImage);
    }

    @Override
    public Observable<Integer> deleteNote(Note note) {
        return this.mNoteDbHelper.deleteNote(note);
    }

    @Override
    public Observable<Integer> deleteNoteImages(Note note) {
        return  this.mNoteDbHelper.deleteNoteImages(note);
    }

    @Override
    public Observable<Long> addNote(Note note) {
        return this.mNoteDbHelper.addNote(note);
    }

    @Override
    public Observable<Long> addNoteImage(Note note, List<NoteImage> arrNoteImage) {
        return this.mNoteDbHelper.addNoteImage(note,arrNoteImage);
    }
}
