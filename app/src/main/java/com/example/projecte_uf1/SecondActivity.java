package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;
    CountDownTimer cdn;
    int countDownPeriod = 30000;
    ProgressBar pb1;
    ProgressBar pb2;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        timerTV = findViewById(R.id.timerTV);
        intent = getIntent();
        progressBarLogic();
        createTimer();
    }

    public void progressBarLogic(){

        Runnable r = new Runnable() {
            public void run() {

                pb1 = findViewById(R.id.progressBar);
                pb2 = findViewById(R.id.progressBar2);

                int pr = 0;

                while (true){
                    pb1.setProgress(pr);
                    pb2.setProgress(pr);
                    pr++;
                    if(pr == 100){
                        pr = 0;

                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(r).start();

    }

    public void createTimer(){
        cdn = new CountDownTimer(countDownPeriod, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // here we could stop whatever the main thread is doing and throw the user out of the app
                Toast.makeText(
                getApplicationContext(),
                "You ran out of time.",
                Toast.LENGTH_LONG).show();

                setResult(RESULT_CANCELED, intent);
                finish();
            }
        }.start();
    }


}
