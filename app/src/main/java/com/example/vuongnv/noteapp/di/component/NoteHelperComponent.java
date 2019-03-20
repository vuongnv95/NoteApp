package com.example.vuongnv.noteapp.di.component;

import com.example.vuongnv.noteapp.data.DataManager;
import com.example.vuongnv.noteapp.di.ApplicationScope;
import com.example.vuongnv.noteapp.di.modul.NoteHelperModul;
import com.example.vuongnv.noteapp.ui.Demo1;

import dagger.Component;
@ApplicationScope
@Component(modules = NoteHelperModul.class)
public interface NoteHelperComponent {
    DataManager provideDataManager();
    Demo1 provideDemo1();
}
