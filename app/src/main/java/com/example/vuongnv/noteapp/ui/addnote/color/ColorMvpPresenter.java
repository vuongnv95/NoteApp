package com.example.vuongnv.noteapp.ui.addnote.color;

import com.example.vuongnv.noteapp.ui.base.MvpDialogPresenter;

public interface ColorMvpPresenter<V extends ColorView> extends MvpDialogPresenter<V> {
    void requestBtnYellow();

    void requestBtnGreen();

    void requestBtnLight();

    void requestBtnWhite();
}
