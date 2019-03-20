package com.example.vuongnv.noteapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.vuongnv.noteapp.di.component.DaggerMainActivityComponent;
import com.example.vuongnv.noteapp.di.component.MainActivityComponent;
import com.example.vuongnv.noteapp.di.component.NoteHelperComponent;
import com.example.vuongnv.noteapp.di.modul.ActivityContextModul;
import com.example.vuongnv.noteapp.di.modul.MainActivityModul;
import com.example.vuongnv.noteapp.ui.Demo1;
import com.example.vuongnv.noteapp.ui.NoteApplication;

public class BaseFragment extends Fragment {
    private MainActivityComponent mMainActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityComponent = DaggerMainActivityComponent.builder().mainActivityModul(new MainActivityModul()).activityContextModul(new ActivityContextModul(getContext())).noteHelperComponent(NoteApplication.get(getActivity()).getNoteHelperComponent()).build();
        NoteHelperComponent noteHelperComponent = NoteApplication.get(getActivity()).getNoteHelperComponent();
        Demo1 demo1 = noteHelperComponent.provideDemo1();
        Log.d("Vuong", "onCreate() called with: name = [" + demo1.getName() + "]");
        ++ NoteApplication.index;
        demo1.setName("Vuong" + NoteApplication.index);
    }

    public MainActivityComponent getmMainActivityComponent() {
        return mMainActivityComponent;
    }
}
