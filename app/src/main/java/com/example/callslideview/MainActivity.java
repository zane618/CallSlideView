package com.example.callslideview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                finish();
            }

            @Override
            public void onSliderListen() {
                Toast.makeText(MainActivity.this, "接听", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
