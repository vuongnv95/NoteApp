package com.example.vuongnv.noteapp.ui.base;

public interface MvpDialogPresenter<V extends MvpView> {
    void attachDialog(V View);

    void detachDialog();

    V getView();
}
