package com.example.urielshimony.myapplication.logic;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by urielshimony on 09/04/2018.
 */

public class MemoryCard {
    private ImageView iv;
    private int image;
    private int viewId;
    private int cardId;
    private boolean isActive;
    private boolean cardUpside;


    public MemoryCard(ImageView iv, int image, int viewId, int cardId) {
        this.iv = iv;
        this.image = image;
        this.viewId = viewId;
        this.cardId = cardId;
    }

    public int getImage() {
        return this.image;
    }

    public int getViewId() {
        return this.viewId;
    }

    public int getCardId() {
        return this.cardId;
    }

    public ImageView getImageView() {
        return this.iv;
    }
//TODO public boolean flipCard()
}
