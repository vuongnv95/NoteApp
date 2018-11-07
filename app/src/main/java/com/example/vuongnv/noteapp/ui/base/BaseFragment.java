package com.example.vuongnv.noteapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.vuongnv.noteapp.di.component.DaggerMainActivityComponent;
import com.example.vuongnv.noteapp.di.component.MainActivityComponent;
import com.example.vuongnv.noteapp.di.modul.MainActivityModul;

public class BaseFragment extends Fragment {
    private MainActivityComponent mMainActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityComponent = DaggerMainActivityComponent.builder().mainActivityModul(new MainActivityModul(getContext())).build();
    }

    public MainActivityComponent getmMainActivityComponent() {
        return mMainActivityComponent;
    }
}
