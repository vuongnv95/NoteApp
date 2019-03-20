package com.example.vuongnv.noteapp.di.modul;

import android.content.Context;

import com.example.vuongnv.noteapp.data.DataManager;
import com.example.vuongnv.noteapp.data.NoteDataManager;
import com.example.vuongnv.noteapp.data.db.NoteDbHelper;
import com.example.vuongnv.noteapp.di.ApplicationContext;
import com.example.vuongnv.noteapp.di.ApplicationScope;
import com.example.vuongnv.noteapp.ui.Demo1;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationContextModul.class)
public class NoteHelperModul {

    @ApplicationScope
    @Provides
    NoteDbHelper provideNoteDbHelper(@ApplicationContext Context context) {
        return new NoteDbHelper(context);
    }

    @Provides
    @ApplicationScope
    DataManager provideDataManager(NoteDataManager noteDataManager) {
        return noteDataManager;
    }
    @Provides
    @ApplicationScope
    Demo1 provideDemo1() {
        return new Demo1("Vuong");
    }
}
