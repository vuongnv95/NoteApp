package com.example.vuongnv.noteapp.ui.activitis;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.ui.callback.ICallBackEditNoteI;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteFragment;
import com.example.vuongnv.noteapp.ui.editnote.EditNoteFragment;
import com.example.vuongnv.noteapp.ui.notedetail.NoteFragment;
import com.example.vuongnv.noteapp.utils.NoteUtils;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ICallBackEditNoteI, ICallBackAddNote, NoteFragment.CallBackNoteFragment {
    private int mFlagFragment;

    private FragmentManager mFragmentManager;
    private AddNoteFragment mAddNoteFragment;
    private NoteFragment mNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mNoteFragment = new NoteFragment(this);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mNoteFragment).commit();
    }

    @Override
    public void createAddFragment() {
        mFlagFragment = NoteUtils.FLAG_ADD_FRAGMENT;
        mAddNoteFragment = new AddNoteFragment(this,null,mFlagFragment);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mAddNoteFragment).addToBackStack("add").commit();
    }

    @Override
    public void createEditFragment(ArrayList<Note> arrNote, int position) {
        mFlagFragment = NoteUtils.FLAG_EDIT_FRAGMENT;
        mFragmentManager.beginTransaction().replace(R.id.fl_main, new EditNoteFragment(this,this,position)).addToBackStack("edit").commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        mFragmentManager.popBackStack();
    }

    @Override
    public void clickBtnBack() {
        mFragmentManager.popBackStack();
    }

    @Override
    public void clickBtnAdd(Note note) {
        mFragmentManager.popBackStack();
    }

    @Override
    public void clickBtnMore() {
        mFragmentManager.popBackStack();
        createAddFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
