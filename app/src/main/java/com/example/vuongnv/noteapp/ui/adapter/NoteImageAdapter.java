package com.example.vuongnv.noteapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vuongnv.noteapp.R;
import com.example.vuongnv.noteapp.data.db.model.NoteImage;
import com.example.vuongnv.noteapp.ui.addnote.NoteChangePresenter;

import java.io.IOException;
import java.util.ArrayList;

public class NoteImageAdapter extends RecyclerView.Adapter<NoteImageAdapter.ViewHolder> {
    private ArrayList<NoteImage> mNoteImages;
    private LayoutInflater mInflater;
    private Context mContext;
    private NoteChangePresenter mNoteChangePresenter;

    public NoteImageAdapter(Context context, ArrayList<NoteImage> noteImages, NoteChangePresenter noteChangePresenter) {
        this.mNoteImages = noteImages;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mNoteChangePresenter = noteChangePresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_imagenote, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Bitmap bitmap = convertPathToBitmap(mNoteImages.get(i).getmPath());
        Log.d("Vuong", "onBindViewHolder() called with: mNoteImages.get(i).getmPath() = [" + mNoteImages.get(i).getmPath() + "], i = [" + i + "]");
        if (bitmap != null) {
            viewHolder.mIvItem.setImageBitmap(bitmap);
            Log.d("Vuong", "onBindViewHolder() called with: bitmap != null");
        }
        viewHolder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteChangePresenter.clickImageItem(i);
                Log.d("Vuong", "onClick() called with: i = [" + i + "]");
            }
        });
    }

    private Bitmap convertPathToBitmap(String path) {
        if (path != null && !path.isEmpty()) {
            try {
                return MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mNoteImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mIvItem;
        ImageView mIvDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvItem = itemView.findViewById(R.id.iv_item_image);
            mIvDelete = itemView.findViewById(R.id.iv_item_remove);
            mIvDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Vuong", "onClick() called with: v = [" + this.getPosition() + "]");
            mNoteChangePresenter.clickImageItem(this.getPosition());
        }
    }
}
