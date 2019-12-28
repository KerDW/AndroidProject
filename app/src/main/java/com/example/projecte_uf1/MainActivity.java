package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Map;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    String userSelected;
    Toolbar toolbar;
    Spinner users;
    EditText password;
    EditText newUser;
    EditText newPass;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> usersList;
    ArrayAdapter<String> adapter;
    RadioButton easy;
    RadioButton medium;
    RadioButton hard;
    RadioButton extreme;
    View.OnClickListener diffListener;

    public static final int GAME_START = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        password = findViewById(R.id.password);
        newPass = findViewById(R.id.newPass);
        users = findViewById(R.id.userSpinner);
        newUser = findViewById(R.id.newUser);
        toolbar = findViewById(R.id.toolbar);
        easy = findViewById(R.id.diffEasy);
        medium = findViewById(R.id.diffMedium);
        hard = findViewById(R.id.diffHard);
        extreme = findViewById(R.id.diffExtreme);

        setSupportActionBar(toolbar);

        // Initialize Realm
        Realm.init(this);

        // in case I need to reset the db schema
        // Realm.deleteRealm(Realm.getDefaultConfiguration());

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        usersList = new ArrayList<String>();
        spinnerSetup();
        Difficulty.setDifficulty("easy");

        diffListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch(v.getTag().toString()){
                    case "easy":
                        Difficulty.setDifficulty("easy");
                        break;
                    case "medium":
                        Difficulty.setDifficulty("medium");
                        break;
                    case "hard":
                        Difficulty.setDifficulty("hard");
                        break;
                    case "extreme":
                        Difficulty.setDifficulty("extreme");
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
        };

        easy.setOnClickListener(diffListener);
        medium.setOnClickListener(diffListener);
        hard.setOnClickListener(diffListener);
        extreme.setOnClickListener(diffListener);

        // load megaman gif for the second activity into cache
        Glide.with(this)
                .load("https://gifimage.net/wp-content/uploads/2017/10/megaman-running-gif-1.gif")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .preload();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if the user doesn't lose on time this will happen
        if (requestCode == GAME_START && resultCode == RESULT_OK) {



        }
    }

    public void play(View view) {

        Log.e("xd", ""+Difficulty.getTime());

        if(userSelected == null || userSelected.equals("")){
            Toast.makeText(
            getApplicationContext(),
            "You need to create a user first.",
            Toast.LENGTH_LONG).show();
            return;
        }

        if(password.getText().toString().equals(sharedPref.getString(userSelected, ""))){
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("USER_NAME", userSelected);
            startActivityForResult(intent, GAME_START);
        } else {
            Toast.makeText(
            getApplicationContext(),
            "Wrong password.",
            Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {

        if(newUser.getText().toString().equals("")){
            Toast.makeText(
            getApplicationContext(),
            "The username can't be empty.",
            Toast.LENGTH_SHORT).show();

            return;
        }

        if(usersList.contains(newUser.getText().toString())){

            Toast.makeText(
            getApplicationContext(),
            "This username is already taken.",
            Toast.LENGTH_LONG).show();

            return;
        }

        editor.putString(newUser.getText().toString(), newPass.getText().toString());
        editor.apply();

        Toast.makeText(
        getApplicationContext(),
        "User created.",
        Toast.LENGTH_SHORT).show();

        usersList.add(newUser.getText().toString());
        adapter.notifyDataSetChanged();

    }

    public void spinnerSetup(){

        Map<String,?> sharedPrefUsers = sharedPref.getAll();

        for (Map.Entry<String, ?> entry : sharedPrefUsers.entrySet()) {
            if(entry.getKey() != null){
                usersList.add(entry.getKey());
                // Log.e("xd", "xd"+entry.getKey());
            }
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,usersList);
        // clear empties list, addAll adds a whole arraylist and notify refreshes the data
        // adapter.clear();
        // adapter.addAll(usersList);
        // adapter.notifyDataSetChanged();

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        users.setAdapter(adapter);

        users.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                userSelected = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    public void bestScores(MenuItem item) {

        startActivity(new Intent(this, BestScores.class));

    }

    public void resetUsers(View view) {

        editor.clear();
        editor.apply();

        userSelected = null;
        usersList.clear();
        adapter.notifyDataSetChanged();

    }
}
