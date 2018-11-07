package com.example.vuongnv.noteapp.ui.notedetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.BaseFragment;
import com.example.vuongnv.noteapp.ui.callback.ICallNoteFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@SuppressLint("ValidFragment")
public class NoteFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, INoteView, ICallNoteFragment {
    private static final String TAG = NoteFragment.class.getSimpleName();

    //view
    private GridView mGridView;
    private ImageView mIvAdd;
    private NoteAdapter mNoteAdapter;
    private ArrayList<Note> mArrNote;

    //
    CallBackNoteFragment mCallBackNoteFragment;

    //Dagger
    @Inject
    NoteMVPPresenter<INoteView> mINoteViewNotePresenter;

    @SuppressLint("ValidFragment")
    public NoteFragment(CallBackNoteFragment callBackNoteFragment) {
        this.mCallBackNoteFragment = callBackNoteFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        getmMainActivityComponent().inject(this);
        initPresenter();
        initView(view);
        initData();
        setOnclick();
        return view;
    }

    private void initPresenter() {
        mINoteViewNotePresenter.atttachView(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView(View view) {
        mGridView = view.findViewById(R.id.gv_note);
        mIvAdd = view.findViewById(R.id.iv_addnote_add);
    }

    private void setOnclick() {
        mIvAdd.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);

    }

    private void initData() {
        mArrNote = new ArrayList<>();
        mNoteAdapter = new NoteAdapter(getContext(), mArrNote);
        mGridView.setAdapter(mNoteAdapter);
        mINoteViewNotePresenter.getAllNotes();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_addnote_add:
                mCallBackNoteFragment.createAddFragment();
                break;
            default:
                Log.d(TAG, "onClick: ");
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallBackNoteFragment.createEditFragment(position);
    }

    @Override
    public void updateAllNotes(List<Note> arrNote) {
        mArrNote.addAll(arrNote);
        for (Note note : arrNote) {
            Log.d("Vuong", "updateAllNotes() called with: getmIdNode = [" + note.getmIdNode() + "]");
        }
        Log.d(TAG, "updateAllNotes() called with: arrNote = [" + arrNote.size() + "]");
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateViewAddNote(Note note) {
        mArrNote.add(note);
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateViewEditNote(Note note) {
        if (mArrNote.isEmpty()) {
            return;
        }
        for (int i = 0; i < mArrNote.size(); i++) {
            if (mArrNote.get(i).getmIdNode() == note.getmIdNode()) {
                mArrNote.set(i, note);
                return;
            }
        }
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public List<Note> getListNote() {
        return mArrNote.isEmpty() ? null : mArrNote;
    }

    public interface CallBackNoteFragment {
        void createAddFragment();

        void createEditFragment(int position);
    }

    public ArrayList<Note> getmArrNote() {
        return mArrNote;
    }
}
