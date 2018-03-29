package com.example.urielshimony.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private String date;
    private String dificultLvl;
    private String name;
    private boolean winner = false;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();
        this.dificultLvl = extras.getString("difcult_lvl");

        ((TextView) findViewById(R.id.dificultLevelLable)).setText("the dificult is: " + this.dificultLvl);

        switch (this.dificultLvl) {
            case "Easy":
                this.seconds = 30;
                break;
            case "Medium":
                this.seconds = 45;
                break;
            case "Hard":
                this.seconds = 60;
                break;
        }
        this.StartTimer(this.seconds);

    }

    private void StartTimer(int timeToStop) {
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int timeToStop = message.getData().getInt("seconds");
                tick(timeToStop);
                if (timeToStop != 0) {
                    StartTimer(--timeToStop);
                    return false;
                }else{
                    endGame();
                }
                return true;
            }
        });
        Message message = new Message();
        Bundle messageData = new Bundle();
        messageData.putInt("seconds", timeToStop);
        message.setData(messageData);
        handler.sendMessageDelayed(message, 1000);
    }

    private void tick(int seconds) {
        this.seconds=seconds;
        ((TextView)findViewById(R.id.Timer)).setText(""+seconds);

    }
    private void endGame(){

    }
}
