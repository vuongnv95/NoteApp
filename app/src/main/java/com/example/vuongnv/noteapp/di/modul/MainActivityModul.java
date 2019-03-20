package com.example.vuongnv.noteapp.di.modul;

import android.content.Context;

import com.example.vuongnv.noteapp.di.ActivityContext;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteMVPPresenter;
import com.example.vuongnv.noteapp.ui.addnote.AddNotePresenter;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteView;
import com.example.vuongnv.noteapp.ui.editnote.EditNoteMVPPresenter;
import com.example.vuongnv.noteapp.ui.editnote.EditNotePresenter;
import com.example.vuongnv.noteapp.ui.editnote.EditNoteView;
import com.example.vuongnv.noteapp.ui.notedetail.INoteView;
import com.example.vuongnv.noteapp.ui.notedetail.NoteMVPPresenter;
import com.example.vuongnv.noteapp.ui.notedetail.NotePresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = ActivityContextModul.class)
public class MainActivityModul {
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    NoteMVPPresenter<INoteView> provideNoteMVPPresenter(NotePresenter<INoteView> viewNotePresenter) {
        return viewNotePresenter;
    }

    @Provides
    AddNoteMVPPresenter<AddNoteView> provideAddNoteMVPPresenter(AddNotePresenter<AddNoteView> addNotePresenter) {
        return addNotePresenter;
    }

    @Provides
    EditNoteMVPPresenter<EditNoteView> provideEditNoteMVPPresenter(EditNotePresenter<EditNoteView> editNotePresenter) {
        return editNotePresenter;
    }

    @Provides
    Context provideContext(@ActivityContext Context context) {
        return context;
    }
}
