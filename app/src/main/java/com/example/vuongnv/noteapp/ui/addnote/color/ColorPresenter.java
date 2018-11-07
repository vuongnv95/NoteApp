package com.example.vuongnv.noteapp.ui.addnote.color;

import com.example.vuongnv.noteapp.ui.base.BaseDialogPresenter;

import javax.inject.Inject;

public class ColorPresenter<V extends ColorView> extends BaseDialogPresenter<V> implements ColorMvpPresenter<V> {

    @Inject
    public ColorPresenter() {
    }

    @Override
    public void requestBtnYellow() {
        getView().updateColorYellow();
    }

    @Override
    public void requestBtnGreen() {
        getView().updateColorGreen();
    }

    @Override
    public void requestBtnLight() {
        getView().updateColorLight();
    }

    @Override
    public void requestBtnWhite() {
        getView().updateColorWhite();
    }
}
