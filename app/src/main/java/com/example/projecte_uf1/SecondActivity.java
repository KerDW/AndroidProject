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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView timerTV;
    CountDownTimer cdn;
    int countDownPeriod = 30000;

    ProgressBar pb;
    int progress = 100;

    ImageView megamanGif;
    EditText inputText;
    TextView textShown;
    TextView marks;
    TextView prog;
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
        prog = findViewById(R.id.prog);

        intent = getIntent();


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


        Glide.with(this)
                .load("https://gifimage.net/wp-content/uploads/2017/10/megaman-running-gif-1.gif")
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(megamanGif);
    }

    public void generateRandomChars(int letterNo){

        Random rand = new Random();
        String randomChars = "";

        String letters = "abcdefghijklmnopqrstuvxyz";

        for (int i = 0;i < letterNo; i++){
            randomChars += letters.charAt(rand.nextInt(25));
        }

        textShown.setText(randomChars);
    }

    public void progressBarLogic(){

        Runnable r = new Runnable() {
            public void run() {

                pb = findViewById(R.id.progressBar);

                while (true){
                    pb.setProgress(progress);
                    progress--;

                    // view updates must run on the UI thread
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            prog.setText(String.valueOf(progress));

                        }
                    });

                    if(progress <= 0){
                        progress = 100;

                        // text reset,string generation and remove previous user input
                        // same as before, UI thread
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                generateRandomChars(5);
                                inputText.setText("");

                            }
                        });
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

    @Override
    public void onBackPressed()
    {
        // user can't back
    }

    public void checkInput() {

        if(markCount == 5){

            cdn.cancel();
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
