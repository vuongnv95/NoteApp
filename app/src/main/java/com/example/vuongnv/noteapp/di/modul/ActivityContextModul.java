package com.example.vuongnv.noteapp.di.modul;

import android.content.Context;

import com.example.vuongnv.noteapp.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModul {
    private Context mContext;

    public ActivityContextModul(Context context) {
        mContext = context;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return this.mContext;
    }
}
