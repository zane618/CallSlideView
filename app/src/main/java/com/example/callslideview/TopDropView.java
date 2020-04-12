package com.example.callslideview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nineoldandroids.view.ViewHelper;

/**
 * created by zhangshi on 2020-04-12.
 */
public class TopDropView extends FrameLayout {

    private Activity mActivity;
    private FrameLayout decorView;
    View v;

    public TopDropView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public TopDropView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    int offsetY;
    int lastDownY;

    private void init(Context context) {
        mActivity = (Activity) context;
        decorView = (FrameLayout) mActivity.getWindow().getDecorView();
        v = LayoutInflater.from(mActivity).inflate(R.layout.drop_view, null);
        addView(v);
        decorView.addView(this);
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.height = 600;
//        invalidate();

        v.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastDownY = (int) event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        offsetY = y - lastDownY;
                        if (offsetY < 0) {
                            ViewHelper.setTranslationY(v, offsetY);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        if (offsetY < 0) {
                            if (Math.abs(offsetY) > v.getHeight() / 3) {
                                onDestroy();
                            } else {
                                ViewHelper.setTranslationY(v, 0f);
                            }
                        }
                        break;
                }


                return true;
            }
        });



    }

    private void onDestroy() {

        if (decorView != null) {
            decorView.removeView(this);
        }
    }
}
