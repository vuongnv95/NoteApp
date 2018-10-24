package com.example.vuongnv.noteapp.view.activitis;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.model.Note;
import com.example.vuongnv.noteapp.view.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.view.callback.ICallBackEditNoteI;
import com.example.vuongnv.noteapp.view.fragment.AddNoteFragment;
import com.example.vuongnv.noteapp.view.fragment.EditNoteFragment;
import com.example.vuongnv.noteapp.view.fragment.NoteFragment;
import com.example.vuongnv.noteapp.view.utils.NoteUtils;


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
    public void clickButtonAdd() {
        mFlagFragment = NoteUtils.FLAG_ADD_FRAGMENT;
        mAddNoteFragment = new AddNoteFragment(this, null,mFlagFragment);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mAddNoteFragment).addToBackStack("add").commit();
    }

    @Override
    public void clickItemGridView(Note note) {
        mFlagFragment = NoteUtils.FLAG_EDIT_FRAGMENT;
        mAddNoteFragment = new AddNoteFragment(this, note,mFlagFragment);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, new EditNoteFragment(this,this,note)).addToBackStack("edit").commit();
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
        switch (mFlagFragment) {
            case NoteUtils.FLAG_ADD_FRAGMENT:
                mNoteFragment.updateViewAddNote(note);
                break;
            case NoteUtils.FLAG_EDIT_FRAGMENT:
                mNoteFragment.updateViewEditNote(note);
                break;
            default:
                break;
        }
    }
}
