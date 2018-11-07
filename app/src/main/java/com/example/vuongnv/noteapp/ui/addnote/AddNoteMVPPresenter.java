package com.example.vuongnv.noteapp.ui.addnote;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.ui.base.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

public interface AddNoteMVPPresenter<V extends AddNoteView> extends MvpPresenter<V> {
    void requestUpdateNode(Note note);

    void requestAddNote(Note note, ArrayList<NoteImage> noteImages, Context context);

    void requestNoteImages(Note note);

    //    void requestAddNoteImages(Note note, List<NoteImage> arrNoteImages);
    void requestClickBtnBack();

    void requestUpdateNoteImages(Note note, List<NoteImage> arrNoteImages);

    void requestclickBtnGallery();

    void requestClickBtnDelete();

    void requestClickBtnAlarm();

    void requestClickBtnAdd();

    void requestClickBtnMore(View view);

    void requestClickDeleteImageItem(int position);

    void requestDataNoteDate(Note note);

    void requestDataNoteTime(Note note);

    void requestClickBtnCamera();

    void requestClickItemSpinnerTime(AdapterView<?> parent, ArrayAdapter<String> adapter, int position);

    void getDateSpinner(int position);

    void getTimeSpinner(int position);

    void requestClickTimePicker(int hourOfDay, int minute);

    void requestClickDatePicker(int year, int month, int dayOfMonth);

    void requestClickItemSpinnerDate();
}
