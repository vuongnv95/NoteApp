package com.example.vuongnv.noteapp.utils;

public class DatabaseUtils {

    //table note
    public static final int DATABASE_VERSION = 1;
    public static final String SQL_DROP = "DROP TABLE IF EXISTS ";
    public static final String DATABASE_NAME = "NoteManager";
    public static final String DATABASE_NAME_NOTE_IMAGE = "NoteImageManager";
    public static final String TABLE_NAME = "Note";
    public static final String COLUMN_NOTE_ID = "Note_id";
    public static final String COLUMN_NOTE_TITLE = "Note_title";
    public static final String COLUMN_NOTE_SUBJECT = "Note_subject";
    public static final String COLUMN_NOTE_DATE = "Note_datte";
    public static final String COLUMN_NOTE_TIME = "Note_time";
    public static final String COLUMN_NOTE_TIMESETUP = "Note_timesetup";
    public static final String COLUMN_NOTE_COLOR = "Note_color";
    public static final String COLUMN_NOTE_ALARM = "Note_isalarm";
    public static final String COLUMN_NOTE_IMAGE = "Note_image";

    //table image
    public static final String TABLE_IMAGE_NAME = "NoteImage";
    public static final String COLUMN_NOTE_IMAGE_ID = "NoteImage_id";
    public static final String COLUMN_NOTE_IMAGE_PATH = "NoteImage_path";

    //query
    public static final String QUERY_GETALL_NOTE = "SELECT  * FROM " + DatabaseUtils.TABLE_NAME;
    public static final String QUERY_GETALL_NOTE_IMAGE = "SELECT  * FROM " + DatabaseUtils.TABLE_IMAGE_NAME + " WHERE " + COLUMN_NOTE_ID + " = ? ";

    public static final String CRETAE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NOTE_TITLE + " TEXT ,"
            + COLUMN_NOTE_SUBJECT + " TEXT ,"
            + COLUMN_NOTE_DATE + " TEXT ,"
            + COLUMN_NOTE_TIME + " TEXT ,"
            + COLUMN_NOTE_TIMESETUP + " TEXT ,"
            + COLUMN_NOTE_COLOR + " INTEGER ,"
            + COLUMN_NOTE_ALARM + " INTEGER )";

    public static final String CRETAE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE_NAME + "("
            + COLUMN_NOTE_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NOTE_ID + " INTEGER ,"
            + COLUMN_NOTE_IMAGE_PATH + " TEXT )";
}
