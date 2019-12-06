package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;

    TextView timerTV;
    CountDownTimer cdn;
    TextView timeLeftInfo;
    int timeLeft;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        timeLeftInfo = findViewById(R.id.timeLeftInfo);
        timerTV = findViewById(R.id.timerTV2);

        intent = getIntent();
        userName = intent.getStringExtra("USER_NAME");
        timeLeft = intent.getIntExtra("TIME_LEFT", 0);
        timeLeftInfo.setText("You've got " + timeLeft/1000 + " seconds left.");

        Realm realm = Realm.getDefaultInstance();

        User user = new User(userName, timeLeft);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

//        RealmQuery<User> query = realm.where(User.class);
//
//        Log.e("xd", "xd"+query.equalTo("name", userName).findFirst().getName());

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

                setResult(RESULT_CANCELED, intent);
                finish();
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
