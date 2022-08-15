package com.lee.bookkeep.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private int lastX,lastY;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                layout(getLeft()+offsetX,getTop()+offsetY,
//                        getRight()+offsetX,getBottom()+offsetY);
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams)getLayoutParams();
                param.leftMargin = getLeft() + offsetX;
                param.topMargin = getTop() + offsetY;
                setLayoutParams(param);
                break;
        }
        return true;
    }
}
