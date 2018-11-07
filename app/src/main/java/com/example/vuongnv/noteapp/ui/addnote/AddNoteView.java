package com.example.vuongnv.noteapp.ui.addnote;


import android.view.View;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.ui.base.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface AddNoteView extends MvpView {
    void updateNoteEdit(Note note);

    void updateNoteAdd(Note note);

    void updateNoteImages(List<NoteImage> arrImage);

    void updateClickBtnBack();

    void updateClickBtnDelete();

    void updateClickBtnCamera();

    void updateClickBtnGallery();

    void updateClickBtnAlarm();

    void updateClickBtnMore(View view);

    void updateClickDeleteNoteImage(int position);

    void showDatePicker();

    void showTimePicker();

    void updateTimePicker(String time, ArrayList<String> arrTime);

    void updateDatePicker(String date, ArrayList<String> arrDate);

    void updateTime(String time);

    void updateDate(String time);

    void updateSpinnerDate(ArrayList<String> arrDate);

    void updateSpinnerTime(ArrayList<String> arrTime);

}
