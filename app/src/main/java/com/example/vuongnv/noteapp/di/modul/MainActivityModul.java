package com.example.vuongnv.noteapp.di.modul;

import android.content.Context;

import com.example.vuongnv.noteapp.data.db.DataManager;
import com.example.vuongnv.noteapp.data.db.NoteDataManager;
import com.example.vuongnv.noteapp.data.db.NoteDbHelper;
import com.example.vuongnv.noteapp.di.NoteDBhelper;
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

@Module
public class MainActivityModul {
    private Context mContext;

    public MainActivityModul(Context mContext) {
        this.mContext = mContext;
    }

    @NoteDBhelper
    @Provides
    NoteDbHelper provideNoteDbHelper(){
        return new NoteDbHelper(this.mContext);
    }

    @Provides
    DataManager provideDataManager(NoteDataManager noteDataManager){
        return noteDataManager;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    NoteMVPPresenter<INoteView> provideNoteMVPPresenter(NotePresenter<INoteView> viewNotePresenter){
        return viewNotePresenter;
    }

    @Provides
    AddNoteMVPPresenter<AddNoteView> provideAddNoteMVPPresenter(AddNotePresenter<AddNoteView> addNotePresenter){
        return addNotePresenter;
    }

    @Provides
    EditNoteMVPPresenter<EditNoteView> provideEditNoteMVPPresenter(EditNotePresenter<EditNoteView> editNotePresenter){
        return editNotePresenter;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
