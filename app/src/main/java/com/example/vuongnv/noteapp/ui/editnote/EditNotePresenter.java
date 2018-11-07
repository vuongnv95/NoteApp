package com.example.vuongnv.noteapp.ui.editnote;

import com.example.vuongnv.noteapp.data.DataManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class EditNotePresenter<V extends EditNoteView> extends BasePresenter<V> implements EditNoteMVPPresenter<V> {
    private final int NEXT_INDEX = 1;
    private final int FIRST_INDEX = 0;
    private int mPosition;
    private List<Note> mArrNotes;

    @Inject
    public EditNotePresenter(DataManager mNoteDataManager, CompositeDisposable mCompositeDisposable) {
        super(mNoteDataManager, mCompositeDisposable);
    }

//    public EditNotePresenter(EditNoteIndicator mEditNoteIndicator, EditNoteView editNoteView) {
//        this.mEditNoteIndicator = mEditNoteIndicator;
//        this.mEditNoteView = editNoteView;
//    }

    @Override
    public void requestListNode() {
        getDataManager().getAllNotes().subscribe(new Consumer<List<Note>>() {
            @Override
            public void accept(List<Note> notes) throws Exception {
                mArrNotes = notes;
                getView().updateListNote(notes);
            }
        });
//        mArrNotes = getDataManager().getAllNotes();
    }

    @Override
    public void requestDeleteNote(Note note) {
//        mEditNoteIndicator.deleteNote(note, this);
        getDataManager().deleteNote(note).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                getView().responeDeleteNote(true);
            }
        });
    }


    @Override
    public void requestClickBtnShare() {
        getView().updateClickBtnShare();
    }

    @Override
    public void requestClickBtnDelete() {
        getView().updateClickBtnDelete();
    }


    @Override
    public void requestClickBtnBack(int position) {
        mPosition = position;
        mPosition -= NEXT_INDEX;
        boolean isHiddenBack = false;
//        mIvNaviNext.setImageResource(R.drawable.ic_next);
        if (mPosition >= FIRST_INDEX) {
            Note note = mArrNotes.get(mPosition);
//            mAddNoteFragment.setData(mNote);
            if (mPosition == FIRST_INDEX) {
                isHiddenBack = true;
//                mIvNaviBack.setImageResource(R.drawable.ic_back_hiden);
            }
            getView().updateClickBtnBack(note, isHiddenBack, mPosition);
        } else {
            mPosition += NEXT_INDEX;
        }
    }

    @Override
    public void requestClickBtnNext(int position) {
        mPosition = position;
//        mIvNaviBack.setImageResource(R.drawable.ic_back);
        mPosition += NEXT_INDEX;
        boolean isHiddenBack = false;
        if (mPosition < mArrNotes.size()) {
            Note note = mArrNotes.get(mPosition);
//            mAddNoteFragment.setData(mNote);
            if (mPosition == mArrNotes.size() - 1) {
                isHiddenBack = true;
//                mIvNaviNext.setImageResource(R.drawable.ic_next_hiden);
            }
            getView().updateClickBtnNext(note, isHiddenBack, mPosition);
        } else {
            mPosition -= NEXT_INDEX;
        }

    }

}
