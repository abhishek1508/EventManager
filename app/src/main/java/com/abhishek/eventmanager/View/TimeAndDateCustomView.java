package com.abhishek.eventmanager.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Abhishek on 2/28/2016.
 */
public class TimeAndDateCustomView extends View {

    private Paint paint;
    private Paint textPaint;
    private float mWidth;
    private float mHeight;

    private String mCircleText = "Hello";
    private String mTime;
    private String mDate;

    private float mRadius = 110.0f;
    private RectF rect = null;

    public TimeAndDateCustomView(Context context) {
        super(context);
    }

    public TimeAndDateCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // create the Paint and set its color
        paint = new Paint();
        textPaint = new Paint();
        //Example values
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        rect = new RectF();
    }

    public TimeAndDateCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        rect.set(mWidth / 2 - mRadius, mHeight / 2 - mRadius, mWidth / 2 + mRadius, mHeight / 2 + mRadius);
        canvas.drawArc(rect, 180, 360, false, paint);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(35);
        canvas.drawText(mCircleText, mWidth / 2, mHeight / 2, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = measure(widthMeasureSpec);
        int measuredHeight = measure(heightMeasureSpec);

        int d = Math.min(measuredWidth, measuredHeight);

        setMeasuredDimension(d, d);
    }

    private int measure(int measureSpec) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.UNSPECIFIED)
            result = 200;
        else
            result = specSize;

        return result;
    }

    public void setTimeText(String newLabel){
        //update the instance variable
        mCircleText = newLabel;
        mTime = mCircleText;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setDateText(String newLabel){
        //update the instance variable
        mCircleText = newLabel;
        mDate = mCircleText;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public String getTime() {
        return mTime;
    }

    public String getDate() {
        return mDate;
    }
}
