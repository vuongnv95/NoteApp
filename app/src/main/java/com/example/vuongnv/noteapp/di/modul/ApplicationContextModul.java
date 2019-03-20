package com.example.vuongnv.noteapp.di.modul;

import android.content.Context;

import com.example.vuongnv.noteapp.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModul {
    private Context mContext;

    public ApplicationContextModul(Context context) {
        mContext = context;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return this.mContext.getApplicationContext();
    }
}
