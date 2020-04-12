package com.example.callslideview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.nineoldandroids.view.ViewHelper;

/**
 * created by zhangshi on 2020-04-12.
 */
public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        this(context, R.style.MyDialog);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    int offsetY;
    int lastDownY;

    private void init(Context context) {
        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.TOP);
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mWindow.setWindowAnimations(R.style.TopAnimation);
        mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        View view = LayoutInflater.from(context).inflate(R.layout.drop_view, null);
        setContentView(view);
        view.setOnTouchListener(new View.OnTouchListener() {
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
                            v.setY(offsetY);

//                            ViewHelper.setTranslationY(v, offsetY);

//                            Animator animator = ObjectAnimator.ofFloat(v, "translationY", offsetY);
//                            animator.setDuration(1);
//                            animator.start();
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        if (offsetY < 0) {
                            if (Math.abs(offsetY) > v.getHeight() / 3) {
                                dismiss();
                            } else {
//                                ViewHelper.setTranslationY(v, 0f);

//                                v.setY(0);

                                Animator animator = ObjectAnimator.ofFloat(v, "translationY", 0f);
                                animator.setDuration(300);
                                animator.start();
                            }
                        }
                        break;
                }


                return true;
            }
        });

    }
}
