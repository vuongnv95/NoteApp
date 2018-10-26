package com.example.vuongnv.noteapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.view.customview.ColorView;
import com.example.vuongnv.noteapp.utils.NoteUtils;


public class ColorDialog extends Dialog implements View.OnClickListener {
    //
    private Context mContext;

    //
    private ColorView mCvYellow;
    private ColorView mCvGreen;
    private ColorView mCvLight;
    private ColorView mCvWhite;

    //
    private ColorBackground mColorBackground;

    public ColorDialog(Context context, ColorBackground colorBackground) {
        super(context);
        this.mContext = context;
        this.mColorBackground = colorBackground;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_color);
        setTitle(mContext.getResources().getString(R.string.colordialog_title));
        initView();
        setOnclick();
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
                mColorBackground.setBackground(NoteUtils.COLOR_YELLOW);
                break;
            case R.id.cv_color_green:
                mColorBackground.setBackground(NoteUtils.COLOR_GREEN);
                break;
            case R.id.cv_color_light:
                mColorBackground.setBackground(NoteUtils.COLOR_LIGHT);
                break;
            case R.id.cv_color_white:
                mColorBackground.setBackground(NoteUtils.COLOR_WHITE);
                break;
            default:
                mColorBackground.setBackground(NoteUtils.COLOR_WHITE);
                break;
        }
        dismiss();
    }

    public interface ColorBackground {
        void setBackground(int color);
    }
}
