package com.example.vuongnv.noteapp.view.utils;

public class DatabaseUtils {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NoteManager";
    public static final String TABLE_NAME = "Note";
    public static final String COLUMN_NOTE_ID = "Note_id";
    public static final String COLUMN_NOTE_TITLE = "Note_title";
    public static final String COLUMN_NOTE_SUBJECT = "Note_subject";
    public static final String COLUMN_NOTE_DATE = "Note_datte";
    public static final String COLUMN_NOTE_TIME = "Note_time";
    public static final String COLUMN_NOTE_TIMESETUP = "Note_timesetup";
    public static final String COLUMN_NOTE_COLOR = "Note_color";
    public static final String COLUMN_NOTE_ALARM = "Note_isalarm";
    public static final String COLUMN_NOTE_IMAGE= "Note_image";

    public static final String CRETAE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NOTE_TITLE + " TEXT ,"
            + COLUMN_NOTE_SUBJECT + " TEXT ,"
            + COLUMN_NOTE_DATE + " TEXT ,"
            + COLUMN_NOTE_TIME + " TEXT ,"
            + COLUMN_NOTE_TIMESETUP + " TEXT ,"
            + COLUMN_NOTE_COLOR + " INTEGER ,"
            + COLUMN_NOTE_ALARM + " INTEGER ,"
            + COLUMN_NOTE_IMAGE + " BLOB"+ ")";
}
