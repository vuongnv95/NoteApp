package com.example.vuongnv.noteapp.model;

import java.io.Serializable;

public class Note implements Serializable {
    private int mIdNode;
    private String mTitle;
    private String mSubject;
    private String mDate;
    private String mTime;
    private int mColor;
    private byte[] mImageNote;

    public Note() {
    }

    public Note(String mTitle, String mSubject, String mDate, String mTime, int mColor) {
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mColor = mColor;
    }

    public Note(String mTitle, String mSubject, String mDate, String mTime, int mColor, byte[] mImageNote) {
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mColor = mColor;
        this.mImageNote = mImageNote;
    }

    public Note(int mIdNode, String mTitle, String mSubject, String mDate, String mTime, int mColor, byte[] mImageNote) {
        this.mIdNode = mIdNode;
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mColor = mColor;
        this.mImageNote = mImageNote;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public byte[] getmImageNote() {
        return mImageNote;
    }

    public void setmImageNote(byte[] mImageNote) {
        this.mImageNote = mImageNote;
    }

    public int getmIdNode() {
        return mIdNode;
    }

    public void setmIdNode(int mIdNode) {
        this.mIdNode = mIdNode;
    }
}
