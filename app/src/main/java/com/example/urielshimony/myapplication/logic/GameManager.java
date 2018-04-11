package com.example.urielshimony.myapplication.logic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;

/**
 * Created by urielshimony on 09/04/2018.
 */

public class GameManager {
    private CardBoard board;
    //TODO  private int timeToEnd;
    private String difficultLvl;
    private boolean timesUp;
    private int cardPairsToReveal;
    private MemoryCard[][] cards;
    private int seconds;


    //initialize game by level
    public GameManager(String difficultLvl) {
        this.difficultLvl = difficultLvl;
        switch (this.difficultLvl) {
            case "Easy":
                this.board = new CardBoard("Easy");
                this.seconds = 30;

                break;
            case "Medium":
                this.board = new CardBoard("Medium");
                this.seconds = 45;

                break;
            case "Hard":
                this.board = new CardBoard("Hard");
                this.seconds = 60;
                break;
        }
        this.cards = this.board.getCards();
        this.timesUp = false;
    }

    public void startGame() {
        this.StartTimer(this.seconds);

    }


    private void StartTimer(int timeToStop) {
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int timeToStop = message.getData().getInt("seconds");
                tick(timeToStop);
                if (timeToStop != 0) {
                    StartTimer(--timeToStop);
                    return false;
                } else {
                    endGame();
                }
                return true;
            }
        });
        Message message = new Message();
        Bundle messageData = new Bundle();
        messageData.putInt("seconds", timeToStop);
        message.setData(messageData);
        handler.sendMessageDelayed(message, 1000);
    }

    private void tick(int seconds) {
        this.seconds = seconds;
        //((TextView) findViewById(R.id.Timer)).setText("" + seconds);
        //TODO send message to ui thread

    }

    private void endGame() {
        if (isPlayerWon()) {
//           TODO wining feedback
        } else {
            //TODO loosing feedback
        }
        // TODO send message to ui thread
    }


    //checks if the single game is in winning state - all not mined cells were revealed
    public boolean isPlayerWon() {
        if (this.cardPairsToReveal == 0) {
            return true;
        } else {
            return false;
        }
    }


     public void flipCard( int cardId ,String cardFlipState ){
        this.board.flipCard(cardId, cardFlipState);
        this.handleMatchs();
     }


    private void handleMatchs() {
        this.cardPairsToReveal = this.board.getCardPairsToReveal();
        if (this.cardPairsToReveal == 0) {endGame();
        }
    }


    public CardBoard getCardBoard() {
        return this.board;
    }

    public MemoryCard[][] getCards() {
        return board.getCards();
    }


}
