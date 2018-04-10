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
    //TODO  private CardBoard board;  (Maybe unnecessary)
    //TODO  private int timeToEnd;
    private String difficultLvl;
    private boolean timesUp;
    private int CardPairsToReveal;
    private MemoryCard[] cards;
    private int seconds;
    private GridLayout gameGrid;

    //TODO private enum flipState = ["first", "second"];

    //initialize game grid by level
    public GameManager(String difficultLvl) {
        this.difficultLvl = difficultLvl;
        switch (difficultLvl) {
            case "Easy":
//              TODO  this.CardBoard = new CardBoard("Easy");
                this.seconds = 30;

                break;
            case "Medium":
//              TODO  this.CardBoard = new CardBoard("Medium");
                this.seconds = 45;

                break;
            case "Hard":
//             TODO   this.CardBoard = new CardBoard("Hard");
                this.seconds = 60;
                break;
        }
//      TODO   this.cards = this.cardBoard.getCards()   Maybe unnecessary
        this.addCardsToGrid();
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
        if (this.CardPairsToReveal == 0) {
            return true;
        } else {
            return false;
        }
    }

    //TODO
    /*
         public flipCard(flipState, int cardId){
         boolean isMatch = this.cardBoard.flip(flipState, cardId); //boolean function that return if there is a match, the board him self know for each card what to draw
        if(isMatch){
        handleMatch()
        }else(){
        flipBack()
        }
         changeFlipState(); // first-> second second -> first
         this.updateBoardUI();
      }
    */

    private void handleMatch() {
        this.CardPairsToReveal--;
        if (this.CardPairsToReveal == 0) {
            endGame();
        }
    }


    // update game board after adding mines
    public void updateBoardUI() {
        //TODO call a new thread
        for (int i = 0; i < cards.length; i++) {

        }
    }

    public void addCardsToGrid() {
        for (int i = 0; i < cards.length; i++) {
            gameGrid.addView(cards[i].getImageView());
        }


    }
}
