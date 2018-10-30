package com.example.vuongnv.noteapp.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.vuongnv.noteapp.R;


public class ColorView extends View {

    private int mColor;
    private int size;
    private Paint mPaint;

    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heighSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSize < heighSize) {
            size = widthSize;
        } else {
            size = heighSize;
        }
        setMeasuredDimension(size, size);
        Log.d("Vuong", "onMeasure() called with: widthMeasureSpec = [" + size + "], heightMeasureSpec = [" + size + "]");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setAlpha(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        Log.d("Vuong", "onDraw() called with: canvas = [" + mColor + "]");
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, size, size), mPaint);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ColorView,
                0, 0);
        mColor = typedArray.getColor(R.styleable.ColorView_color_background, Color.RED);
        Log.d("vuong", "initView() called with: context = [" + context + "], mColor = [" + mColor + "]");
    }
}
