package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.urielshimony.myapplication.R;

public class EndOfGameActivity extends AppCompatActivity {

    private String gameResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_game);
        setTitle("");
        Bundle extras = getIntent().getExtras();
        this.gameResult = extras.getString("gameResult");
        if(gameResult.equals("win")) {
            findViewById(R.id.end_of_game_actity).setBackgroundResource(R.drawable.baloon);
            findViewById(R.id.end_of_game_image).setBackgroundResource(R.drawable.win_image);
        }else if(gameResult.equals("lose")) {
            findViewById(R.id.end_of_game_actity).setBackgroundColor(Color.RED);
            findViewById(R.id.end_of_game_image).setBackgroundResource(R.drawable.game_over);
        }
        try {
            Thread.sleep(10000);
            //finish();
            //TODO go back to choose level acticity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
