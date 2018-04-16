package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.GameManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class choseDifficultActivity extends AppCompatActivity {
    private String date;
    private String dificultLvl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_difficult);
        setTitle("Choose Level");

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();
        Toast.makeText(this, "Hello " + this.name  +" your age is " +this.getAge(this.date), Toast.LENGTH_LONG).show();

        if (!this.date.equals("")) {

            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
            String today = dateFormat.format(Calendar.getInstance().getTime());
            if (comparDates(today, this.date)) {
                Toast.makeText(this, "happy birthday", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void chooseLvlClick(View view) {
        switch (view.getId()) {
            case R.id.Easy:
                this.dificultLvl = "Easy";
                GameActivity.gameManager = new GameManager(this.dificultLvl, this.name ,this.date);
                break;
            case R.id.Medium:
                this.dificultLvl = "Medium";
                GameActivity.gameManager = new GameManager(this.dificultLvl, this.name , this.date);
                break;
            case R.id.Hard:
                this.dificultLvl = "Hard";
                GameActivity.gameManager = new GameManager(this.dificultLvl, this.name , this.date);
                break;
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("name", this.name);
        intent.putExtra("date_of_birth", this.date);
        intent.putExtra("difcult_lvl", this.dificultLvl);
        startActivity(intent);

    }

    public boolean comparDates(String date1, String date2) {
        int date1Length = date1.length();
        int date2Length = date2.length();
        String trimDate1 = date1.substring(0, date1Length - 2);
        String trimDate2 = date2.substring(0, date2Length - 2);

        return trimDate1.equals(trimDate2);

    }

    public int getAge(String birthdate) {

        int thisYear = Calendar.getInstance().get((Calendar.YEAR));
        int yearOfBirth = thisYear;


        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(birthdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(convertedDate);
            yearOfBirth = cal.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (yearOfBirth != 0) {
            return thisYear - yearOfBirth;
        } else {
            return 0;
        }

    }
}

