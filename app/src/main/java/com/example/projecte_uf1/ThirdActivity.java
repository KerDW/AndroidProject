package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;
    TextView timeLeftInfo;
    int timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        timeLeftInfo = findViewById(R.id.timeLeftInfo);

        intent = getIntent();
        timeLeft = intent.getIntExtra("TIME_LEFT", 0);

        timeLeftInfo.setText("You got " + timeLeft/1000 + " seconds left.");
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
