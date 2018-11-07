package com.example.vuongnv.noteapp.ui.notedetail;


import com.example.vuongnv.noteapp.data.db.DataManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class NotePresenter<V extends INoteView> extends BasePresenter<V> implements NoteMVPPresenter<V> {

    @Inject
    public NotePresenter(DataManager mNoteDataManager, CompositeDisposable mCompositeDisposable) {
        super(mNoteDataManager, mCompositeDisposable);
    }

    @Override
    public void getAllNotes() {
        getDataManager().getAllNotes().subscribe(new Consumer<List<Note>>() {
            @Override
            public void accept(List<Note> notes) throws Exception {
                getView().updateAllNotes(notes);
            }
        });
    }
}
