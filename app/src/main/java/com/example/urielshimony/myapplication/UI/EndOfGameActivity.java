package com.example.urielshimony.myapplication.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.GameManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import static com.example.urielshimony.myapplication.FinalStrings.DATE_OF_BIRTH;
import static com.example.urielshimony.myapplication.FinalStrings.GAME_RESULT;
import static com.example.urielshimony.myapplication.FinalStrings.PLAYER_NAME;

public class EndOfGameActivity extends AppCompatActivity {

    private String gameResult;
    private String name;
    private String date;

    final static int DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_game);
        setTitle("");
        Bundle extras = getIntent().getExtras();
        this.name = extras.getString(PLAYER_NAME);
        this.date = getIntent().getSerializableExtra(DATE_OF_BIRTH).toString();
        this.gameResult = extras.getString(GAME_RESULT);
        if (gameResult.equals("win")) {
            findViewById(R.id.end_of_game_actity).setBackgroundResource(R.drawable.baloon);
            findViewById(R.id.end_of_game_image).setBackgroundResource(R.drawable.winner_red);
            YoYo.with(Techniques.Tada)
                    .duration(400)
                    .repeat(7)
                    .playOn(findViewById(R.id.end_of_game_image));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    createMenuActivity();
                    finish();
                }
            }, DELAY_TIME);
        } else if (gameResult.equals("lose")) {
            findViewById(R.id.end_of_game_actity).setBackgroundColor(Color.RED);
            findViewById(R.id.end_of_game_image).setBackgroundResource(R.drawable.game_over);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    createLevelActivity();
                    finish();
                }
            }, DELAY_TIME);
        }
    }

    private void createLevelActivity() {
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra(PLAYER_NAME, this.name);
        intent.putExtra(DATE_OF_BIRTH, this.date);
        startActivity(intent);
    }

    private void createMenuActivity()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(PLAYER_NAME, this.name);
        intent.putExtra(DATE_OF_BIRTH, this.date);
        startActivity(intent);
    }

}
