package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        timerTV = findViewById(R.id.timerTV);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // here we could stop whatever the main thread is doing and throw the user out of the app

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

    }
}
