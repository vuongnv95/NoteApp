package com.example.vuongnv.noteapp.ui.addnote;

import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;

import java.util.List;

public interface NoteChangMVPPresenter {
    void requestUpdateNode(Note note);

    void requestAddNote(Note note);

    List<NoteImage> requestNoteImages(Note note);

    void requestAddNoteImages(Note note, List<NoteImage> arrNoteImages);

    void requestUpdateNoteImages(Note note, List<NoteImage> arrNoteImages);

    void clickImageItem(int position);
}
