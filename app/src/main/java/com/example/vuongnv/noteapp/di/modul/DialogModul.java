package com.example.vuongnv.noteapp.di.modul;

import com.example.vuongnv.noteapp.ui.addnote.camera.CameraMvpPresenter;
import com.example.vuongnv.noteapp.ui.addnote.camera.CameraPresenter;
import com.example.vuongnv.noteapp.ui.addnote.camera.CameraView;
import com.example.vuongnv.noteapp.ui.addnote.color.ColorMvpPresenter;
import com.example.vuongnv.noteapp.ui.addnote.color.ColorPresenter;
import com.example.vuongnv.noteapp.ui.addnote.color.ColorView;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogModul {

    @Provides
    CameraMvpPresenter<CameraView> provideMvpPresenter(CameraPresenter<CameraView> cameraPresenter) {
        return cameraPresenter;
    }

    @Provides
    ColorMvpPresenter<ColorView> provideColorMvpPresenter(ColorPresenter<ColorView> colorPresenter) {
        return colorPresenter;
    }
}
