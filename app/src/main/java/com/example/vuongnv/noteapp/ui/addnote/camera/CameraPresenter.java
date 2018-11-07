package com.example.vuongnv.noteapp.ui.addnote.camera;

import com.example.vuongnv.noteapp.ui.base.BaseDialogPresenter;

import javax.inject.Inject;

public class CameraPresenter<V extends CameraView> extends BaseDialogPresenter<V> implements CameraMvpPresenter<V> {

    @Inject
    public CameraPresenter() {
    }

    @Override
    public void requestOpenCamera() {
        getView().openCamera();
    }

    @Override
    public void requestOpenGallery() {
        getView().openGallery();
    }
}
