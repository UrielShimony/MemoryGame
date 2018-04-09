package com.example.urielshimony.myapplication.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.urielshimony.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameMediumLevel extends AppCompatActivity {
    private String date;
    private String dificultLvl;
    private String name;
    private boolean winner = false;
    private int seconds;

    //imageView
    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24,
            iv_31, iv_32, iv_33, iv_34, iv_41, iv_42, iv_43, iv_44;

    ImageView[] cardsImageViewArray = {iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24,
            iv_31, iv_32, iv_33, iv_34, iv_41, iv_42, iv_43, iv_44};

    ArrayList<ImageView> cardList = new ArrayList<ImageView>();

    int[] imageViewIdArr = {
            R.id.mat_11,
            R.id.mat_12,
            R.id.mat_13,
            R.id.mat_14,
            R.id.mat_21,
            R.id.mat_22,
            R.id.mat_23,
            R.id.mat_24,
            R.id.mat_31,
            R.id.mat_32,
            R.id.mat_33,
            R.id.mat_34,
            R.id.mat_41,
            R.id.mat_42,
            R.id.mat_43,
            R.id.mat_44};


    //photoArray
    Integer[] cardsIdArray = {101, 102, 103, 104, 105, 106, 107, 108, 201, 202, 203, 204, 205, 206, 207, 208};
    ArrayList<Integer> chosenCardsArray = new ArrayList<Integer>();

    //actual photo
    int image101, image102, image103, image104, image105, image106, image107, image108,
            image201, image202, image203, image204, image205, image206, image207, image208;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNum = 1;
    int imageBackCard = R.drawable.back_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium_level);
//       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);

        //??
        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.date = getIntent().getSerializableExtra("date_of_birth").toString();
        this.dificultLvl = extras.getString("difcult_lvl");

        //update name ,level,Timer
        ((TextView) findViewById(R.id.player_name)).setText(("" + this.name));
        ((TextView) findViewById(R.id.dificultLevelLable)).setText("" + this.dificultLvl);
        setTimer();

        initialImageView();


        //load card Image
        loadFrontCard();

        //mix images
        Collections.shuffle(Arrays.asList(cardsIdArray));

        //cards click
        for (int i = 0; i < cardsImageViewArray.length; i++) {
            cardsImageViewArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int chosenCard = Integer.parseInt((String) view.getTag());
                    flipCard(((ImageView) view), chosenCard);
                }
            });
        }
        cardList.addAll(Arrays.asList(cardsImageViewArray));

//        iv_11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_11, chosenCard);
//            }
//        });
//        iv_12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_12, chosenCard);
//            }
//        });
//        iv_13.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_13, chosenCard);
//            }
//        });
//        iv_14.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_14, chosenCard);
//            }
//        });
//        iv_21.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_21, chosenCard);
//            }
//        });
//        iv_22.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_22, chosenCard);
//            }
//        });
//        iv_23.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_23, chosenCard);
//            }
//        });
//        iv_24.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_24, chosenCard);
//            }
//        });
//        iv_31.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_31, chosenCard);
//            }
//        });
//        iv_32.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_32, chosenCard);
//            }
//        });
//        iv_33.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_33, chosenCard);
//            }
//        });
//        iv_34.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_34, chosenCard);
//            }
//        });
//        iv_41.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_41, chosenCard);
//            }
//        });
//        iv_42.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_42, chosenCard);
//            }
//        });
//        iv_43.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_43, chosenCard);
//            }
//        });
//        iv_44.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int chosenCard = Integer.parseInt((String) view.getTag());
//                flipCard(iv_44, chosenCard);
//            }
//        });
    }

    private void initialImageView() {
        for (int i = 0; i < imageViewIdArr.length; i++) {
            cardsImageViewArray[i] = findViewById(imageViewIdArr[i]);
            cardsImageViewArray[i].setTag("" + i);
        }

//        iv_11 = findViewById(R.id.mat_11);
//        iv_12 = findViewById(R.id.mat_12);
//        iv_13 = findViewById(R.id.mat_13);
//        iv_14 = findViewById(R.id.mat_14);
//        iv_21 = findViewById(R.id.mat_21);
//        iv_22 = findViewById(R.id.mat_22);
//        iv_23 = findViewById(R.id.mat_23);
//        iv_24 = findViewById(R.id.mat_24);
//        iv_31 = findViewById(R.id.mat_31);
//        iv_32 = findViewById(R.id.mat_32);
//        iv_33 = findViewById(R.id.mat_33);
//        iv_34 = findViewById(R.id.mat_34);
//        iv_41 = findViewById(R.id.mat_41);
//        iv_42 = findViewById(R.id.mat_42);
//        iv_43 = findViewById(R.id.mat_43);
//        iv_44 = findViewById(R.id.mat_44);

//        iv_11.setTag("0");
//        iv_12.setTag("1");
//        iv_13.setTag("2");
//        iv_14.setTag("3");
//        iv_21.setTag("4");
//        iv_22.setTag("5");
//        iv_23.setTag("6");
//        iv_24.setTag("7");
//        iv_31.setTag("8");
//        iv_32.setTag("9");
//        iv_33.setTag("10");
//        iv_34.setTag("11");
//        iv_41.setTag("12");
//        iv_42.setTag("13");
//        iv_43.setTag("14");
//        iv_44.setTag("15");
    }

    private void loadFrontCard() {
        image101 = R.drawable.p1;
        image102 = R.drawable.p2;
        image103 = R.drawable.p3;
        image104 = R.drawable.p4;
        image105 = R.drawable.p5;
        image106 = R.drawable.p6;
        image107 = R.drawable.p7;
        image108 = R.drawable.p8;
        image201 = R.drawable.p1;
        image202 = R.drawable.p2;
        image203 = R.drawable.p3;
        image204 = R.drawable.p4;
        image205 = R.drawable.p5;
        image206 = R.drawable.p6;
        image207 = R.drawable.p7;
        image208 = R.drawable.p8;
    }

    private void flipCard(ImageView iv, int chosenCard) {
        switch (cardsIdArray[chosenCard]) {
            case 101:
                iv.setImageResource(image101);
                break;
            case 102:
                iv.setImageResource(image102);
                break;
            case 103:
                iv.setImageResource(image103);
                break;
            case 104:
                iv.setImageResource(image104);
                break;
            case 105:
                iv.setImageResource(image105);
                break;
            case 106:
                iv.setImageResource(image106);
                break;
            case 107:
                iv.setImageResource(image107);
                break;
            case 108:
                iv.setImageResource(image108);
                break;
            case 201:
                iv.setImageResource(image201);
                break;
            case 202:
                iv.setImageResource(image202);
                break;
            case 203:
                iv.setImageResource(image203);
                break;
            case 204:
                iv.setImageResource(image204);
                break;
            case 205:
                iv.setImageResource(image205);
                break;
            case 206:
                iv.setImageResource(image206);
                break;
            case 207:
                iv.setImageResource(image207);
                break;
            case 208:
                iv.setImageResource(image208);
                break;
        }
        //check which image is selected
        if (cardNum == 1) {
            firstCard = cardsIdArray[chosenCard];
            cardNum = 2;
            clickedFirst = chosenCard;
            iv.setEnabled(false);
        } else if (cardNum == 2) {
            secondCard = cardsIdArray[chosenCard];
            cardNum = 1;
            clickedSecond = chosenCard;

            setEnableAllImageView(false);
            Handler hendler = new Handler();
            hendler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (checkEqualCards()) {
                        handleEqualCard();
                    }
                    flipBackCards();
                    //  nextTurn();
                }
            }, 1000);

        }
    }

    private void flipBackCards() {
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setImageResource(imageBackCard);
        }
        setEnableAllImageView(true);
    }

    private void handleEqualCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getTag().equals( "" + clickedFirst) || cardList.get(i).getTag().equals( "" + clickedSecond))
                cardList.remove(i);
        }
    }

    private boolean checkEqualCards() {
        //if equal remove chosenImages
        if (firstCard > 200)
            firstCard -= 100;
        if (secondCard > 200)
            secondCard -= 100;
        if (firstCard == secondCard) {
            return true;
        }
        return false;
    }

    private void setEnableAllImageView(boolean b) {
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setEnabled(b);
        }
    }

    private void setTimer() {

        switch (this.dificultLvl) {
            case "Easy":
                this.seconds = 30;
                break;
            case "Medium":
                this.seconds = 45;
                break;
            case "Hard":
                this.seconds = 60;
                break;
        }
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
        ((TextView) findViewById(R.id.Timer)).setText("" + seconds);

    }

    private void endGame() {
    }
}
