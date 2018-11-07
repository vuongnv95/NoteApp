package com.example.vuongnv.noteapp.ui.base;

import com.example.vuongnv.noteapp.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;

    private DataManager mNoteDataManager;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(DataManager mNoteDataManager, CompositeDisposable mCompositeDisposable) {
        this.mNoteDataManager = mNoteDataManager;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    @Override
    public void atttachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
        mCompositeDisposable.dispose();
    }

    public DataManager getmNoteDataManager() {
        return mNoteDataManager;
    }

    @Override
    public boolean isDetachView() {
        return mvpView == null;
    }

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public V getView() {
        return mvpView;
    }

    @Override
    public DataManager getDataManager() {
        return mNoteDataManager;
    }
}
