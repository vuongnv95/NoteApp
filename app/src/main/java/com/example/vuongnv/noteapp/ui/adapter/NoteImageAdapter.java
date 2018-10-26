package com.example.vuongnv.noteapp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vuongnv.noteapp.R;

import java.util.ArrayList;

public class NoteImageAdapter extends RecyclerView.Adapter<NoteImageAdapter.ViewHolder> {
    private ArrayList<Bitmap> mArrBitmap;
    private LayoutInflater mInflater;

    public NoteImageAdapter(Context context,ArrayList<Bitmap> mArrBitmap) {
        this.mArrBitmap = mArrBitmap;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_imagenote, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.mIvItem.setImageBitmap(mArrBitmap.get(i));
    }

    @Override
    public int getItemCount() {
        return mArrBitmap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvItem = itemView.findViewById(R.id.iv_item_image);
        }
    }
}
