package com.example.urielshimony.myapplication.logic;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by urielshimony on 30/05/2018.
 */

public class ScoreEntity implements Comparable<ScoreEntity>{
//    private int highScoreCounter = 0;
    private String playerName;
    private int score;
    private String level;
    private LatLng playerLocation;
    private String address;


    public ScoreEntity(int score, String level,String playerName , String address) {
        this.score = score;
        this.level=level;
        this.playerName= playerName;
        this.address = address;
       // this.highScoreCounter = load(context).size();
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public int compareTo(@NonNull ScoreEntity other) {
        if(this.score<other.score)
            return -1;
        else if(this.score>other.score)
            return 1;
        return 0;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public LatLng getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Location location ) {
        try {
            this.playerLocation = new LatLng(location.getLatitude(), location.getLongitude());
        }catch (NullPointerException e){
        }
    }

    public String getAddress() {
        return address;
    }
}
