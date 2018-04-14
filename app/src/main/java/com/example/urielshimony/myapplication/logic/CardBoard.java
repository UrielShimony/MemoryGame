package com.example.urielshimony.myapplication.logic;

import android.media.MediaCas;

import com.example.urielshimony.myapplication.R;
import com.example.urielshimony.myapplication.UI.GameActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.net.ssl.HandshakeCompletedEvent;

/**
 * Created by urielshimony on 11/04/2018.
 */

public class CardBoard {
    private final static int EASY_ROWS = 3;
    private final static int EASY_COLS = 4;

    private final static int MEDIUM_ROWS = 4;
    private final static int MEDIUM_COLS = 4;

    private final static int HARD_ROWS = 5;
    private final static int HARD_COLS = 4;

    private static int[] emjoiesArr =
            {R.drawable.p1,
                    R.drawable.p2,
                    R.drawable.p3,
                    R.drawable.p4,
                    R.drawable.p5,
                    R.drawable.p6,
                    R.drawable.p7,
                    R.drawable.p8,
                    R.drawable.p9,
                    R.drawable.p10};


    private String difficultLvl;
    private int rows;
    private int cols;
    private MemoryCard[][] cards;
    private int[] firstCardCord;//= new int[2]; //TODO check if need to be new;
    private int[] secondCardCord;//= new int[2];
    private int cardPairsToReveal;

    CardBoard(String difficultLvl) {
        this.difficultLvl = difficultLvl;
        switch (difficultLvl) {
            case "Easy":
                this.rows = EASY_ROWS;
                this.cols = EASY_COLS;
                this.cards = this.generateCards(EASY_ROWS, EASY_COLS);
                break;
            case "Medium":
                this.rows = MEDIUM_ROWS;
                this.cols = MEDIUM_COLS;
                this.cards = this.generateCards(MEDIUM_ROWS, MEDIUM_COLS);
                break;
            case "Hard":
                this.rows = HARD_ROWS;
                this.cols = HARD_COLS;
                this.cards = this.generateCards(HARD_ROWS, HARD_COLS);
                break;
        }

    }

    private MemoryCard[][] generateCards(int rows, int cols) {
        int emojyId = 0;
        int cardsId = 0;

        this.cardPairsToReveal = rows * cols / 2;

        cards = new MemoryCard[rows][cols];
        MemoryCard[] tempCards = new MemoryCard[rows * cols];
        //Collections.shuffle(Arrays.asList(emjoiesArr)); // shuffle images.

        for (int i = 0; i < rows * cols; i++) {
            tempCards[i] = new MemoryCard(emjoiesArr[emojyId], cardsId, emojyId);
            if (cardsId % 2 == 1) emojyId++;
            cardsId++;
        }

        Collections.shuffle(Arrays.asList(tempCards)); //shuffle the array

        for (int i = 0; i < rows; i++) { // set arrat to card matrix from end to begin
            for (int j = 0; j < cols; j++) {
                cards[i][j] = tempCards[--cardsId];
            }
        }
        return cards;
    }

    MemoryCard[][] getCards() {
        return cards;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }


    public void flipCard(int cardId, String cardFlipState) {
        int[] cords = getIndexById(cardId);
        if (cardFlipState.equals("First")) {
            cards[cords[0]][cords[1]].flip();
            firstCardCord = cords;

        } else {
            cards[cords[0]][cords[1]].flip();
            secondCardCord = cords;

            checkMatch();
        }
    }

    private void checkMatch() {
        if (cards[firstCardCord[0]][firstCardCord[1]].getEmojiId() ==
                cards[secondCardCord[0]][secondCardCord[1]].getEmojiId()) {

            cards[firstCardCord[0]][firstCardCord[1]].setIsMatched();
            cards[secondCardCord[0]][secondCardCord[1]].setIsMatched();
            this.cardPairsToReveal--;
        }

    }

    private int[] getIndexById(int cardId) {
        int rows = cards.length;
        int cols = cards[0].length;
        int[] index = new int[2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cards[i][j].getCardId() == cardId) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return null; //todo throw error
    }

    public int getCardPairsToReveal() {
        return this.cardPairsToReveal;
    }
}
