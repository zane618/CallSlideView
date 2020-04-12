package com.example.callslideview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallSliderView callView = (CallSliderView) findViewById(R.id.slider_view);
        callView.setSliderEndListener(new CallSliderView.SliderListener() {
            @Override
            public void onSliderEnd() {
                Toast.makeText(MainActivity.this, "挂断", Toast.LENGTH_SHORT).show();
                new TopDropView(MainActivity.this);
            }

            @Override
            public void onSliderListen() {
                Toast.makeText(MainActivity.this, "接听", Toast.LENGTH_SHORT).show();
                new MyDialog(MainActivity.this).show();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new TopDropView(MainActivity.this);
            }
        }, 1);
    }

}
