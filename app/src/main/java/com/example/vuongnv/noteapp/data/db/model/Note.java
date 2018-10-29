package com.example.vuongnv.noteapp.data.db.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Note implements Serializable {
    private int mIdNode;
    private String mTitle;
    private String mSubject;
    private String mDate;
    private String mTime;
    private String mSetupTime;
    private int mColor;
    private int mIsAlarm;
    private ArrayList<String> mImagePath;

    public Note() {
    }

    public Note(String mTitle, String mSubject, String mDate, String mTime, int mColor) {
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mColor = mColor;
    }

    public Note(String mTitle, String mSubject, String mDate, String mTime, String mSetupTime, int mColor, int mAlarm,ArrayList<String> mImagePath) {
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mSetupTime = mSetupTime;
        this.mColor = mColor;
        this.mIsAlarm = mAlarm;
        this.mImagePath = mImagePath;
    }

    public Note(int mIdNode, String mTitle, String mSubject, String mDate, String mTime, int mColor, ArrayList<String> mImagePath) {
        this.mIdNode = mIdNode;
        this.mTitle = mTitle;
        this.mSubject = mSubject;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mColor = mColor;
        this.mImagePath = mImagePath;
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

    public ArrayList<String> getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(ArrayList<String> mImagePath) {
        this.mImagePath = mImagePath;
    }

    public int getmIdNode() {
        return mIdNode;
    }

    public void setmIdNode(int mIdNode) {
        this.mIdNode = mIdNode;
    }

    public String getmSetupTime() {
        return mSetupTime;
    }

    public void setmSetupTime(String mSetupTime) {
        this.mSetupTime = mSetupTime;
    }

    public int getmIsAlarm() {
        return mIsAlarm;
    }

    public void setmIsAlarm(int mAlarm) {
        this.mIsAlarm = mAlarm;
    }
}
