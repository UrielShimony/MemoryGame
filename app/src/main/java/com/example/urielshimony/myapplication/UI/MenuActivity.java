package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.urielshimony.myapplication.R;

public class MenuActivity extends AppCompatActivity {

    private String date;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();



    }

    public void play(View view)
    {
        //create  choseDifficultActivity
        Intent intent = new Intent(this, choseDifficultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("date_of_birth", date);
        startActivity(intent);

    }

    public void showScoreTable(View view)
    {
        //create  HighScoreActivity
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);

    }
    public void exit(View view) {

    }



}
