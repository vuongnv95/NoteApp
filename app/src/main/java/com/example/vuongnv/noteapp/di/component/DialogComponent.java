package com.example.vuongnv.noteapp.di.component;

import com.example.vuongnv.noteapp.di.modul.DialogModul;
import com.example.vuongnv.noteapp.ui.addnote.camera.CameraDialog;
import com.example.vuongnv.noteapp.ui.addnote.color.ColorDialog;

import dagger.Component;

@Component(modules = DialogModul.class)
public interface DialogComponent {
    void inject(CameraDialog cameraDialog);
    void inject(ColorDialog colorDialog);
}
