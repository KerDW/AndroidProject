package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

public class BestScores extends AppCompatActivity {

    ListView lv;
    Toolbar toolbar;
    Realm realm;

    ArrayList<User> users;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_scores);

        lv = findViewById(R.id.listView);
        toolbar = findViewById(R.id.scoresToolbar);

        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class).sort("time", Sort.DESCENDING);
        users = new ArrayList<>(query.findAllAsync());

        adapter = new ListAdapter(
                this,
                R.layout.user_item,
                users
        );



        lv.setAdapter(adapter);



    }

    public void goBack(MenuItem item){

        this.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scores_menu, menu);

        return true;
    }

    public void resetlb(View view) {

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

        users.clear();

        adapter.notifyDataSetChanged();

    }
}
