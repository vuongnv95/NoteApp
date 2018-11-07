package com.example.vuongnv.noteapp.ui.base;

import javax.inject.Inject;

public class BaseDialogPresenter<V extends MvpView> implements MvpDialogPresenter<V> {
    private V view;

    @Inject
    public BaseDialogPresenter() {
    }

    @Override
    public void attachDialog(V view) {
        this.view = view;
    }

    @Override
    public void detachDialog() {
        view = null;
    }

    @Override
    public V getView() {
        return view;
    }

}
