package com.example.urielshimony.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class choseDifficultActivity extends AppCompatActivity {
    private String date;
    private String dificultLvl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //dont know what is it
        setContentView(R.layout.activity_chose_difficult);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();

        getSupportActionBar().setTitle("Hello " + this.name);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String today = dateFormat.format(Calendar.getInstance().getTime());
        if(comparDates(today, this.date)){
            Toast.makeText(this, "happy birthday", Toast.LENGTH_LONG).show();
        }

    }

    public void chooseLvlClick(View view) {
        switch (view.getId()) {
            case R.id.Easy:
                Log.d("dificult is EASY", "EASY");
                this.dificultLvl= "Easy";
                break;
            case R.id.Medium:
                Log.d("dificult is Medium", "MEDIUM");
                this.dificultLvl= "Medium";
                break;
            case R.id.Hard:
                this.dificultLvl= "Hard";
                Log.d("dificult is Hard", "HARD");
                break;
        }

        Intent intent = new Intent(this, Game.class);
        intent.putExtra("name", this.name);
        intent.putExtra("date_of_birth", this.date);
        intent.putExtra("difcult_lvl", this.dificultLvl);
        startActivity(intent);
        Log.d("date is", this.date);
    }
public boolean comparDates(String date1,String date2){
        int date1Length = date1.length();
        int date2Length = date2.length();
        String trimDate1= date1.substring(0,date1Length-2);
        String trimDate2= date2.substring(0,date2Length-2);

        return trimDate1.equals(trimDate2);

}
}

