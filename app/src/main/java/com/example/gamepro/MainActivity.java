package com.example.gamepro;

import static com.example.gamepro.R.id.androidButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        final Button startButton = findViewById(androidButton);

        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startButton.getBackground().setAlpha(100);

                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent =new Intent(MainActivity.this,GameActivity.class);
                        startActivity(intent);
                        startButton.getBackground().setAlpha(255);

                        break;

                    case MotionEvent.ACTION_MOVE:

                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }
}