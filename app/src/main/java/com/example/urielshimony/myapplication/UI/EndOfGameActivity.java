package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.GameManager;

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
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();
        this.gameResult = extras.getString("gameResult");
        if (gameResult.equals("win")) {
            findViewById(R.id.end_of_game_actity).setBackgroundResource(R.drawable.baloon);
            findViewById(R.id.end_of_game_image).setBackgroundResource(R.drawable.win_image);
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

    private void createLevelActivity()
    {
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra("name", this.name);
        intent.putExtra("date_of_birth", this.date);
        startActivity(intent);
    }

}
