package com.example.urielshimony.myapplication.logic;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by urielshimony on 09/04/2018.
 */

public class MemoryCard /* extends AppCompatButton*/ {
    private ImageView iv;
    private int image;
    private int viewId;
    private int cardId;
    private boolean isActive;
    private boolean cardUpside;


    public MemoryCard(/*Context context*/ ImageView iv, int image, int viewId, int cardId) {
//        super(context); TODO check what we prefer image || button
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
