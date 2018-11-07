package com.example.vuongnv.noteapp.ui.notedetail;

import com.example.vuongnv.noteapp.ui.base.MvpPresenter;

public interface NoteMVPPresenter<V extends INoteView> extends MvpPresenter<V> {
    void getAllNotes();
}
