package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String userSelected;
    Spinner users;
    EditText password;
    EditText newUser;
    EditText newPass;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> usersList;
    ArrayList<String> passwordList;
    ArrayAdapter<String> adapter;

    public static final int GAME_START = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        password = findViewById(R.id.password);
        newPass = findViewById(R.id.newPass);
        users = findViewById(R.id.userSpinner);
        newUser = findViewById(R.id.newUser);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        usersList = new ArrayList<String>();
        spinnerSetup();

        // preload megaman gif for the second activity so there's no delay
        Glide.with(this)
                .load("https://gifimage.net/wp-content/uploads/2017/10/megaman-running-gif-1.gif")
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .preload();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GAME_START && resultCode == RESULT_CANCELED) {

            editor.remove(userSelected);
            editor.apply();

            usersList.remove(userSelected);
            adapter.notifyDataSetChanged();

        } else{

        }
    }

    public void login(View view) {

        if(password.getText().toString().equals(sharedPref.getString(userSelected, ""))){
            Intent intent = new Intent(this, SecondActivity.class);
            startActivityForResult(intent, GAME_START);
        } else {
            Toast.makeText(
            getApplicationContext(),
            "Wrong password.",
            Toast.LENGTH_LONG).show();
        }
    }

    public void register(View view) {

        if(usersList.contains(newUser.getText().toString())){

            Toast.makeText(
            getApplicationContext(),
            "This username already taken, choose a different one, please.",
            Toast.LENGTH_LONG).show();

            return;
        }

        editor.putString(newUser.getText().toString(), newPass.getText().toString());
        editor.apply();

        Toast.makeText(
        getApplicationContext(),
        "User created.",
        Toast.LENGTH_LONG).show();

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
}
