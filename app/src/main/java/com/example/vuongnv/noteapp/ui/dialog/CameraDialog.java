package com.example.vuongnv.noteapp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import com.example.vuongnv.noteapp.R;

public class CameraDialog extends Dialog implements View.OnClickListener {
    //view
    private TableRow mTrTakePhoto;
    private TableRow mTrChoosePhoto;
    private Context mContext;
    private ICameraCall mICameraCall;

    public CameraDialog(Context context, ICameraCall iCameraCall) {
        super(context);
        this.mContext = context;
        this.mICameraCall = iCameraCall;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_camera);
        setTitle(mContext.getResources().getString(R.string.insertpicture));
        initView();
        setOnclick();
    }

    private void initView() {
        mTrTakePhoto = findViewById(R.id.tb_camera);
        mTrChoosePhoto = findViewById(R.id.tb_choose_image);
    }

    private void setOnclick() {
        mTrTakePhoto.setOnClickListener(this);
        mTrChoosePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_camera:
                mICameraCall.openCamera();
                break;
            case R.id.tb_choose_image:
                mICameraCall.openGallery();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface ICameraCall {
        void openCamera();

        void openGallery();
    }
}
