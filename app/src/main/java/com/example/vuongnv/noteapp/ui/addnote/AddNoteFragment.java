package com.example.vuongnv.noteapp.ui.addnote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.ui.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.ui.dialog.CameraDialog;
import com.example.vuongnv.noteapp.ui.dialog.ColorDialog;
import com.example.vuongnv.noteapp.utils.AlarmUtils;
import com.example.vuongnv.noteapp.utils.NoteUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("ValidFragment")
public class AddNoteFragment extends Fragment implements View.OnClickListener, CameraDialog.ICameraCall, ColorDialog.ColorBackground, INoteChangeView, TextWatcher, PopupMenu.OnMenuItemClickListener, AdapterView.OnItemSelectedListener {
    private static final String TAG = AddNoteFragment.class.getSimpleName();
    private final int VALUE_TODAY = 0;
    private final int VALUE_TOMORROW = 1;
    private final int VALUE_NEXTWEEK = 7;
    private final int SELECT_POSITION = 2;
    private final int NUMBER_SELECT_SPINNER = 2;

    public final String FORMAT_DATE = "MM/dd/yyyy";
    public final String FORMAT_TIME_PRESENT = "MM/dd/yyyy HH:mm";
    private final String OTHER_TIME = "Other";
    private final String OTHER_DATE = "Other";

    //
    private boolean isSelectSpin;
    private int numSelectSpin = 0;

    //view
    private ImageView mIvBack;
    private Spinner mSpinDate;
    private Spinner mSpinTime;
    private EditText mEtTitle;
    private EditText mEtNote;
    private ImageView mIvCamera;
    private ImageView mIvFolder;
    private ImageView mIvRemove;
    private ImageView mIvAddNote;
    private ImageView mIvImageNote;
    private ImageView mIvMore;
    private TextView mTvAlarm;
    private TextView mTvTime;
    private TextView mtvTitle;
    private FrameLayout mFlBack;
    private RecyclerView mRvImage;
    private ScrollView mSrvNote;

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

    //data
    private List mArrDate;
    private List mArrTime;

    //adapter
    private ArrayAdapter<String> mDateAdapter;
    private ArrayAdapter<String> mTimeAdapter;


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
        //init mvp
        initMVP();
        initData(mNote);
        return view;
    }

    private void initView(View view) {
        mSrvNote = view.findViewById(R.id.srv_note);
        mIvBack = view.findViewById(R.id.iv_addnote_back);
        mSpinDate = view.findViewById(R.id.spin_addnote_date);
        mSpinTime = view.findViewById(R.id.spin_addnote_time);
        mEtTitle = view.findViewById(R.id.et_addnote_title);
        mEtNote = view.findViewById(R.id.et_addnote_note);
        mIvCamera = view.findViewById(R.id.iv_addnote_camera);
        mIvFolder = view.findViewById(R.id.iv_addnote_gallery);
        mIvRemove = view.findViewById(R.id.iv_addnote_delete);
        mIvAddNote = view.findViewById(R.id.iv_addnote_add);
        mTvAlarm = view.findViewById(R.id.tv_addnote_alarm);
        mIvImageNote = view.findViewById(R.id.iv_addnote_node);
        mIvMore = view.findViewById(R.id.iv_addnote_more);
        mTvTime = view.findViewById(R.id.tv_addnote_time);
        mtvTitle = view.findViewById(R.id.tv_addnote_title);
        mFlBack = view.findViewById(R.id.fl_addnote_back);
//        mRvImage = view.findViewById(R.id.rv_addnote_image);
    }

    private void setOnClick() {
        mSpinDate.setOnItemSelectedListener(this);
        mSpinTime.setOnItemSelectedListener(this);
        mIvCamera.setOnClickListener(this);
        mIvFolder.setOnClickListener(this);
        mIvAddNote.setOnClickListener(this);
        mIvRemove.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mTvAlarm.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
        mEtTitle.addTextChangedListener(this);
    }


    public void initData(Note note) {
        mNote = note;
        mArrDate = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.spin_date)));
        mArrTime = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.spin_time)));
        if (mNote != null) {
            mEtTitle.setText(mNote.getmTitle());
            mTvTime.setText(mNote.getmSetupTime());
            mEtNote.setText(mNote.getmSubject());
            mSrvNote.setBackgroundColor(mNote.getmColor());
            if (mNote.getmIsAlarm() == NoteUtils.IS_ALARM) {
                mArrDate.set((mArrDate.size() - 1), mNote.getmDate());
                mArrDate.add(OTHER_TIME);
                mArrTime.set((mArrTime.size() - 1), mNote.getmTime());
                mArrTime.add(OTHER_DATE);
                clickBtnAlarm();
            } else {
                clickBtnDelete();
            }
            mIvMore.setVisibility(View.VISIBLE);
            setImageFromBytes();
        } else {
            mNote = new Note();
            mTvTime.setText(getTimePresent());
            clickBtnDelete();
        }
        initDataSpinner();
    }

    private void initDataSpinner() {
        mDateAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.spinner_item,
                mArrDate
        );
        mSpinDate.setAdapter(mDateAdapter);
        mTimeAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.spinner_item,
                mArrTime
        );
        mSpinTime.setAdapter(mTimeAdapter);
        if (mNote.getmIsAlarm() == NoteUtils.IS_ALARM) {
            mSpinDate.setSelection(mArrDate.size() - SELECT_POSITION);
            mSpinTime.setSelection(mArrTime.size() - SELECT_POSITION);
        }
    }

    private void setImageFromBytes() {
        byte[] byteImage = mNote.getmImageNote();
        if (byteImage != null) {
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
            case R.id.fl_addnote_back:
                mCallBackAddNote.clickBtnBack();
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
            case R.id.iv_addnote_more:
                if (mIvMore.getVisibility() == View.VISIBLE) {
                    clickBtnMore(v);
                }

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
        int indexArr = mArrDate.size() - 1;
        mArrDate.set(indexArr, date);
        mArrDate.add(OTHER_DATE);
        mDateAdapter.notifyDataSetChanged();
        mSpinDate.setSelection(indexArr);
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
        mTimePicker.show();
    }

    private void updateTextTime(int hourOfDay, int minute) {
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        mNote.setmTime(time);
        mArrTime.set(mArrTime.size() - 1, time);
        mArrTime.add(OTHER_TIME);
        mTimeAdapter.notifyDataSetChanged();
        mSpinTime.setSelection(mArrTime.size() - SELECT_POSITION);

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
        mNote.setmIsAlarm(NoteUtils.NO_ALARM);
        mTvAlarm.setVisibility(View.VISIBLE);
        mSpinTime.setVisibility(View.GONE);
        mSpinDate.setVisibility(View.GONE);
        mIvRemove.setVisibility(View.GONE);
    }

    private void clickBtnAlarm() {
        mNote.setmIsAlarm(NoteUtils.IS_ALARM);
        mSpinTime.setVisibility(View.VISIBLE);
        mSpinDate.setVisibility(View.VISIBLE);
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
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME_PRESENT);
        String date = sdf.format(cal.getTime());
        return date;
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
        mNote.setmSetupTime(getTimePresent());
        getDateAlarm(mSpinDate.getSelectedItemPosition());
        getTimeAlarm(mSpinTime.getSelectedItemPosition());
        switch (mFlagFragment) {
            case NoteUtils.FLAG_ADD_FRAGMENT:
                mNoteChangePresenter.requestAddNote(mNote);
                break;
            case NoteUtils.FLAG_EDIT_FRAGMENT:
                mNoteChangePresenter.requestUpdateNode(mNote);
                break;
            default:
                break;
        }
    }

    private void clickBtnMore(View view) {
        PopupMenu popup = new PopupMenu(getContext(), view);
        popup.setOnMenuItemClickListener(this);// to implement on click event on items of menu
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.d(TAG, "onMenuItemClick() called with: item = [" + item + "]");
        mCallBackAddNote.clickBtnMore();
        return true;
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
        mSrvNote.setBackgroundColor(colorBackground);
    }

    @Override
    public void updateNoteEdit(Note note) {
        AlarmUtils.createAlarm(getContext(),note);
        mCallBackAddNote.clickBtnAdd(note);
    }

    @Override
    public void updateNoteAdd(Note note) {
        AlarmUtils.createAlarm(getContext(),note);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        numSelectSpin += 1;
        if (numSelectSpin <= NUMBER_SELECT_SPINNER) {
            return;
        }
        if (parent.getAdapter() == mDateAdapter) {
            getDateAlarm(position);
        } else{
            getTimeAlarm(position);
        }
    }

    private void getTimeAlarm(int position) {
        int sizeArrTime = mArrTime.size() - 1;
        if (sizeArrTime == position) {
            clickBtnTime();
        } else {
            mNote.setmTime(String.valueOf(mArrTime.get(position)));
        }
    }

    private void getDateAlarm(int position) {
        int sizeArrDate = mArrDate.size() - 1;
        if (sizeArrDate == position) {
            clickBtnDate();
        } else {
            switch (position) {
                case NoteUtils.TODAY:
                    getDate(VALUE_TODAY);
                    break;
                case NoteUtils.TOMORROW:
                    getDate(VALUE_TOMORROW);
                    break;
                case NoteUtils.NEXTWEDNETDAY:
                    getDate(VALUE_NEXTWEEK);
                    break;
                default:
                    mNote.setmDate(String.valueOf(mArrDate.get(position)));
                    break;
            }
        }
    }

    private void getDate(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, date);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        String dateTomorrow = dateFormat.format(tomorrow);
        mArrDate.set(0, dateTomorrow);
        mDateAdapter.notifyDataSetChanged();
        mNote.setmDate(dateTomorrow);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public ICallBackAddNote  getICallBackAddNote(){
        return this.mCallBackAddNote;
    }
}
