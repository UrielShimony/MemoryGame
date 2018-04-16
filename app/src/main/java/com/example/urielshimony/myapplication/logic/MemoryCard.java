package com.example.urielshimony.myapplication.logic;



import com.example.urielshimony.myapplication.R;

/**
 * Created by urielshimony on 09/04/2018.
 */

public class MemoryCard {
    static int unRevealdImage = R.drawable.back_ground_new;
    private int image;
    private int cardId;
    private int emojiId;
    private boolean isBeenMatched;
    private boolean isChosenCard;

    public MemoryCard(int image, int cardId, int emojiId) {

        this.image = image;
        this.cardId = cardId;
        this.emojiId = emojiId;
        this.isChosenCard = false;
        this.isBeenMatched = false;
    }

    public int getImage() {
        return this.image;
    }

    public int getCardId() {
        return this.cardId;
    }


    public void flipBack() {
        this.isChosenCard = false;
    }

    public void flip() {
        this.isChosenCard = true;
    }

    public int getBackImage() {
        return unRevealdImage;
    }


    public boolean getIsBeenMatched() {
        return isBeenMatched;
    }

    public int getEmojiId() {
        return emojiId;
    }

    public void setIsMatched() {
        this.isBeenMatched =true;
    }
}