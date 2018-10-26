package com.example.vuongnv.noteapp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableRow;

import com.example.vuongnv.noteapp.R;

public class PopupBottomDialog extends Dialog implements View.OnClickListener {
    private TableRow mTrShareFaceBook;
    private TableRow mTrShareTwitter;
    private TableRow mTrShareLine;

    private OnDialogMenuListener mOnDialogMenuListener;

    public PopupBottomDialog(Context context,OnDialogMenuListener onDialogMenuListener) {
        super(context);
        this.mOnDialogMenuListener = onDialogMenuListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_popup_bottom);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
        setOnclick();
    }

    private void initView() {
        mTrShareFaceBook = findViewById(R.id.tr_share_facebook);
        mTrShareTwitter = findViewById(R.id.tr_share_twitter);
        mTrShareLine= findViewById(R.id.tr_share_line);
    }

    private void setOnclick() {
        mTrShareLine.setOnClickListener(this);
        mTrShareFaceBook.setOnClickListener(this);
        mTrShareTwitter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tr_share_facebook:
                mOnDialogMenuListener.shareFacebook();
                break;
            case R.id.tr_share_twitter:
                mOnDialogMenuListener.shareTwitter();
                break;
            case R.id.tr_share_line:
                mOnDialogMenuListener.shareLine();
                break;
                default:
                    break;
        }
    }

    public interface OnDialogMenuListener{
        void shareFacebook();
        void shareTwitter();
        void shareLine();
    }
}
