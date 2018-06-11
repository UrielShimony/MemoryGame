package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.urielshimony.myapplication.R;

import static com.example.urielshimony.myapplication.FinalStrings.DATE_OF_BIRTH;
import static com.example.urielshimony.myapplication.FinalStrings.PLAYER_NAME;

public class MenuActivity extends ParentActivity {

    private String date;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString(PLAYER_NAME);
        this.date = getIntent().getSerializableExtra(DATE_OF_BIRTH).toString();



    }

    public void play(View view)
    {
        //create  choseDifficultActivity
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra(PLAYER_NAME, name);
        intent.putExtra(DATE_OF_BIRTH, date);
        startActivity(intent);

    }

    public void showScoreTable(View view)
    {
        //create  HighScoreActivity
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);

    }

}
