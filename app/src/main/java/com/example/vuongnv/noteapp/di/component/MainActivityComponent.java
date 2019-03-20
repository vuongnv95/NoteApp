package com.example.vuongnv.noteapp.di.component;

import com.example.vuongnv.noteapp.di.ActivityScope;
import com.example.vuongnv.noteapp.di.ApplicationScope;
import com.example.vuongnv.noteapp.di.modul.MainActivityModul;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteFragment;
import com.example.vuongnv.noteapp.ui.editnote.EditNoteFragment;
import com.example.vuongnv.noteapp.ui.notedetail.NoteFragment;

import dagger.Component;

@ActivityScope
@Component(modules = MainActivityModul.class,dependencies = NoteHelperComponent.class)
public interface MainActivityComponent {
    void inject(NoteFragment noteFragment);

    void inject(AddNoteFragment addNoteFragment);

    void inject(EditNoteFragment editNoteFragment);
}
