package com.example.vuongnv.noteapp.ui.base;

import android.app.Dialog;
import android.content.Context;

import com.example.vuongnv.noteapp.di.component.DaggerDialogComponent;
import com.example.vuongnv.noteapp.di.component.DialogComponent;
import com.example.vuongnv.noteapp.di.modul.DialogModul;

public class BaseDialog extends Dialog {
    private DialogComponent mDialogComponent;
    public BaseDialog( Context context) {
        super(context);
        mDialogComponent = DaggerDialogComponent.builder().dialogModul(new DialogModul()).build();
    }

    public DialogComponent getmDialogComponent() {
        return mDialogComponent;
    }
}
