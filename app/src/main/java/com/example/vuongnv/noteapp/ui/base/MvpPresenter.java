package com.example.vuongnv.noteapp.ui.base;

import com.example.vuongnv.noteapp.data.db.DataManager;

public interface MvpPresenter<V extends MvpView> {
    void atttachView(V view);
    void detachView();
    boolean isDetachView();
    V getView();
    DataManager getDataManager();
}
