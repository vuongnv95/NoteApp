package com.example.vuongnv.noteapp.ui.editnote;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.addnote.AddNoteFragment;
import com.example.vuongnv.noteapp.ui.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.ui.callback.ICallBackEditNoteI;
import com.example.vuongnv.noteapp.ui.dialog.PopupBottomDialog;
import com.example.vuongnv.noteapp.utils.AlarmUtils;
import com.example.vuongnv.noteapp.utils.NoteUtils;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class EditNoteFragment extends Fragment implements View.OnClickListener, IEditNoteView, PopupBottomDialog.OnDialogMenuListener {
    private static final String TAG = EditNoteFragment.class.getSimpleName();
    private final int NEXT_INDEX = 1;
    private final int FIRST_INDEX = 0;

    private Note mNote;

    //view
    private ImageView mIvNaviBack;
    private ImageView mIvNaviShare;
    private ImageView mIvNaviDelete;
    private ImageView mIvNaviNext;

    //interface
    private ICallBackEditNoteI mCallBackEditNote;
    private ICallBackAddNote mICallBackAddNote;

    //
    private AddNoteFragment mAddNoteFragment;

    //dialog
    private PopupBottomDialog mPopupBottomDialog;
    //
    private ArrayList<Note> mArrNote;
    private int mPosition;

    //mvp
    private EditNotePresenter mEditNotePresenter;

    public EditNoteFragment(ICallBackEditNoteI callBackEditNote, ICallBackAddNote iCallBackAddNote, int position) {
        this.mCallBackEditNote = callBackEditNote;
        this.mICallBackAddNote = iCallBackAddNote;
        this.mPosition = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editnote, container, false);
        initMVP();
        initData();
        initView(view);
        setOnclick();
        setData();
        return view;
    }

    private void initMVP() {
        EditNoteIndicator editNoteIndicator = new EditNoteIndicator(getContext());
        mEditNotePresenter = new EditNotePresenter(editNoteIndicator, this);
    }

    private void initView(View view) {
        mAddNoteFragment = new AddNoteFragment(mICallBackAddNote, mNote, NoteUtils.FLAG_EDIT_FRAGMENT);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_editnote_addnote, mAddNoteFragment).commit();
        mIvNaviBack = view.findViewById(R.id.iv_editnote_bottom_back);
        mIvNaviShare = view.findViewById(R.id.iv_editnote_bottom_share);
        mIvNaviDelete = view.findViewById(R.id.iv_editnote_bottom_delete);
        mIvNaviNext = view.findViewById(R.id.iv_editnote_bottom_next);
    }

    private void setOnclick() {
        mIvNaviBack.setOnClickListener(this);
        mIvNaviShare.setOnClickListener(this);
        mIvNaviDelete.setOnClickListener(this);
        mIvNaviNext.setOnClickListener(this);
    }

    private void initData() {
        this.mArrNote = new ArrayList<>();
        mEditNotePresenter.requestListNode();
        this.mNote = this.mArrNote.get(this.mPosition);
    }

    private void setData() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_editnote_bottom_back:
                clickBtnNaviBack();
                break;
            case R.id.iv_editnote_bottom_share:
                clickBtnNaviShare();
                break;
            case R.id.iv_editnote_bottom_delete:
                clickBtnNaviDelete(mArrNote.get(mPosition));
                break;
            case R.id.iv_editnote_bottom_next:
                clickBtnNaviNext();
                break;
            default:
                Log.d(TAG, "onClick() called with: v = [" + v + "]");
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void clickBtnNaviBack() {
        Log.d(TAG, "clickBtnNaviBack() called");
        mPosition -= NEXT_INDEX;
        mIvNaviNext.setImageResource(R.drawable.ic_next);
        if (mPosition >= FIRST_INDEX) {
            mNote = mArrNote.get(mPosition);
            mAddNoteFragment.setData(mNote);
            if (mPosition == FIRST_INDEX) {
                mIvNaviBack.setImageResource(R.drawable.ic_back_hiden);
            }
        } else {
            mPosition += NEXT_INDEX;
        }
    }

    private void clickBtnNaviShare() {
        Log.d(TAG, "clickBtnNaviShare() called");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, mNote.getmTitle());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, mNote.getmSubject());
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                mNote.getmSubject());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void clickBtnNaviDelete(Note note) {
        Log.d(TAG, "clickBtnNaviDelete() called");
        showDialogConFirmDel();
    }

    private void showDialogConFirmDel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this?");
        builder.setCancelable(false);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Vuong", "onClick() called with: dialogInterface = [" + dialogInterface + "], i = [" + i + "]");
                mEditNotePresenter.requestDeleteNote(mNote);
                mEditNotePresenter.requestListNode();
                AlarmUtils.cancelAlarm(getContext(), mNote);
                if (mArrNote.size() == FIRST_INDEX) {
                    Log.d("Vuong", "onClick() called with:mArrNote.size() == FIRST_INDEX");
                    mAddNoteFragment.getICallBackAddNote().clickBtnBack();
                    return;
                }
                if (mPosition > 0) {
                    --mPosition;
                }
                mNote = mArrNote.get(mPosition);
                mAddNoteFragment.setData(mNote);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void clickBtnNaviNext() {
        Log.d(TAG, "clickBtnNaviNext() called");
        mIvNaviBack.setImageResource(R.drawable.ic_back);
        mPosition += NEXT_INDEX;
        if (mPosition < mArrNote.size()) {
            mNote = mArrNote.get(mPosition);
            mAddNoteFragment.setData(mNote);
            if (mPosition == mArrNote.size() - 1) {
                mIvNaviNext.setImageResource(R.drawable.ic_next_hiden);
            }
        } else {
            mPosition -= NEXT_INDEX;
        }
    }


    @Override
    public void updateListNote(List<Note> arr) {
        this.mArrNote.clear();
        this.mArrNote.addAll(arr);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void responeDeleteNote(boolean b) {
        clickBtnNaviBack();
    }

    @Override
    public void shareFacebook() {

    }

    @Override
    public void shareTwitter() {

    }

    @Override
    public void shareLine() {

    }
}
