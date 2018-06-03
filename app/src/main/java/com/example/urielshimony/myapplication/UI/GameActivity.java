package com.example.urielshimony.myapplication.UI;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.GameManager;
import com.example.urielshimony.myapplication.logic.MemoryCard;

import tyrantgit.explosionfield.ExplosionField;

public class GameActivity extends AppCompatActivity {

    static GameManager gameManager;
    private GridLayout gameGrid;
    private Button[][] buttons;
    private String currentFlip;
    private int timeToStop;
    private int timeLeft;
    private String gameResult;

    public MotionService.SensorServiceBinder binder;
    private boolean isServiceBound = false;

    ExplosionField explosionField;

    final static int SECOND = 1000;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {

            binder = (MotionService.SensorServiceBinder) service;
            isServiceBound = true;
            notifyBoundService(MotionService.SensorServiceBinder.START_LISTENING);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceBound = false;

        }

        void notifyBoundService(String massageFromActivity) {
            if (isServiceBound && binder != null) {
                binder.notifyService(massageFromActivity);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, MotionService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,
                        new IntentFilter("motion-change"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent(this, MotionService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,
                        new IntentFilter("motion-change"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init
        super.onCreate(savedInstanceState);
        setTitle("Let's Play!");
        setContentView(R.layout.activity_game);
        this.gameGrid = (GridLayout) findViewById(R.id.grid);
        this.currentFlip = "First";
        this.timeToStop = gameManager.getSeconds();
        startTimer(this.timeToStop);
        setName(gameManager.getName());
        setLevel(gameManager.getDifficultLvl());
        setNewGrid(gameManager.getCardBoard().getRows(), gameManager.getCardBoard().getCols());

    }

    //create new grid according to spesific rows and cols
    private void setNewGrid(int rows, int cols) {
        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        final int screenWidth = size.x;
        final int screenHeight = size.y;
        int buttonsWidth = (int) (screenWidth * 0.8 / cols);
        int buttonsHeight = (int) (screenHeight * 0.6 / rows);

        Log.d("width", "" + buttonsWidth);
        Log.d("H", "" + buttonsHeight);

        gameGrid.removeAllViews();
        gameGrid.setColumnCount(cols);
        gameGrid.setRowCount(rows);
        MemoryCard[][] cards = gameManager.getCards();

        this.buttons = new Button[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.buttons[i][j] = new Button(this);
                this.buttons[i][j].setBackgroundResource(cards[i][j].getBackImage());
                this.buttons[i][j].setLayoutParams(new LinearLayout.LayoutParams(buttonsWidth, buttonsHeight));
                this.buttons[i][j].setTag(cards[i][j]);
                this.buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.setBackgroundResource(((MemoryCard) view.getTag()).getImage());
                        view.setEnabled(false);
                        gameManager.flipCard(((MemoryCard) view.getTag()).getCardId(), currentFlip);
                        if (currentFlip.equals("Second")) {
                            setEnableAll(false);
                            Handler hendler = new Handler();
                            hendler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    updateGrid();
                                    setEnableAll(true);

                                }
                            }, 400);
                        }
                        changeCurrentFlip();
                    }
                });

                gameGrid.addView(buttons[i][j]);
            }

        }
    }

    //set if it first or second card
    private void changeCurrentFlip() {
        if (this.currentFlip.equals("First")) this.currentFlip = "Second";
        else {
            this.currentFlip = "First";
        }
    }

    //update grid after flip
    private void updateGrid() {
        MemoryCard[][] cards = gameManager.getCards();
        int rows = cards.length;
        int cols = cards[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cards[i][j].getIsBeenMatched()) {
                    this.buttons[i][j].setBackgroundResource(cards[i][j].getImage());
                } else {
                    this.buttons[i][j].setBackgroundResource(cards[i][j].getBackImage());
                    this.buttons[i][j].setEnabled(true);
                }
            }
        }
        if (gameManager.isPlayerWon()) {
            gameManager.endGame(timeLeft);
            handleEndOfGame();
        }
    }

    private void handleRotation(boolean validity) {
        if (!validity) {

            MemoryCard[][] cards = gameManager.getCards();
            int rows = cards.length;
            int cols = cards[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    this.buttons[i][j].setBackgroundResource(cards[i][j].getBackImage());
                    this.buttons[i][j].setEnabled(false);
                }
            }
        }

        if (validity) {
            MemoryCard[][] cards = gameManager.getCards();
            int rows = cards.length;
            int cols = cards[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (cards[i][j].getIsBeenMatched()) {
                        this.buttons[i][j].setBackgroundResource(cards[i][j].getImage());
                    } else {

                        this.buttons[i][j].setEnabled(true);
                    }
                }
            }
            this.currentFlip = "First";
        }

    }

    public void setEnableAll(boolean enabledValue) {
        MemoryCard[][] cards = gameManager.getCards();
        int rows = cards.length;
        int cols = cards[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cards[i][j].getIsBeenMatched()) {

                } else {
                    this.buttons[i][j].setEnabled(enabledValue);
                }
            }
        }
    }

    private void startTimer(int timeToStop) {
        timeLeft = timeToStop;
        Handler hendler = new Handler();
        hendler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tick(timeLeft);
                if (timeLeft != 0) {
                    startTimer(--timeLeft);
                } else {
                    gameManager.endGame(timeLeft);
                    handleEndOfGame();
                }
            }
        }, SECOND);

    }

    private void tick(int seconds) {
        ((TextView) findViewById(R.id.Timer)).setText("" + seconds);

    }

    public void setName(String name) {
        ((TextView) findViewById(R.id.name)).setText("" + name);
    }

    public void createEndOfGameActivity() {
        Intent intent = new Intent(this, EndOfGameActivity.class);
        intent.putExtra("name", gameManager.getName());
        intent.putExtra("date_of_birth", gameManager.getDate());
        intent.putExtra("gameResult", gameResult);

        //todo unbound service;
        startActivity(intent);
    }

    public void handleEndOfGame() {
        this.gameResult = gameManager.getGameResult();
        renderAnimation();
        if (this.gameResult.equals("lose")) {
            Handler hendler = new Handler();
            hendler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    createEndOfGameActivity();
                }
            }, 1300);
        } else {
            createEndOfGameActivity();
        }
    }

    public void renderAnimation() {
        if (this.gameResult.equals("lose")) {
            explosionField = ExplosionField.attach2Window(this);
            explosionField.explode(gameGrid);
        } else if (this.gameResult.equals("win")) {
            // animmation in the next activity
        }
    }

    public void setLevel(String level) {
        ((TextView) findViewById(R.id.dificultLevelLable)).setText("" + level);
    }

    // Handling the received Intents for the "my-integer" event
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            boolean state = intent.getBooleanExtra("value", true);
            Log.d("on recive from srervice", "onReceive: " + state);
            if (state) {
                ((TextView) findViewById(R.id.RotationStatus)).setText("");
            } else {
                ((TextView) findViewById(R.id.RotationStatus)).setText("Rotate the Phone");
            }
            handleRotation(state);
        }
    };


}