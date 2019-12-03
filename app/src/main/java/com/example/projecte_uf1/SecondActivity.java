package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;
    CountDownTimer cdn;
    int countDownPeriod = 30000;

    ProgressBar pb;
    EditText inputText;
    TextView textShown;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        timerTV = findViewById(R.id.timerTV);
        inputText = findViewById(R.id.userInput);
        textShown = findViewById(R.id.textShown);

        intent = getIntent();

        generateRandomChars(5);
        progressBarLogic();
        createTimer();
    }

    public void generateRandomChars(int letterNo){

        Random rand = new Random();
        String randomChars = "";

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";

        for (int i = 0;i < letterNo; i++){
            randomChars += letters.charAt(rand.nextInt(51));
        }

        textShown.setText(randomChars);
    }

    public void progressBarLogic(){

        Runnable r = new Runnable() {
            public void run() {

                pb = findViewById(R.id.progressBar);

                int pr = 0;

                while (true){
                    pb.setProgress(pr);
                    pr++;
                    if(pr == 200){
                        pr = 0;
                        generateRandomChars(5);
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


    public void checkInput(View view) {

        String inputString = inputText.getText().toString();
        String generatedString = textShown.getText().toString();

        if(inputString.equals(generatedString)){

        }

    }
}
