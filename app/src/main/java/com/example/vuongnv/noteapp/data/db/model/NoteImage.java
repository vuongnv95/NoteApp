package com.example.vuongnv.noteapp.data.db.model;

public class NoteImage {
    private int mNoteImageId;
    private int mNoteId;
    private String mPath;

    public NoteImage() {
    }

    public NoteImage(int mNoteId, String mPath) {
        this.mNoteId = mNoteId;
        this.mPath = mPath;
    }

    public int getmNoteImageId() {
        return mNoteImageId;
    }

    public void setmNoteImageId(int mNoteImageId) {
        this.mNoteImageId = mNoteImageId;
    }

    public int getmNoteId() {
        return mNoteId;
    }

    public void setmNoteId(int mNoteId) {
        this.mNoteId = mNoteId;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }
}
