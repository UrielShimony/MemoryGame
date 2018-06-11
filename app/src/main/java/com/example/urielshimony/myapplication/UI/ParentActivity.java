package com.example.urielshimony.myapplication.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.PlayerLocation;

public class ParentActivity extends AppCompatActivity {

    private PlayerLocation playerLocation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

         playerLocation = new PlayerLocation(this);
    }


    public PlayerLocation getPlayerLocation() {
        return playerLocation;
    }
}
