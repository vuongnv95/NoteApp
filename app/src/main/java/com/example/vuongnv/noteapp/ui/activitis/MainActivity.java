package com.example.vuongnv.noteapp.ui.activitis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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
//        registerPermistion();
        mFragmentManager = getSupportFragmentManager();
        mNoteFragment = new NoteFragment(this);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mNoteFragment).commit();
    }

    private void registerPermistion() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1){
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS, Manifest.permission.CAMERA}, 101);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
