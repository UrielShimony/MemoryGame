package com.example.urielshimony.myapplication.UI;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.DatePicker;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.HighScoreTable;
import com.example.urielshimony.myapplication.logic.PlayerLocation;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar BirthDate;
    public static HighScoreTable highScoreTable;
    private Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Emoji Memory Game");
        highScoreTable = new HighScoreTable(this);
        application = getApplication();
        application.registerActivityLifecycleCallbacks(new AppLifecycleTracker());
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
        finish();
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

    private static class AppLifecycleTracker implements Application.ActivityLifecycleCallbacks {
        private static final String TAG = AppLifecycleTracker.class.getSimpleName();
        private int numStarted = 0;
        private boolean isApplicationInForeground;

        public AppLifecycleTracker() {
            Log.d(TAG, " in my ciostructor");
        }

        @Override
        public void onActivityStarted(Activity activity) {

            if (numStarted++ == 0) {
                isApplicationInForeground = false;
                Log.d(TAG, "onActivityStopped: app went to foreground");
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (--numStarted == 0) {
                isApplicationInForeground = false;
                Log.d(TAG, "onActivityStopped: app went to background");
                highScoreTable.saveTableToMemory();
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }
    }


}
