package com.example.vuongnv.noteapp.ui.addnote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.vuongnv.noteapp.BuildConfig;
import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.ui.adapter.NoteImageAdapter;
import com.example.vuongnv.noteapp.ui.callback.ICallBackAddNote;
import com.example.vuongnv.noteapp.ui.customview.CustomTextView;
import com.example.vuongnv.noteapp.ui.dialog.CameraDialog;
import com.example.vuongnv.noteapp.ui.dialog.ColorDialog;
import com.example.vuongnv.noteapp.utils.AlarmUtils;
import com.example.vuongnv.noteapp.utils.NoteUtils;

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
    private final String FORMAT_IMAGE = ".jpg";
    private final int FIRST_ITEM_SPINNER = 0;
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
    private CustomTextView mEtNote;
    private ImageView mIvCamera;
    private ImageView mIvFolder;
    private ImageView mIvRemove;
    private ImageView mIvAddNote;
    private RecyclerView mRvNoteImages;
    //    private ImageView mIvImageNote;
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
    private List<String> mArrDate;
    private List<String> mArrTime;
    private ArrayList<NoteImage> mArrNoteImages;
    private int mPositionTimepicker;
    private int mPositionDatepicker;

    //adapter
    private ArrayAdapter<String> mDateAdapter;
    private ArrayAdapter<String> mTimeAdapter;
    private NoteImageAdapter noteImageAdapter;


    @SuppressLint("ValidFragment")
    public AddNoteFragment(ICallBackAddNote callBackAddNote, Note note, int flagFragment) {
        this.mCallBackAddNote = callBackAddNote;
        mNote = note;
        this.mFlagFragment = flagFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addnote, container, false);
        //init mvp
        initMVP();
        initData();
        initView(view);
        setOnClick();
        setData(mNote);
        return view;
    }

    private void initData() {
        mArrDate = new ArrayList<>();
        mArrTime = new ArrayList<>();
        mArrNoteImages = new ArrayList<>();
        noteImageAdapter = new NoteImageAdapter(getContext(), mArrNoteImages, mNoteChangePresenter);
    }

    private void initView(View view) {
        mSrvNote = view.findViewById(R.id.srv_note);
        mIvBack = view.findViewById(R.id.iv_addnote_back);
        mSpinDate = view.findViewById(R.id.spin_addnote_date);
        mSpinTime = view.findViewById(R.id.spin_addnote_time);
        mEtTitle = view.findViewById(R.id.et_addnote_title);
        mEtNote = view.findViewById(R.id.et_addnote_note);
        mEtNote.addTextChangedListener(this);
        mIvCamera = view.findViewById(R.id.iv_addnote_camera);
        mIvFolder = view.findViewById(R.id.iv_addnote_gallery);
        mIvRemove = view.findViewById(R.id.iv_addnote_delete);
        mIvAddNote = view.findViewById(R.id.iv_addnote_add);
        mTvAlarm = view.findViewById(R.id.tv_addnote_alarm);
        mRvNoteImages = view.findViewById(R.id.rv_addnote_nodeimage);
        mRvNoteImages.setLayoutManager(new GridLayoutManager(getContext(), NoteUtils.COMLUMN_IMAGE));
//        mIvImageNote = view.findViewById(R.id.iv_addnote_node);
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setData(Note note) {
        mNote = note;
        mArrDate.clear();
        mArrTime.clear();
        mArrDate.addAll(Arrays.asList(getResources().getStringArray(R.array.spin_date)));
        mArrTime.addAll(Arrays.asList(getResources().getStringArray(R.array.spin_time)));
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
            setNoteImages();
        } else {
            mNote = new Note();
            mTvTime.setText(getTimePresent());
            clickBtnDelete();
        }

        mRvNoteImages.setAdapter(noteImageAdapter);
        noteImageAdapter.notifyDataSetChanged();
        initDataSpinner();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //get all image in sqlite
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setNoteImages() {
        mArrNoteImages.clear();
        mArrNoteImages.addAll(mNoteChangePresenter.requestNoteImages(mNote));
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
            mPositionDatepicker = mArrDate.size() - SELECT_POSITION;
            mPositionTimepicker = mArrTime.size() - SELECT_POSITION;
            mSpinDate.setSelection(mPositionDatepicker);
            mSpinTime.setSelection(mPositionTimepicker);
        } else {
            mPositionTimepicker = FIRST_ITEM_SPINNER;
            mPositionDatepicker = FIRST_ITEM_SPINNER;
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
        DatePickerDialog dialog = new DatePickerDialog(getContext(), date, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    mSpinDate.setSelection(mPositionDatepicker);
                }
            }
        });
        dialog.show();
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
        mTimePicker.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    mSpinTime.setSelection(mPositionTimepicker);
                }
            }
        });
        mTimePicker.show();
    }

    private void updateTextTime(int hourOfDay, int minute) {
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        mNote.setmTime(time);
        int indexArr = mArrTime.size() - 1;
        mArrTime.set(indexArr, time);
        mArrTime.add(OTHER_TIME);
        mTimeAdapter.notifyDataSetChanged();
        mSpinTime.setSelection(indexArr);

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
        mSpinTime.setVisibility(View.GONE);
        mSpinDate.setVisibility(View.GONE);
        mIvRemove.setVisibility(View.GONE);
    }

    private void clickBtnAlarm() {
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
        Log.d(TAG, "getImageChoose() called with: data = [" + data.toString() + "]");
        if (data != null) {
            mArrNoteImages.add(new NoteImage(mNote.getmIdNode(), data.getData().toString()));
            noteImageAdapter.notifyDataSetChanged();
        }
    }


    private void getImageTake(Intent data) {
        Log.d(TAG, "getImageTake() called with: data = [" + mUri.toString() + "]");
        mArrNoteImages.add(new NoteImage(mNote.getmIdNode(), mUri.toString()));
        noteImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void openCamera() {
        File destination = new File(Environment
                .getExternalStorageDirectory(), Calendar.getInstance().getTimeInMillis() + FORMAT_IMAGE);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        mUri = FileProvider.getUriForFile(getActivity(),
                BuildConfig.APPLICATION_ID + ".provider",
                destination);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
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
        if (mIvRemove.getVisibility() == View.GONE){
            mNote.setmIsAlarm(NoteUtils.NO_ALARM);
        }else{
            mNote.setmIsAlarm(NoteUtils.IS_ALARM);
        }
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
        AlarmUtils.createAlarm(getContext(), note);
        Log.d("Vuong", "updateNoteEdit() called with: getmIdNode = [" + note.getmIdNode() + "]");
        mNoteChangePresenter.requestUpdateNoteImages(mNote, mArrNoteImages);
        mCallBackAddNote.clickBtnAdd(note);
    }

    @Override
    public void updateNoteAdd(Note note) {
        AlarmUtils.createAlarm(getContext(), note);
        Log.d("Vuong", "index() called with: note = [" + note.getmIdNode() + "]");
        mNote.setmIdNode(note.getmIdNode());
        if (mArrNoteImages.size() > 0) {
            mNoteChangePresenter.requestAddNoteImages(mNote, mArrNoteImages);
        }
        mCallBackAddNote.clickBtnAdd(note);
    }

    @Override
    public void clickDeleteNoteImage(int position) {
        mArrNoteImages.remove(position);
        Log.d("Vuong", "clickDeleteNoteImage() called with: size = [" + mArrNoteImages.size() + "]");
        noteImageAdapter.notifyDataSetChanged();
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
        } else {
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
        mNote.setmDate(dateTomorrow);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public ICallBackAddNote getICallBackAddNote() {
        return this.mCallBackAddNote;
    }
}
