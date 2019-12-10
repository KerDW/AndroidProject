package com.example.projecte_uf1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;
    Realm realm;

    TextView timerTV;
    TextView timeLeftInfo;
    Button begin;

    Fragment sdf_1;
    Fragment sdf_2;
    Fragment sdf_3;

    ArrayList<Integer> cbs = new ArrayList<>();
    int checks = 0;

    CountDownTimer cdn;
    int timeLeft;
    String userName;

    Fragment currentFrag;
    int fragmentSwapper = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        timeLeftInfo = findViewById(R.id.timeLeftInfo);
        timerTV = findViewById(R.id.timerTV2);
        begin = findViewById(R.id.beginGame);

        intent = getIntent();
        realm = Realm.getDefaultInstance();

        userName = intent.getStringExtra("USER_NAME");
        timeLeft = intent.getIntExtra("TIME_LEFT", 0);

        sdf_1 = new SecondGameDynamicFragment_1();
        sdf_2 = new SecondGameDynamicFragment_2();
        sdf_3 = new SecondGameDynamicFragment_3();

        timeLeftInfo.setText("You've got " + timeLeft/1000 + " seconds left, press start to begin the second game.");
        timerTV.setText("Seconds remaining: "+timeLeft/1000);

    }

    public void createTimer(){
        cdn = new CountDownTimer(timeLeft, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished;
                timerTV.setText("Seconds remaining: " + millisUntilFinished / 1000);

                switch(fragmentSwapper){
                    case 0:
                        currentFrag = sdf_1;
                        fragmentSwapper++;
                        break;
                    case 1:
                        currentFrag = sdf_2;
                        fragmentSwapper++;
                        break;
                    case 2:
                        currentFrag = sdf_3;
                        fragmentSwapper = 0;
                        break;
                }

                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragContainer, currentFrag).
                        commit();

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

    }

    public void beginGameTwo(View view) {

        createTimer();
        timeLeftInfo.setVisibility(View.INVISIBLE);
        begin.setVisibility(View.INVISIBLE);

    }

    public void checkBoxes(View view) {

        if(!cbs.contains(view.getId())){
            cbs.add(view.getId());
            checks++;
        } else {
            // got to get an Integer object otherwise the arraylist thinks it's an index and crashes
            cbs.remove(Integer.valueOf(view.getId()));
            checks--;
        }


        if(checks == 15){
            cdn.cancel();

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setMessage("Congratulations, you finished in time!\n" +
                    "Would you like to save your game time?");
            ad.setCancelable(false);
            ad.setPositiveButton(
            "Sure",
            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // time shouldn't be running anymore so no waste of time
                            // on user input
                            User user = new User(userName, timeLeft);

                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(user);
                            realm.commitTransaction();

                            setResult(RESULT_OK, intent);
                            finish();

                            dialog.cancel();
                        }
                    });

            ad.setNegativeButton(
            "No, thanks!",
            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            setResult(RESULT_OK, intent);
                            finish();

                            dialog.cancel();
                        }
                    });
            ad.setTitle("Game completion");
            ad.setIcon(android.R.drawable.ic_menu_save);

            AlertDialog alert = ad.create();
            alert.show();

        }

    }
}
