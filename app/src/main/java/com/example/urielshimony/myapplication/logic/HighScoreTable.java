package com.example.urielshimony.myapplication.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


/**
 * Created by urielshimony on 30/05/2018.
 */

// todo on close main activity.... call save to memory

public class HighScoreTable {
    //todo call a single instance in start of the application.
    final static int MAX_TABLE_SIZE = 10;
    private Context context;
    private ArrayList<ScoreEntity> scoreTable;
    private int currentMinScore;


    public HighScoreTable(Context context) {
        this.context = context;
        this.scoreTable =new ArrayList<ScoreEntity>();
        this.loadTable(context);
        this.currentMinScore = 0;
    }

    public void saveEntityToMemory(SharedPreferences.Editor editorTable, ScoreEntity scoreEntity, int index) {
        String jsonStringScore = new Gson().toJson(scoreEntity);
        editorTable.putString(this.getClass().getSimpleName() + index, jsonStringScore);
    }

    public void saveTableToMemory() {
        SharedPreferences.Editor editorTable = context.getSharedPreferences("HighScoreTable", MODE_PRIVATE).edit();
        for (int i = 0; i < scoreTable.size(); i++) {
            this.saveEntityToMemory(editorTable, this.scoreTable.get(i), i);
        }
        editorTable.apply();

    }

    public void udpateScoreTable(ScoreEntity newScoreEntity) {

        if (scoreTable.size() < MAX_TABLE_SIZE) {
            scoreTable.add(newScoreEntity);

        } else if (newScoreEntity.getScore() > currentMinScore) {
            scoreTable.remove(scoreTable.size() - 1);
            scoreTable.add(newScoreEntity);
        }
        Collections.sort(scoreTable);
        this.currentMinScore = scoreTable.get(scoreTable.size() - 1).getScore();

    }

    private  void loadTable(Context context) {

        SharedPreferences sp = context.getSharedPreferences("HighScoreTable", MODE_PRIVATE);
        Map<String,?> scores = sp.getAll();

        //get the high scores
        for (Map.Entry<String, ?> entry : scores.entrySet()){
            String json = entry.getValue().toString();
            scoreTable.add(new Gson().fromJson(json,ScoreEntity.class));
        }
       // todo check this.  Collections.sort(table);

    }

    public  ArrayList<ScoreEntity> getScoreTable()
    {
        return this.scoreTable;
    }


}
