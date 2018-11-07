package com.example.vuongnv.noteapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import android.support.annotation.RequiresApi;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;

public class NoteImageDatabaseHelper extends SQLiteOpenHelper {
    public NoteImageDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseUtils.CRETAE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseUtils.TABLE_IMAGE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<NoteImage> getAllImageOfNote(Note note) {
        List<NoteImage> noteImageList = new ArrayList<>();
        // Select All Query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DatabaseUtils.QUERY_GETALL_NOTE_IMAGE, new String[]{String.valueOf(note.getmIdNode())}, null);
        int indexImagePathId = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_IMAGE_ID);
        int indexNoteId = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_ID);
        int indexImagePath = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_IMAGE_PATH);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NoteImage noteImage = new NoteImage();
            noteImage.setmNoteId(cursor.getInt(indexImagePathId));
            noteImage.setmNoteId(indexNoteId);
            noteImage.setmPath(cursor.getString(indexImagePath));
            noteImageList.add(noteImage);
            cursor.moveToNext();
        }
        return noteImageList;
    }

    public long updateAllNoteImage(Note note, List<NoteImage> arrNoteImage) {
        deleteAllNoteImage(note.getmIdNode());
        long index = addNoteImages(note, arrNoteImage);
        return index;
    }

    public long addNoteImages(Note note, List<NoteImage> arrNoteImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        long index = 0;
        for (NoteImage noteImage : arrNoteImage) {
            ContentValues values = new ContentValues();
            values.put(DatabaseUtils.COLUMN_NOTE_ID, note.getmIdNode());
            values.put(DatabaseUtils.COLUMN_NOTE_IMAGE_PATH, noteImage.getmPath());
            index = db.insert(DatabaseUtils.TABLE_IMAGE_NAME, null, values);
        }
        db.close();
        return index;
    }

    public int deleteAllNoteImage(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int index = db.delete(DatabaseUtils.TABLE_IMAGE_NAME, DatabaseUtils.COLUMN_NOTE_ID + " = ?", new String[]{String.valueOf(noteId)});
        db.close();
        return index;
    }
}
