package com.gavblaze.android.customedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextWithClear extends android.support.v7.widget.AppCompatEditText {
    private Drawable mDrawable;


    public EditTextWithClear(Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    public void init() {
        mDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
        setCompoundDrawablesWithIntrinsicBounds(null, null, mDrawable, null);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                                EditTextWithClear et = (EditTextWithClear) v;

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if(event.getX() >= (et.getRight() - et.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            et.setText("");
                            Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                }
                return false;
            }
        });
    }
}
