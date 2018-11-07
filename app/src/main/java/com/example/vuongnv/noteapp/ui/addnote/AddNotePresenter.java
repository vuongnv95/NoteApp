package com.example.vuongnv.noteapp.ui.addnote;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.DataManager;
import com.example.vuongnv.noteapp.data.db.model.Note;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.ui.base.BasePresenter;
import com.example.vuongnv.noteapp.utils.AlarmUtils;
import com.example.vuongnv.noteapp.utils.NoteUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AddNotePresenter<V extends AddNoteView> extends BasePresenter<V> implements AddNoteMVPPresenter<V> {
    private final int NUMBER_SELECT_SPINNER = 2;
    private final int VALUE_TODAY = 0;
    private final int VALUE_TOMORROW = 1;
    private final int VALUE_NEXTWEEK = 7;
    private final int SELECT_POSITION = 2;
    public final String FORMAT_DATE = "MM/dd/yyyy";
    private final String OTHER_TIME = "Other";
    private final String OTHER_DATE = "Other";
    private int numSelectSpin = 0;
    private Context mContext;
    private ArrayList<String> arrDate;
    private ArrayList<String> arrTime;

    @Inject
    public AddNotePresenter(DataManager mNoteDataManager, CompositeDisposable mCompositeDisposable, Context context) {
        super(mNoteDataManager, mCompositeDisposable);
        this.mContext = context;
        arrDate = new ArrayList<>();
        arrTime = new ArrayList<>();
    }


    @Override
    public void requestUpdateNode(Note note) {
        getDataManager().updateNote(note).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("Vuong", "accept() called with: integer = [" + integer + "]");
            }
        });
        getView().updateNoteEdit(note);
//        mNoteChangeIndicator.updateNode(note, this);
    }

    @Override
    public void requestAddNote(final Note note, final ArrayList<NoteImage> noteImages, final Context context) {
        getDataManager().addNote(note).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                note.setmIdNode(aLong.intValue());
                AlarmUtils.createAlarm(context, note);
                if (!noteImages.isEmpty()) {
                    getDataManager().addNoteImage(note, noteImages).subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.d("Vuong", "accept() called with: aLong = [" + aLong + "]");
                        }
                    });
                }
                getView().updateNoteAdd(note);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("Vuong", "accept() called with: throwable = [" + throwable + "]");
            }
        });
//        long index = getDataManager().addNote(note);
        /// TODO: 11/7/2018 return  id note to note of change
        /// TODO: 11/7/2018  if save complete
//        mNoteChangeIndicator.addNote(note, this);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void requestNoteImages(final Note note) {
        getDataManager().getAllNoteImages(note).subscribe(new Consumer<List<NoteImage>>() {
            @Override
            public void accept(List<NoteImage> noteImages) throws Exception {
                getView().updateNoteImages(noteImages);
            }
        });
//        return mNoteChangeIndicator.getAllNoteImages(note);
    }

    @Override
    public void requestClickBtnBack() {
        getView().updateClickBtnBack();
    }

//    @Override
//    public void requestAddNoteImages(Note note, List<NoteImage> arrNoteImages) {
//        getDataManager().addNoteImage(note,arrNoteImages);
////        mNoteChangeIndicator.addNoteImages(note, arrNoteImages);
//    }

    @Override
    public void requestUpdateNoteImages(Note note, List<NoteImage> arrNoteImages) {
        getDataManager().updateNoteImage(note, arrNoteImages).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });
//        mNoteChangeIndicator.updateNoteImages(note, arrNoteImages);
    }

    @Override
    public void requestclickBtnGallery() {
        getView().updateClickBtnGallery();
    }

    @Override
    public void requestClickBtnDelete() {
        getView().updateClickBtnDelete();
    }

    @Override
    public void requestClickBtnAlarm() {
        getView().updateClickBtnAlarm();
    }

    @Override
    public void requestClickBtnAdd() {

    }

    @Override
    public void requestClickBtnMore(View view) {
        getView().updateClickBtnMore(view);
    }

    @Override
    public void requestClickDeleteImageItem(int position) {
        getView().updateClickDeleteNoteImage(position);
    }

    @Override
    public void requestDataNoteDate(Note note) {
        arrDate.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.spin_date)));
        if (note.getmIsAlarm() == NoteUtils.IS_ALARM) {
            arrDate.set((arrDate.size() - 1), note.getmDate());
            arrDate.add(OTHER_DATE);
        }
        getView().updateSpinnerDate(arrDate);
    }

    @Override
    public void requestDataNoteTime(Note note) {
        arrTime.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.spin_time)));
        if (note.getmIsAlarm() == NoteUtils.IS_ALARM) {
            arrTime.set((arrTime.size() - 1), note.getmTime());
            arrTime.add(OTHER_TIME);
        }
        getView().updateSpinnerTime(arrTime);
    }

    @Override
    public void requestClickBtnCamera() {
        getView().updateClickBtnCamera();
    }

    @Override
    public void requestClickItemSpinnerTime(AdapterView<?> parent, ArrayAdapter<String> adapter, int position) {
        numSelectSpin += 1;
        if (numSelectSpin <= NUMBER_SELECT_SPINNER) {
            return;
        }
        if (parent.getAdapter() == adapter) {
            getDateSpinner(position);
        } else {
            getTimeSpinner(position);
        }
    }

    @Override
    public void getTimeSpinner(int position) {
        int sizeArrTime = arrTime.size() - 1;
        if (sizeArrTime == position) {
            getView().showTimePicker();
        } else {
            getView().updateTime(arrTime.get(position));
        }
    }

    @Override
    public void requestClickTimePicker(int hourOfDay, int minute) {
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        int indexArr = arrTime.size() - 1;
        arrTime.set(indexArr, time);
        arrTime.add(OTHER_TIME);
        getView().updateTimePicker(time, arrTime);
    }

    @Override
    public void requestClickDatePicker(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dateFormat = FORMAT_DATE;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        String date = sdf.format(calendar.getTime());
        int indexArr = arrDate.size() - 1;
        arrDate.set(indexArr, date);
        arrDate.add(OTHER_DATE);
        getView().updateDatePicker(date, arrDate);
//        mDateAdapter.notifyDataSetChanged();
//        mSpinDate.setSelection(indexArr);
//        updateTextDate();
    }

    @Override
    public void getDateSpinner(int position) {
        int sizeArrDate = arrDate.size() - 1;
        if (sizeArrDate == position) {
            getView().showDatePicker();
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
                    getView().updateDate(String.valueOf(arrDate.get(position)));
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
        getView().updateDate(dateTomorrow);
    }

    @Override
    public void requestClickItemSpinnerDate() {

    }
}
