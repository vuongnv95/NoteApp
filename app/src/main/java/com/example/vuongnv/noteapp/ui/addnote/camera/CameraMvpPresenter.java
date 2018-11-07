package com.example.vuongnv.noteapp.ui.addnote.camera;

import com.example.vuongnv.noteapp.ui.base.BasePresenter;
import com.example.vuongnv.noteapp.ui.base.MvpDialogPresenter;
import com.example.vuongnv.noteapp.ui.base.MvpPresenter;
import com.example.vuongnv.noteapp.ui.base.MvpView;

public interface CameraMvpPresenter <V extends MvpView> extends MvpDialogPresenter<V> {
    void requestOpenCamera();
    void requestOpenGallery();
}
