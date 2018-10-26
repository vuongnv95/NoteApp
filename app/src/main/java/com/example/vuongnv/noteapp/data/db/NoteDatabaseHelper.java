package com.example.vuongnv.noteapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    public NoteDatabaseHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseUtils.CRETAE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseUtils.TABLE_NAME);
        onCreate(db);
    }
    public void drop(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseUtils.TABLE_NAME);
        onCreate(db);
    }

    public long addNote(Note note) {
        Log.d("Vuong", "requestAddNote() called with: note = [" + note + "]");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Vuong", "requestAddNote() called with: isOpen = [" + db.isOpen() + "]");
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.COLUMN_NOTE_TITLE, note.getmTitle());
        values.put(DatabaseUtils.COLUMN_NOTE_SUBJECT, note.getmSubject());
        values.put(DatabaseUtils.COLUMN_NOTE_DATE, note.getmDate());
        values.put(DatabaseUtils.COLUMN_NOTE_TIME, note.getmTime());
        values.put(DatabaseUtils.COLUMN_NOTE_TIMESETUP, note.getmSetupTime());
        values.put(DatabaseUtils.COLUMN_NOTE_COLOR, note.getmColor());
        values.put(DatabaseUtils.COLUMN_NOTE_ALARM, note.getmIsAlarm());
        values.put(DatabaseUtils.COLUMN_NOTE_IMAGE, note.getmImageNote());
        long index = db.insert(DatabaseUtils.TABLE_NAME, null, values);
        Log.d("Vuong", "requestAddNote() called with: index = [" + index + "]");
        db.close();
        return index;
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseUtils.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int indexId = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_ID);
        int indexTitle= cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_TITLE);
        int indexSubject = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_SUBJECT);
        int indexDate = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_DATE);
        int indexTime = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_TIME);
        int indexTimeSetup = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_TIMESETUP);
        int indexColor = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_COLOR);
        int indexIsAlarm= cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_ALARM);
        int indexImage = cursor.getColumnIndex(DatabaseUtils.COLUMN_NOTE_IMAGE);
        cursor.moveToFirst();
        Log.d("Vuong", "getCount() called"+cursor.getCount());
        while (! cursor.isAfterLast()){
            Note note = new Note();
            Log.d("Vuong", "titl) called"+cursor.getString(indexTitle));
            note.setmIdNode(cursor.getInt(indexId));
            note.setmTitle(cursor.getString(indexTitle));
            note.setmSubject(cursor.getString(indexSubject));
            note.setmDate(cursor.getString(indexDate));
            note.setmTime(cursor.getString(indexTime));
            note.setmSetupTime(cursor.getString(indexTimeSetup));
            note.setmColor(Integer.parseInt(cursor.getString(indexColor)));
            note.setmIsAlarm(cursor.getInt(indexIsAlarm));
            note.setmImageNote(cursor.getBlob(indexImage));
            noteList.add(note);
            cursor.moveToNext();
        }
        return noteList;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.COLUMN_NOTE_TITLE, note.getmTitle());
        values.put(DatabaseUtils.COLUMN_NOTE_SUBJECT, note.getmSubject());
        values.put(DatabaseUtils.COLUMN_NOTE_DATE, note.getmDate());
        values.put(DatabaseUtils.COLUMN_NOTE_TIME, note.getmTime());
        values.put(DatabaseUtils.COLUMN_NOTE_TIMESETUP, note.getmSetupTime());
        values.put(DatabaseUtils.COLUMN_NOTE_COLOR, note.getmColor());
        values.put(DatabaseUtils.COLUMN_NOTE_ALARM, note.getmIsAlarm());
        values.put(DatabaseUtils.COLUMN_NOTE_IMAGE, note.getmImageNote());

        // updating row
        return db.update(DatabaseUtils.TABLE_NAME, values, DatabaseUtils.COLUMN_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getmIdNode())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseUtils.TABLE_NAME, DatabaseUtils.COLUMN_NOTE_ID + " = ?",
                new String[] { String.valueOf(note.getmIdNode()) });
        db.close();
    }
}
