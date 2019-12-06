package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmQuery;

public class BestScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class);

        Log.e("xd", "xd"+query.equalTo("name", "x").findFirst().getName());

        // if we go back to the main activity like this the user won't be able to access this activity with the back button
        // this.onBackPressed();
    }


}
