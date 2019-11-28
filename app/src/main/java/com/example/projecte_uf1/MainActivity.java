package com.example.projecte_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner users;
    EditText password;
    EditText newUser;
    EditText newPass;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

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
        Map<String,?> sharedPrefUsers = sharedPref.getAll();
        List<String> userString = sharedPrefUsers.entrySet().

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,items);

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

    public void login(View view) {



    }
}
