package com.example.urielshimony.myapplication.UI;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.ScoreEntity;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {

    private ArrayList<ScoreEntity> scoreTable;
    private TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //get scoreTable
        scoreTable = MainActivity.highScoreTable.getScoreTable();

        tl = (TableLayout) findViewById(R.id.high_score_table);

        showTable(scoreTable);


    }

    private void showTable( ArrayList<ScoreEntity> scoreTable)
    {
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

        //add the headlines to the view
        headlines.addView(rankHeadline);
        headlines.addView(locationHeadline);
        headlines.addView(nameHeadline);
        tl.addView(headlines, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        //create the table
//        for (HighScore e : scores) {
//            /* Create a new row to be added. */
//            if (e.getLevel() == level) {
//                if (rankVal <= getResources().getInteger(R.integer.table_size)) {
//                    TableRow tr = new TableRow(this);
//                    TextView rank = new TextView(this);
//                    TextView time = new TextView(this);
//                    TextView name = new TextView(this);
//
//                    // set row text
//                    rank.setText((rankVal++) + "");
//                    name.setText(e.getPlayerName());
//                    time.setText(e.getScore() + "");
//
//                    //change font
//                    face = Typeface.createFromAsset(this.getAssets(), getString(R.string.number_font));
//                    rank.setTypeface(face);
//                    time.setTypeface(face);
//                    name.setTypeface(face);
//
//                    rank.setGravity(Gravity.CENTER);
//                    time.setGravity(Gravity.CENTER);
//                    name.setGravity(Gravity.CENTER);
//
//                    rank.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
//                    time.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
//                    name.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
//
//                    rank.setTextSize(getResources().getInteger(R.integer.table_text_size));
//                    time.setTextSize(getResources().getInteger(R.integer.table_text_size));
//                    name.setTextSize(getResources().getInteger(R.integer.table_text_size));
//
//                    // add row to view
//                    tr.addView(rank);
//                    tr.addView(time);
//                    tr.addView(name);
//                    tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
//                }
//            }
        }
    }

//}
