package com.example.vuongnv.noteapp.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.model.Note;
import com.example.vuongnv.noteapp.view.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.view.callback.ICallBackEditNoteI;
import com.example.vuongnv.noteapp.view.utils.NoteUtils;


@SuppressLint("ValidFragment")
public class EditNoteFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = EditNoteFragment.class.getSimpleName();

    private Note mNote;

    //view
    private TextView mTvDate;
    private TextView mTvtime;
    private ImageView mIvBack;

    //interface
    ICallBackEditNoteI mCallBackEditNote;
    ICallBackAddNote mICallBackAddNote;

    public EditNoteFragment(ICallBackEditNoteI callBackEditNote, ICallBackAddNote iCallBackAddNote,Note note) {
        this.mCallBackEditNote = callBackEditNote;
        this.mICallBackAddNote = iCallBackAddNote;
        this.mNote = note;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editnote, container, false);
        initView(view);
//        setOnclick();
//        initData();
//        setData();
        return view;
    }

    private void initView(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_editnote_addnote,new AddNoteFragment(mICallBackAddNote,mNote,NoteUtils.FLAG_EDIT_FRAGMENT)).commit();
//        mTvDate = view.findViewById(R.id.tv_addnote_date);
//        mTvtime = view.findViewById(R.id.tv_addnote_time);
//        mIvBack = view.findViewById(R.id.iv_editnoteback);
    }

    private void setOnclick() {
        mIvBack.setOnClickListener(this);
    }

    private void initData() {

    }

    private void setData() {
        mTvtime.setText(mNote.getmTime());
        mTvDate.setText(mNote.getmDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_editnoteback:
//                mCallBackEditNote.clickBtnBack();
//                break;
            default:
                Log.d(TAG, "onClick() called with: v = [" + v + "]");
                break;
        }
    }
}
