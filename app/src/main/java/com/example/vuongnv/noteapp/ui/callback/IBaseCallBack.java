package com.example.vuongnv.noteapp.ui.callback;


import com.example.vuongnv.noteapp.data.db.model.Note;

public interface IBaseCallBack {
    void clickBtnBack();

    void popFragmentBackStack(Note note);

    void clickBtnMore();
}
