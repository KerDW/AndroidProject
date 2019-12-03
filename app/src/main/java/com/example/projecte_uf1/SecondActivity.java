package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;
    CountDownTimer cdn;
    int countDownPeriod = 30000;

    ProgressBar pb;
    int progress = 0;

    ImageView megamanGif;
    EditText inputText;
    TextView textShown;
    TextView marks;
    int markCount = 0;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        megamanGif = findViewById(R.id.megaman);
        timerTV = findViewById(R.id.timerTV);
        inputText = findViewById(R.id.userInput);
        textShown = findViewById(R.id.textShown);
        marks = findViewById(R.id.marks);

        intent = getIntent();

        Glide
                .with(this) // replace with 'this' if it's in activity
                .load("http://www.google.com/.../image.gif")
                .asGif()
                .into(R.id.megaman);

        generateRandomChars(5);
        progressBarLogic();
        createTimer();

        inputText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // only check if the strings got the same length otherwise it's pointless
                if(inputText.getText().toString().length() == textShown.getText().toString().length())
                    checkInput();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
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

                while (true){
                    pb.setProgress(progress);
                    progress++;
                    if(progress == 200){
                        progress = 0;
                        generateRandomChars(5);

                        // text reset, remove previous user input
                        inputText.setText("");
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
                Toast.makeText(
                getApplicationContext(),
                "You ran out of time.",
                Toast.LENGTH_LONG).show();

                // variable resetting
                markCount = 0;

                setResult(RESULT_CANCELED, intent);
                finish();
            }
        }.start();
    }


    public void checkInput() {

        if(markCount == 5){

            // next activity or thing

        } else {
            String inputString = inputText.getText().toString();
            String generatedString = textShown.getText().toString();
            String marksString = marks.getText().toString();


            if (inputString.equals(generatedString)) {
                markCount++;

                generateRandomChars(5);
                marks.setText(markCount + marksString.substring(1));

                progress = 0;
                inputText.setText("");
            }
        }
    }
}
