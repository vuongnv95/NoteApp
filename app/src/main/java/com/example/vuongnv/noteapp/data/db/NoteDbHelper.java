package com.example.vuongnv.noteapp.data.db;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.utils.DatabaseUtils;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class NoteDbHelper implements DbHelper {

    private Context mContext;
    private NoteDatabaseHelper mNoteDatabase;
    private NoteImageDatabaseHelper mNoteImageDatabaseHelper;
    private static NoteDbHelper instance = null;

    public static NoteDbHelper getInstance(Context context) {
        if (instance == null)
            instance = new NoteDbHelper(context);

        return instance;
    }

    public NoteDbHelper(Context context) {
        this.mContext = context;
        mNoteDatabase = new NoteDatabaseHelper(mContext, DatabaseUtils.DATABASE_NAME, null, DatabaseUtils.DATABASE_VERSION);
        mNoteImageDatabaseHelper = new NoteImageDatabaseHelper(mContext, DatabaseUtils.DATABASE_NAME_NOTE_IMAGE, null, DatabaseUtils.DATABASE_VERSION);
    }

    @Override
    public Observable<List<Note>> getAllNotes() {
        return Observable.fromCallable(new Callable<List<Note>>() {
            @Override
            public List<Note> call() throws Exception {
                return mNoteDatabase.getAllNotes();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Observable<List<NoteImage>> getAllNoteImages(final Note note) {
        return Observable.fromCallable(new Callable<List<NoteImage>>() {
            @Override
            public List<NoteImage> call() throws Exception {
                return mNoteImageDatabaseHelper.getAllImageOfNote(note);
            }
        });
    }

    @Override
    public Observable<Integer> updateNote(final Note note) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mNoteDatabase.updateNote(note);
            }
        });
    }

    @Override
    public Observable<Long> updateNoteImage(final Note note, final List<NoteImage> arrNoteImage) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mNoteImageDatabaseHelper.updateAllNoteImage(note, arrNoteImage);
            }
        });
    }

    @Override
    public Observable<Integer> deleteNote(final Note note) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mNoteDatabase.deleteNote(note);
            }
        });
    }

    @Override
    public Observable<Integer> deleteNoteImages(final Note note) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return  mNoteImageDatabaseHelper.deleteAllNoteImage(note.getmIdNode());
            }
        });
    }

    @Override
    public Observable<Long> addNote(final Note note) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return  mNoteDatabase.addNote(note);
            }
        });
    }

    @Override
    public Observable<Long> addNoteImage(final Note note, final List<NoteImage> arrNoteImage) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mNoteImageDatabaseHelper.addNoteImages(note, arrNoteImage);
            }
        });
    }
}
