package com.example.testing.viewdrawdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆形周围有包裹的图片
 * Created by wangying on 2017/1/3.
 */

public class RoundImageWithSurround extends ImageView {

    private Bitmap mSourceBitmap;
    private Canvas mSourceCanvas;
    private Paint mPaint;

    private int mSurroundColor;
    private int mSurroundWidth;

    private Rect mDrawingRect = new Rect();

    public RoundImageWithSurround(Context context) {
        this(context, null);
    }

    public RoundImageWithSurround(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageWithSurround(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundImageWithSurround);
        mSurroundColor = ta.getColor(R.styleable.RoundImageWithSurround_surround_color, Color.WHITE);
        mSurroundWidth = ta.getInt(R.styleable.RoundImageWithSurround_surround_width, 10);
        ta.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDrawingRect(mDrawingRect);
        if (mSourceBitmap == null || mSourceCanvas == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mSourceBitmap = Bitmap.createBitmap(mDrawingRect.width(), mDrawingRect.height(), Bitmap.Config.ARGB_8888);
            mSourceCanvas = new Canvas(mSourceBitmap);
        }
        mSourceCanvas.drawCircle(mDrawingRect.width() / 2, mDrawingRect.height() / 2, mDrawingRect.width() / 3, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSourceBitmap, 0f, 0f, mPaint);
        mPaint.setXfermode(null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mSurroundWidth);
        mPaint.setColor(mSurroundColor);
        canvas.drawCircle(mDrawingRect.width() / 2, mDrawingRect.height() / 2, mDrawingRect.width() / 3, mPaint);
    }
}
