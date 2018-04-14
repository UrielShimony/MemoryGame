package com.example.urielshimony.myapplication.UI;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.logic.GameManager;
import com.example.urielshimony.myapplication.logic.MemoryCard;

public class GameActivity extends AppCompatActivity {

    static GameManager gameManager;
    private GridLayout gameGrid;
    private Button[][] buttons;
    private String currentFlip;
    private  int timeToStop;
    private int timeLeft;
    private String gameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void setNewGrid(int rows, int cols) {
        gameGrid.removeAllViews();
        gameGrid.setColumnCount(cols);
        gameGrid.setRowCount(rows);
        MemoryCard[][] cards = gameManager.getCards();
        this.buttons = new Button[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.buttons[i][j] = new Button(this);
                this.buttons[i][j].setBackgroundResource(cards[i][j].getBackImage());
                this.buttons[i][j].setLayoutParams(new LinearLayout.LayoutParams(150, 150));
                this.buttons[i][j].setTag(cards[i][j]);
                this.buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.setBackgroundResource(((MemoryCard) view.getTag()).getImage());
                        view.setEnabled(false); //TODO set enable false in thr second chose
                        gameManager.flipCard(((MemoryCard) view.getTag()).getCardId(), currentFlip);
                        if (currentFlip.equals("Second")) {
                            Handler hendler = new Handler();
                            hendler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    updateGrid();
                                }
                            }, 600);
                        }
                        changeCurrentFlip();
                    }
                });

                gameGrid.addView(buttons[i][j]);
            }

        }
    }

    private void changeCurrentFlip() {
        if (this.currentFlip.equals("First")) this.currentFlip = "Second";
        else {
            this.currentFlip = "First";
        }
    }

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
            gameManager.endGame();
            createEndOfGameActivity();
        }
    }

    public void setEnableAll(boolean enabledValue) {
        MemoryCard[][] cards = gameManager.getCards();
        int rows = cards.length;
        int cols = cards[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cards[i][j].getIsBeenMatched()) {
                    this.buttons[i][j].setBackgroundResource(cards[i][j].getImage());
                } else {
                    this.buttons[i][j].setBackgroundResource(cards[i][j].getBackImage());
                    this.buttons[i][j].setEnabled(enabledValue);
                }
            }
        }
    }

    private void startTimer(int timeToStop) {//TODO check if timer is working
        timeLeft = timeToStop;
        Handler hendler = new Handler();
        hendler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tick(timeLeft);
                if (timeLeft != 0) {
                    startTimer(--timeLeft);
                } else {
                    gameManager.endGame();
                    createEndOfGameActivity();
                }
            }
        }, 2000);

    }

    private void tick(int seconds) {
        ((TextView) findViewById(R.id.Timer)).setText("" + seconds);

    }

    public void setName(String name) {
        ((TextView) findViewById(R.id.name)).setText("" + name);
    }

    public void createEndOfGameActivity()
    {
        this.gameResult = gameManager.getGameResult();
        Intent intent = new Intent(this, EndOfGameActivity.class);
        intent.putExtra("gameResult", gameResult);
        startActivity(intent);
    }

    public void setLevel(String level) {
        ((TextView) findViewById(R.id.dificultLevelLable)).setText(""+level);
    }
}