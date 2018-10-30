package com.example.vuongnv.noteapp.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class CustomTextView extends AppCompatEditText implements TextWatcher {
    private Paint mPaint;
    private int mLineHeight;
    private int count;
    private int mHeightView;


    public CustomTextView(Context context) {
        super(context);
        initView();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mLineHeight = getLineHeight();
        count = getLineCount();
        mHeightView = getHeight();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        count = getLineCount();
        mHeightView = getHeight();
        int count = mHeightView / mLineHeight;
        Rect rect = new Rect();
        int baseline = getLineBounds(0, rect);
        for (int i = 0; i < count; i++) {
            canvas.drawLine(rect.left, baseline + 1, rect.right, baseline + 1, mPaint);
            baseline += getLineHeight();//next line
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.d("Vuong", "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
        invalidate();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("Vuong", "afterTextChanged() called with: getLineCount = [" + getLineCount() + "]");
    }
}
