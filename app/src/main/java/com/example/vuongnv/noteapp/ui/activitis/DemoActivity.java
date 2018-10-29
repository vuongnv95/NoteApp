package com.example.vuongnv.noteapp.ui.activitis;

import android.app.Activity;
import android.os.Bundle;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.NoteDatabaseManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NoteDatabaseManager databaseManager = new NoteDatabaseManager(this);
//        List<NoteImage> arr = new ArrayList<>();
//        arr.add(new NoteImage(1,"a"));
//        arr.add(new NoteImage(2,"b"));
//        arr.add(new NoteImage(1,"c"));
//        arr.add(new NoteImage(1,"d"));
//        arr.add(new NoteImage(1,"e"));
//        databaseManager.addNoteImage(arr);
//        Note note = new Note();
//        note.setmIdNode(1);
//        databaseManager.getAllNoteImages(note);
//        databaseManager.deleteNoteImages(note);
//        databaseManager.getAllNoteImages(note);
//        arr.remove(2);
//        arr.remove(1);
//        databaseManager.updateNoteImage(arr);
//        databaseManager.getAllNoteImages(note);
    }
}
