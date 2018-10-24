package com.example.vuongnv.noteapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    private ArrayList<Note> mArrNote;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public NoteAdapter(Context context, ArrayList<Note> arrNote) {
        this.mContext = context;
        this.mArrNote = arrNote;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mArrNote.isEmpty()? 0 : mArrNote.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrNote.isEmpty()?null:mArrNote.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_note,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvDate = convertView.findViewById(R.id.tv_item_date);
            viewHolder.mTvTime = convertView.findViewById(R.id.tv_item_time);
            viewHolder.mTvTitle = convertView.findViewById(R.id.tv_item_title);
            viewHolder.mTvSubject = convertView.findViewById(R.id.tv_item_subject);
            viewHolder.mRlItem = convertView.findViewById(R.id.rl_item_note);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Note note = mArrNote.get(position);
        viewHolder.mTvTitle.setText(note.getmTitle());
        viewHolder.mTvSubject.setText(note.getmSubject());
        viewHolder.mTvDate.setText(note.getmDate());
        viewHolder.mTvTime.setText(note.getmTime());
        viewHolder.mRlItem.setBackgroundColor(note.getmColor());
        return convertView;
    }

    private class ViewHolder{
        TextView mTvTitle;
        TextView mTvSubject;
        TextView mTvTime;
        TextView mTvDate;
        RelativeLayout mRlItem;
    }
}
