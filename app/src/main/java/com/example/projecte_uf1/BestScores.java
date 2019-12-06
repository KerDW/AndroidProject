package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BestScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);

        // if we go back to the main activity like this the user won't be able to access this activity with the back button
        // this.onBackPressed();
    }


}
