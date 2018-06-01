package com.example.urielshimony.myapplication.UI;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.ScoreEntity;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {

    //private ArrayList<ScoreEntity> scoreTable;
    private TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //get scoreTable
        //scoreTable = MainActivity.highScoreTable.getScoreTable();
        tl = (TableLayout) findViewById(R.id.high_score_table);
        showTable(MainActivity.highScoreTable.getScoreTable());

    }

    private void showTable(ArrayList<ScoreEntity> scoreTable) {
        int rankVal = 1;
        Typeface face;

        // view for headline
        TableRow headlines = new TableRow(this);

        //rank
        TextView rankHeadline = new TextView(this);
        rankHeadline.setText("Rank");

        //time
        TextView locationHeadline = new TextView(this);
        locationHeadline.setText("Location");

        //name
        TextView nameHeadline = new TextView(this);
        nameHeadline.setText("Name");

        rankHeadline.setTextSize(25);
        locationHeadline.setTextSize(25);
        nameHeadline.setTextSize(25);
        rankHeadline.setPadding(0, 2, 70, 2);
        locationHeadline.setPadding(0, 2, 70, 2);


        rankHeadline.setGravity(Gravity.CENTER);
        locationHeadline.setGravity(Gravity.CENTER);
        nameHeadline.setGravity(Gravity.CENTER);

        rankHeadline.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        locationHeadline.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        nameHeadline.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

        //add the headlines to the view
        headlines.addView(rankHeadline);
        headlines.addView(locationHeadline);
        headlines.addView(nameHeadline);
        tl.addView(headlines, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        //create the table
        for (ScoreEntity e : scoreTable) {

            if (rankVal <= 10) {//todo make it final or global
                TableRow tr = new TableRow(this);
                TextView rank = new TextView(this);
                TextView location = new TextView(this);
                TextView name = new TextView(this);

                // set row text
                rank.setText((rankVal++) + " ");
                name.setText(e.getPlayerName());
                location.setText("location");

                //change font
//                face = Typeface.createFromAsset(this.getAssets(), "fonts/big_noodle_titling.ttf");
//                rank.setTypeface(face);
//                location.setTypeface(face);
//                name.setTypeface(face);

                rank.setGravity(Gravity.CENTER);
                location.setGravity(Gravity.CENTER);
                name.setGravity(Gravity.CENTER);

                rank.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                location.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                name.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

                rank.setTextSize(20);
                location.setTextSize(20);
                name.setTextSize(20);
                rank.setPadding(0, 2, 70, 2);
                location.setPadding(0, 2, 70, 2);

                // add row to view
                tr.addView(rank);
                tr.addView(location);
                tr.addView(name);
                tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }
    }
}
