package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;

    TextView timerTV;
    TextView timeLeftInfo;
    Button begin;

    CountDownTimer cdn;
    int timeLeft;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        timeLeftInfo = findViewById(R.id.timeLeftInfo);
        timerTV = findViewById(R.id.timerTV2);
        begin = findViewById(R.id.beginGame);

        intent = getIntent();
        userName = intent.getStringExtra("USER_NAME");
        timeLeft = intent.getIntExtra("TIME_LEFT", 0);

        timeLeftInfo.setText("You've got " + timeLeft/1000 + " seconds left, press start to begin the second game.");
        timerTV.setText("Seconds remaining: "+timeLeft/1000);

        Realm realm = Realm.getDefaultInstance();

        User user = new User(userName, timeLeft);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

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

                setResult(RESULT_CANCELED, intent);
                finish();
            }
        }.start();
    }

    @Override
    public void onBackPressed()
    {

    }

    public void beginGameTwo(View view) {

        createTimer();
        timeLeftInfo.setVisibility(View.INVISIBLE);
        begin.setVisibility(View.INVISIBLE);

    }
}
