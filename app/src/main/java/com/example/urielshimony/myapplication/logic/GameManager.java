package com.example.urielshimony.myapplication.logic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.UI.GameActivity;
import com.example.urielshimony.myapplication.UI.MainActivity;

/**
 * Created by urielshimony on 09/04/2018.
 */

public class GameManager {
    private CardBoard board;
    private String difficultLvl;
    private boolean timesUp;
    private int cardPairsToReveal;
    private MemoryCard[][] cards;
    private int seconds;
    private String name;
    private String date;
    private String gameResult;

    private ScoreEntity scoreEntity;

    final static int EASY_SECONDS = 30;
    final static int MEDIUM_SECONDS = 45;
    final static int HARD_SECONDS = 60;

    final static int EASY_SCORE = 100;
    final static int MEDIUM_SCORE = 200;
    final static int HARD_SCORE = 300;



    //initialize game by level
    public GameManager(String difficultLvl, String name, String date) {
        this.difficultLvl = difficultLvl;
        this.name = name;
        this.date = date;
        switch (this.difficultLvl) {
            case "Easy":
                this.board = new CardBoard("Easy");
                this.seconds = EASY_SECONDS;
                break;
            case "Medium":
                this.board = new CardBoard("Medium");
                this.seconds = MEDIUM_SECONDS;

                break;
            case "Hard":
                this.board = new CardBoard("Hard");
                this.seconds = HARD_SECONDS;
                break;
        }
        this.cards = this.board.getCards();
        this.timesUp = false;
        this.cardPairsToReveal = this.board.getCardPairsToReveal();
    }

    public void endGame(int timeLeft) {
        if (isPlayerWon()) {
            gameResult = "win";
            this.scoreEntity = new ScoreEntity(this.calculateScore(timeLeft), this.difficultLvl, this.name);
            MainActivity.highScoreTable.udpateScoreTable(this.scoreEntity);
        } else {
            gameResult = "lose";
        }
    }

    private int calculateScore(int timeLeft) {
        switch (this.difficultLvl) {
            case "Easy":
                return timeLeft*EASY_SCORE;
            case "Medium":
                this.board = new CardBoard("Medium");
                return timeLeft*MEDIUM_SCORE;
            case "Hard":
                this.board = new CardBoard("Hard");
                return timeLeft*HARD_SCORE;
        }
        return 0;

    }


    //checks if the single game is in winning state - all not mined cells were revealed
    public boolean isPlayerWon() {
        if (this.cardPairsToReveal == 0) {
            return true;
        } else {
            return false;
        }
    }


    public void flipCard(int cardId, String cardFlipState) {
        this.board.flipCard(cardId, cardFlipState);
        this.cardPairsToReveal = this.board.getCardPairsToReveal();

    }

    public CardBoard getCardBoard() {
        return this.board;
    }

    public MemoryCard[][] getCards() {
        return board.getCards();
    }


    public int getSeconds() {
        return this.seconds;
    }

    public String getName() {
        return this.name;
    }

    public String getGameResult() {
        return gameResult;
    }

    public String getDifficultLvl() {
        return difficultLvl;
    }

    public String getDate() {
        return date;
    }

}
