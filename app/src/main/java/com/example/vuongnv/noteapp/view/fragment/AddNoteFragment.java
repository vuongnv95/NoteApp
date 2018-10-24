package com.example.vuongnv.noteapp.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.model.Note;
import com.example.vuongnv.noteapp.model.prenter.NoteChangeIndicator;
import com.example.vuongnv.noteapp.presenter.NoteChangePresenter;
import com.example.vuongnv.noteapp.view.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.view.dialog.CameraDialog;
import com.example.vuongnv.noteapp.view.dialog.ColorDialog;
import com.example.vuongnv.noteapp.view.presenter.INoteChangeView;
import com.example.vuongnv.noteapp.view.utils.NoteUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint("ValidFragment")
public class AddNoteFragment extends Fragment implements View.OnClickListener, CameraDialog.ICameraCall, ColorDialog.ColorBackground, INoteChangeView, TextWatcher {
    public final String FORMAT_DATE = "MM/dd/yyyy";
    private static final String TAG = AddNoteFragment.class.getSimpleName();
    //view
    private ImageView mIvBack;
    private EditText mEtDate;
    private EditText mEtTime;
    private EditText mEtTitle;
    private EditText mEtNote;
    private ImageView mIvCamera;
    private ImageView mIvFolder;
    private ImageView mIvRemove;
    private ImageView mIvAddNote;
    private ImageView mIvImageNote;
    private TextView mTvAlarm;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mtvTitle;
    private RelativeLayout mRlNote;

    //dialog_color
    private CameraDialog mCameraDialog;
    private ColorDialog mColorDialog;

    //
    private int mColor;
    private int mFlagFragment;

    //
    private Uri mUri;

    //
    Note mNote;

    //interface
    private ICallBackAddNote mCallBackAddNote;

    //
    Calendar mCalendar = Calendar.getInstance();

    //MVP
    NoteChangePresenter mNoteChangePresenter;

    @SuppressLint("ValidFragment")
    public AddNoteFragment(ICallBackAddNote callBackAddNote, Note note, int flagFragment) {
        this.mCallBackAddNote = callBackAddNote;
        mNote = note;
        this.mFlagFragment = flagFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addnote, container, false);
        initView(view);
        setOnClick();
        initData();
        return view;
    }

    private void initView(View view) {
        mRlNote = view.findViewById(R.id.rl_addnote_note);
        mIvBack = view.findViewById(R.id.iv_addnote_back);
        mEtDate = view.findViewById(R.id.et_addnote_date);
        mEtTime = view.findViewById(R.id.et_addnote_time);
        mEtTitle = view.findViewById(R.id.et_addnote_title);
        mEtNote = view.findViewById(R.id.et_addnote_note);
        mIvCamera = view.findViewById(R.id.iv_addnote_camera);
        mIvFolder = view.findViewById(R.id.iv_addnote_gallery);
        mIvRemove = view.findViewById(R.id.iv_addnote_delete);
        mIvAddNote = view.findViewById(R.id.iv_addnote_add);
        mTvAlarm = view.findViewById(R.id.tv_addnote_alarm);
        mIvImageNote = view.findViewById(R.id.iv_addnote_node);
        mTvDate = view.findViewById(R.id.tv_addnote_date);
        mTvTime = view.findViewById(R.id.tv_addnote_time);
        mtvTitle = view.findViewById(R.id.tv_addnote_title);
    }

    private void setOnClick() {
        mIvBack.setOnClickListener(this);
        mEtDate.setOnClickListener(this);
        mEtTime.setOnClickListener(this);
        mIvCamera.setOnClickListener(this);
        mIvFolder.setOnClickListener(this);
        mIvAddNote.setOnClickListener(this);
        mIvRemove.setOnClickListener(this);
        mTvAlarm.setOnClickListener(this);
        mEtTitle.addTextChangedListener(this);
    }


    private void initData() {
        //init mvp
        initMVP();
        if (mNote != null) {
            mTvDate.setText(mNote.getmDate());
            mTvTime.setText(mNote.getmTime());
            mEtTitle.setText(mNote.getmTitle());
            mEtNote.setText(mNote.getmSubject());
            mRlNote.setBackgroundColor(mNote.getmColor());
            mEtDate.setText(mNote.getmDate());
            mEtTime.setText(mNote.getmTime());
            setImageFromBytes();
            clickBtnAlarm();
        } else {
            mNote = new Note();
        }
    }

    private void setImageFromBytes() {
        byte[] byteImage = mNote.getmImageNote();
        if (byteImage!= null) {
           mIvImageNote.setImageBitmap(BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length));
           mIvImageNote.setVisibility(View.VISIBLE);
        }
    }

    private void initMVP() {
        NoteChangeIndicator noteChangeIndicator = new NoteChangeIndicator(getContext());
        this.mNoteChangePresenter = new NoteChangePresenter(noteChangeIndicator, this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_addnote_back:
                mCallBackAddNote.clickBtnBack();
                break;
            case R.id.et_addnote_date:
                clickBtnDate();
                break;
            case R.id.et_addnote_time:
                clickBtnTime();
                break;
            case R.id.iv_addnote_camera:
                clickBtnCamera();
                break;
            case R.id.iv_addnote_gallery:
                clickBtnChooseColor();
                break;
            case R.id.iv_addnote_delete:
                clickBtnDelete();
                break;
            case R.id.tv_addnote_alarm:
                clickBtnAlarm();
                break;
            case R.id.iv_addnote_add:
                clickBtnAdd();
                break;
            default:
                Log.d(TAG, "onClick() called with: v = [" + v + "]");
                break;
        }
    }

    private void clickBtnDate() {
        new DatePickerDialog(getContext(), date, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextDate();
        }
    };

    private void updateTextDate() {
        String dateFormat = FORMAT_DATE;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        String date = sdf.format(mCalendar.getTime());
        mEtDate.setText(date);
        mTvDate.setText(date);
    }

    private void clickBtnTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                updateTextTime(hourOfDay, minute);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void updateTextTime(int hourOfDay, int minute) {
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        mEtTime.setText(time);
        mTvTime.setText(time);
    }

    private void clickBtnCamera() {
        if (mCameraDialog == null) {
            mCameraDialog = new CameraDialog(getContext(), this);
        }
        mCameraDialog.show();
    }

    private void clickBtnChooseColor() {
        if (mColorDialog == null) {
            mColorDialog = new ColorDialog(getContext(), this);
        }
        mColorDialog.show();

    }

    private void clickBtnDelete() {
        mTvAlarm.setVisibility(View.VISIBLE);
        mEtTime.setVisibility(View.GONE);
        mEtDate.setVisibility(View.GONE);
        mIvRemove.setVisibility(View.GONE);
    }

    private void clickBtnAlarm() {
        mEtTime.setVisibility(View.VISIBLE);
        mEtDate.setVisibility(View.VISIBLE);
        mIvRemove.setVisibility(View.VISIBLE);
        mTvAlarm.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case NoteUtils.CHOOSE_IMAGE:
                    getImageChoose(data);
                    break;
                case NoteUtils.TAKE_IMAGE:
                    getImageTake(data);
                    break;
                default:
                    Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
                    break;
            }
        }
    }

    private void getImageChoose(Intent data) {
        if (data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                mIvImageNote.setVisibility(View.VISIBLE);
                mIvImageNote.setImageBitmap(bitmap);
                convertBitmapToBytes(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void convertBitmapToBytes(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            stream.close();
            mNote.setmImageNote(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getImageTake(Intent data) {
        Log.d(TAG, "getImageTake() called with: data = [" + data + "]" + mUri.getPath());
        Uri selectedImage = mUri;
        Context context = getContext();
        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), mUri);
            Log.d(TAG, "getImageTake() called with: data = [" + data + "]" + bitmap.toString());
            mIvImageNote.setVisibility(View.VISIBLE);
            mIvImageNote.setImageBitmap(bitmap);
            convertBitmapToBytes(bitmap);
        } catch (Exception e) {
            Log.d(TAG, "getImageTake() called with: data = [" + data + "]");
        }
    }

    @Override
    public void openCamera() {

        String name = getTimePresent();
        File destination = new File(Environment
                .getExternalStorageDirectory(), name + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mUri = Uri.fromFile(destination);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                mUri);
        Log.d(TAG, "openCamera() called" + mUri.getPath());
        startActivityForResult(intent, NoteUtils.TAKE_IMAGE);
    }

    private String getTimePresent() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().toString();
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), NoteUtils.CHOOSE_IMAGE);
    }


    private void clickBtnAdd() {
        Log.d(TAG, "clickBtnAdd() called");
        mNote.setmTitle(String.valueOf(mEtTitle.getText()));
        mNote.setmSubject(String.valueOf(mEtNote.getText()));
        mNote.setmDate(String.valueOf(mTvDate.getText()));
        switch (mFlagFragment) {
            case NoteUtils.FLAG_ADD_FRAGMENT:
                mNoteChangePresenter.addNote(mNote);
                break;
            case NoteUtils.FLAG_EDIT_FRAGMENT:
                mNoteChangePresenter.updateNode(mNote);
                break;
            default:
                break;
        }
    }

    @Override
    public void setBackground(int color) {
        Resources resources = getContext().getResources();
        int colorBackground = 0;
        switch (color) {
            case NoteUtils.COLOR_GREEN:
                colorBackground = resources.getColor(R.color.choose_item_green);
                break;
            case NoteUtils.COLOR_WHITE:
                colorBackground = resources.getColor(R.color.choose_item_white);
                break;
            case NoteUtils.COLOR_LIGHT:
                colorBackground = resources.getColor(R.color.choose_item_light);
                break;
            case NoteUtils.COLOR_YELLOW:
                colorBackground = resources.getColor(R.color.choose_item_red);
                break;
            default:
                break;
        }
        mNote.setmColor(colorBackground);
        mRlNote.setBackgroundColor(colorBackground);
    }

    @Override
    public void updateNoteEdit(Note note) {
        mCallBackAddNote.clickBtnAdd(note);
    }

    @Override
    public void updateNoteAdd(Note note) {
        mCallBackAddNote.clickBtnAdd(note);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mtvTitle.setText(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
