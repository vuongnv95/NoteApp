package com.example.vuongnv.noteapp.ui.editnote;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.base.MvpView;

import java.util.List;

public interface EditNoteView extends MvpView {
    void updateListNote(List<Note> arr);

    void responeDeleteNote(boolean b);

    void updateClickBtnBack(Note note, boolean isHiddenBack, int position);

    void updateClickBtnNext(Note note, boolean isHiddenBack, int position);

    void updateClickBtnShare();

    void updateClickBtnDelete();


}
