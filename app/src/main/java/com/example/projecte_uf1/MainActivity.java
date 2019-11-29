package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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



    }

    public void login(View view) {

        if(password.getText().toString().equals(sharedPref.getString(userSelected, ""))){

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
