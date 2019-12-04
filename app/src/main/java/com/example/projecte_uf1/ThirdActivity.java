package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;

    TextView timerTV;
    CountDownTimer cdn;
    TextView timeLeftInfo;
    int timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        timeLeftInfo = findViewById(R.id.timeLeftInfo);
        timerTV = findViewById(R.id.timerTV2);

        intent = getIntent();
        timeLeft = intent.getIntExtra("TIME_LEFT", 0);
        timeLeftInfo.setText("You've got " + timeLeft/1000 + " seconds left.");

        createTimer();

    }

    public void createTimer(){
        cdn = new CountDownTimer(timeLeft, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished;
                timerTV.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(
                        getApplicationContext(),
                        "You ran out of time.",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
            }
        }.start();
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(
        getApplicationContext(),
        "There is no escape.",
        Toast.LENGTH_LONG).show();
    }



}
