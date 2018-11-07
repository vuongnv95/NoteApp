package com.example.vuongnv.noteapp.ui.addnote.color;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.ui.base.BaseDialog;
import com.example.vuongnv.noteapp.ui.customview.CustomColorView;
import com.example.vuongnv.noteapp.utils.NoteUtils;

import javax.inject.Inject;


public class ColorDialog extends BaseDialog implements ColorView, View.OnClickListener {
    //
    private Context mContext;

    //
    private CustomColorView mCvYellow;
    private CustomColorView mCvGreen;
    private CustomColorView mCvLight;
    private CustomColorView mCvWhite;

    //
    private ColorBackground mColorBackground;

    //MVP
    @Inject
    ColorMvpPresenter<ColorView> mColorMvpPresenter;

    public ColorDialog(Context context, ColorBackground colorBackground) {
        super(context);
        this.mContext = context;
        this.mColorBackground = colorBackground;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_color);
        initMVP();
        setTitle(mContext.getResources().getString(R.string.colordialog_title));
        initView();
        setOnclick();
    }

    private void initMVP() {
        getmDialogComponent().inject(this);
        mColorMvpPresenter.attachDialog(this);
    }

    private void initView() {
        mCvYellow = findViewById(R.id.cv_color_yellow);
        mCvGreen = findViewById(R.id.cv_color_green);
        mCvLight = findViewById(R.id.cv_color_light);
        mCvWhite = findViewById(R.id.cv_color_white);
    }

    private void setOnclick() {
        mCvYellow.setOnClickListener(this);
        mCvGreen.setOnClickListener(this);
        mCvLight.setOnClickListener(this);
        mCvWhite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_color_yellow:
                mColorMvpPresenter.requestBtnYellow();
//                mColorBackground.setBackground(NoteUtils.COLOR_YELLOW);
                break;
            case R.id.cv_color_green:
                mColorMvpPresenter.requestBtnGreen();
//                mColorBackground.setBackground(NoteUtils.COLOR_GREEN);
                break;
            case R.id.cv_color_light:
                mColorMvpPresenter.requestBtnLight();
//                mColorBackground.setBackground(NoteUtils.COLOR_LIGHT);
                break;
            case R.id.cv_color_white:
                mColorMvpPresenter.requestBtnWhite();
//                mColorBackground.setBackground(NoteUtils.COLOR_WHITE);
                break;
            default:
                mColorMvpPresenter.requestBtnWhite();
//                mColorBackground.setBackground(NoteUtils.COLOR_WHITE);
                break;
        }
        dismiss();
    }

    @Override
    public void updateColorYellow() {
        mColorBackground.setBackground(NoteUtils.COLOR_YELLOW);
    }

    @Override
    public void updateColorGreen() {
        mColorBackground.setBackground(NoteUtils.COLOR_GREEN);
    }

    @Override
    public void updateColorLight() {
        mColorBackground.setBackground(NoteUtils.COLOR_LIGHT);
    }

    @Override
    public void updateColorWhite() {
        mColorBackground.setBackground(NoteUtils.COLOR_WHITE);
    }

    public interface ColorBackground {
        void setBackground(int color);
    }
}
