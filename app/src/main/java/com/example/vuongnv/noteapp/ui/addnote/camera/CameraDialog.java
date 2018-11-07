package com.example.vuongnv.noteapp.ui.addnote.camera;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.ui.base.BaseDialog;

import javax.inject.Inject;

public class CameraDialog extends BaseDialog implements CameraView, View.OnClickListener {
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

    //MVP
    @Inject
    CameraMvpPresenter<CameraView> mCameraMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_camera);
        initMVP();
        setTitle(mContext.getResources().getString(R.string.insertpicture));
        initView();
        setOnclick();
    }

    private void initMVP() {
        getmDialogComponent().inject(this);
        mCameraMvpPresenter.attachDialog(this);
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
                mCameraMvpPresenter.requestOpenCamera();
                break;
            case R.id.tb_choose_image:
                mCameraMvpPresenter.requestOpenGallery();
                break;
            default:
                break;
        }
        dismiss();
    }

    @Override
    public void openCamera() {
        mICameraCall.openCamera();
    }

    @Override
    public void openGallery() {
        mICameraCall.openGallery();
    }


    public interface ICameraCall {
        void openCamera();

        void openGallery();
    }
}
