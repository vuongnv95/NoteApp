package com.example.vuongnv.noteapp.ui.activitis;

import android.app.Activity;
import android.os.Bundle;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.DatabaseManager;

import java.util.ArrayList;

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager databaseManager = new DatabaseManager(this);
        ArrayList<byte[]> arr = new ArrayList<>();
    }
}
