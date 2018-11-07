package com.example.vuongnv.noteapp.ui.activitis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.ui.callback.ICallBackEditNoteI;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteFragment;
import com.example.vuongnv.noteapp.ui.editnote.EditNoteFragment;
import com.example.vuongnv.noteapp.ui.notedetail.NoteFragment;
import com.example.vuongnv.noteapp.utils.NoteUtils;


public class MainActivity extends AppCompatActivity implements ICallBackEditNoteI, ICallBackAddNote, NoteFragment.CallBackNoteFragment {
    private final int CODE_PERMISTION = 101;
    private final long TIME_DELAY = 2000;
    private int mPositionNote;

    private int mFlagFragment;
    private FragmentManager mFragmentManager;
    private AddNoteFragment mAddNoteFragment;
    private NoteFragment mNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            registerPermistion();
            createNoteDetailFragment();
        } else {
            switch (savedInstanceState.getInt("fragmentdtate")) {
                case NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT:
                    createNoteDetailFragment();
                    break;
                case NoteUtils.FLAG_ADD_FRAGMENT:
                    createAddFragment();
                    break;
                case NoteUtils.FLAG_EDIT_FRAGMENT:
                    createEditFragment(savedInstanceState.getInt("position"));
                    break;
            }
        }
    }

    private void createNoteDetailFragment() {
        mFlagFragment = NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT;
        mFragmentManager = getSupportFragmentManager();
        mNoteFragment = new NoteFragment(this);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mNoteFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mPositionNote);
        outState.putInt("fragmentdtate", mFlagFragment);
    }

    private void registerPermistion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_PERMISTION);
    }

    private boolean checkIfAlreadyHavePermission() {
        int resultReadExternal = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int resultWriteExternal = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (resultReadExternal == PackageManager.PERMISSION_GRANTED && resultWriteExternal == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("Vuong", "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + permissions[0] + ":" + permissions[1] + "], grantResults = [" + grantResults[0] + ":"+ grantResults[1] + "]");
        switch (requestCode) {
            case CODE_PERMISTION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    Toast.makeText(this, "You are register permistion!", Toast.LENGTH_LONG).show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, TIME_DELAY);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void createAddFragment() {
        mFlagFragment = NoteUtils.FLAG_ADD_FRAGMENT;
        mAddNoteFragment = new AddNoteFragment(this, null, mFlagFragment);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, mAddNoteFragment).addToBackStack("add").commit();
    }

    @Override
    public void createEditFragment(int position) {
        this.mPositionNote = position;
        mFlagFragment = NoteUtils.FLAG_EDIT_FRAGMENT;
        mFragmentManager.beginTransaction().replace(R.id.fl_main, new EditNoteFragment( this, position)).addToBackStack("edit").commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        mFlagFragment = NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT;
        mFragmentManager.popBackStack();
    }

    @Override
    public void clickBtnBack() {
        mFlagFragment = NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT;
        mFragmentManager.popBackStack();
    }

    @Override
    public void popFragmentBackStack(Note note) {
        mFlagFragment = NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT;
        mFragmentManager.popBackStack();
    }

    @Override
    public void clickBtnMore() {
        mFlagFragment = NoteUtils.FLAG_NOTE_DETAIL_FRAGMENT;
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
