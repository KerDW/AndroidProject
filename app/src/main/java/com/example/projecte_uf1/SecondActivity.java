package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;
    CountDownTimer cdn;
    int countDownPeriod = 5000;
    int countDownInterval = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        timerTV = findViewById(R.id.timerTV);

        createTimer();
    }

    public void createTimer(){
        cdn = new CountDownTimer(countDownPeriod, countDownInterval) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // here we could stop whatever the main thread is doing and throw the user out of the app
                Toast.makeText(
                getApplicationContext(),
                "Unfortunately, you ran out of time.",
                Toast.LENGTH_LONG).show();
    
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}
