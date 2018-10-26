package com.example.vuongnv.noteapp.ui.callback;


import com.example.vuongnv.noteapp.data.db.model.Note;

public interface IBaseCallBack {
    void clickBtnBack();
    void clickBtnAdd(Note note);
    void clickBtnMore();
}
