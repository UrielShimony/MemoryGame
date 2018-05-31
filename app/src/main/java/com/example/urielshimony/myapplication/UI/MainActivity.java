package com.example.urielshimony.myapplication.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.HighScoreTable;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar BirthDate ;
    public static  HighScoreTable highScoreTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Emoji Memory Game");
        highScoreTable = new HighScoreTable(this);
    }

    public void next(View view) {
        EditText nameInput = findViewById(R.id.name);
        String name = nameInput.getText().toString();
        TextView dateInput = findViewById(R.id.dateResult);
        String date = dateInput.getText().toString();

        //create  choseDifficultActivity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("date_of_birth", date);
        startActivity(intent);
    }

    public void picDate(View view) {

        MyDatePicker fragment = new MyDatePicker();
        fragment.show(getFragmentManager(), "show");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        this.BirthDate = cal;
        setDate(cal);
    }

    private void setDate(Calendar calendar) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        ((TextView) findViewById(R.id.dateResult)).setText(dateFormat.format(calendar.getTime()));
    }
}
