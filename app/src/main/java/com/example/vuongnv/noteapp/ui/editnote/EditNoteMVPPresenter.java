package com.example.vuongnv.noteapp.ui.editnote;

import android.view.View;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.MvpPresenter;
import com.example.vuongnv.noteapp.ui.base.MvpView;

public interface EditNoteMVPPresenter<V extends MvpView> extends MvpPresenter<V> {
    void requestListNode();

    void requestDeleteNote(Note note);

    void requestClickBtnBack(int position);

    void requestClickBtnShare();

    void requestClickBtnDelete();

    void requestClickBtnNext(int position);
}
