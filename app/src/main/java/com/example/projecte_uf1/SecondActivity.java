package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    int timeLeft;

    ProgressBar pb;
    int progress = 100;

    ImageView megamanGif;
    EditText inputText;
    TextView textShown;
    TextView marks;
    TextView prog;
    int markCount = 0;

    Intent intent;

    public static final int GAME_START2 = 1;


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

        // loading gif instantly since it was preloaded in the first activity
        Glide.with(this)
                .load("https://gifimage.net/wp-content/uploads/2017/10/megaman-running-gif-1.gif")
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
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

                    // view updates must run on the UI thread to avoid crashes
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            prog.setText(String.valueOf(progress));

                        }
                    });

                    if(progress <= 0){
                        progress = 100;

                        // UI thread
                        // text reset,string generation and remove previous user input
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
        cdn = new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished;
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

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GAME_START2 && resultCode == RESULT_CANCELED) {

            setResult(RESULT_CANCELED, intent);
            finish();

        } else{

            setResult(RESULT_OK, intent);
            finish();

        }
    }

    public void checkInput() {

        if(markCount == 0){
            cdn.cancel();

            Intent intent2 = new Intent(this, ThirdActivity.class);
            intent2.putExtra("TIME_LEFT", timeLeft);
            intent2.putExtra("USER_NAME", intent.getStringExtra("USER_NAME"));
            startActivityForResult(intent2, GAME_START2);

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
