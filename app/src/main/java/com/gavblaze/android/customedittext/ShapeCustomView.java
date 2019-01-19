package com.gavblaze.android.customedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShapeCustomView extends View implements View.OnTouchListener {
    private static final int SQUARE_SIZE_DEF = 200;
    private Rect mRectSqr;
    private Paint mPaintSqr;
    private Paint mPaintCircle;

    private int mSquareColor;
    private int mSquareSize;

    private float mCx, mCy;
    private float mRadius = 100f;


    public ShapeCustomView(Context context) {
        super(context);
        init(null);
    }

    public ShapeCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ShapeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShapeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mRectSqr = new Rect();
        mPaintSqr = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSqr.setColor(Color.GREEN);

        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.MAGENTA);

        if (set == null) return;

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.ShapeCustomView);

        mSquareColor = typedArray.getColor(R.styleable.ShapeCustomView_square_color, Color.GREEN);

        mSquareSize = typedArray.getDimensionPixelSize(R.styleable.ShapeCustomView_square_size, SQUARE_SIZE_DEF);

        mPaintSqr.setColor(mSquareColor);

        typedArray.recycle();
    }

    public void swapColor() {
        if (mPaintSqr.getColor() == mSquareColor) {
            mPaintSqr.setColor(Color.RED);
        } else {
            mPaintSqr.setColor(mSquareColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectSqr.left = 50;
        mRectSqr.top = 250;
        mRectSqr.right = mRectSqr.left + mSquareSize;
        mRectSqr.bottom = mRectSqr.top + mSquareSize;

        canvas.drawRect(mRectSqr, mPaintSqr);

        /*position circle to centre of canvas*/
        if (mCx == 0f || mCy == 0f) {
            mCx = getWidth() / 2;
            mCy = getHeight() / 2;
        }

        canvas.drawCircle(mCx, mCy, mRadius, mPaintCircle);

        postInvalidate();
        setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                /*is touch inside circle?*/
                double dx = Math.pow(x - mCx, 2);
                double dy = Math.pow(y - mCy, 2);

                if (dx + dy < Math.pow(mRadius, 2)) {
                    //this is a touch event
                    mCx = x;
                    mCy = y;
                    postInvalidate();
                }
                return true;
        }
        return false;
    }
}

