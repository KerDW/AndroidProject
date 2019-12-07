package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

public class BestScores extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);

        lv = findViewById(R.id.listView);

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class).sort("time");

        ArrayList<User> users = new ArrayList(query.findAll());

        ListAdapter adapter = new ListAdapter(
                this,
                R.layout.user_item,
                users
        );

        lv.setAdapter(adapter);

        for (User u: query.findAll()) {
            Log.e("xd", "xd"+u.getNameTime());
        }


        // if we go back to the main activity like this the user won't be able to access this activity with the back button
        // this.onBackPressed();
    }


}
