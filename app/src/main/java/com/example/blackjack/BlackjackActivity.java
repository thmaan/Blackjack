package com.example.blackjack;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Random;

public class BlackjackActivity extends AppCompatActivity {
    int hand_player;
    int hand_dealer;
    ImageView firstcard;
    ImageView secondcard;
    ImageView thirdcard;
    ImageView fourthcard;
    ImageView fifthcard;
    ImageView firstcardbot;
    ImageView secondcardbot;
    ImageView thirdcardbot;
    ImageView fourthcardbot;
    ImageView fifthcardbot;
    ImageView sixthcard;
    ImageView sixthcardbot;
    int eventCount;
    int eventCountBot;
    int win;
    int lose;
    String username;
    ConstraintLayout toolbar;
    TextView scoreboard;
    HashMap<Integer,String> cardMap;
    HashMap<String, Integer> cardValueMap;
    int aceplayer;
    int acebot;
    Button stnButton;
    Button hitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);
        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.apply();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("users", MODE_APPEND);
        win = sh.getInt("win",-500);
        lose = sh.getInt("lose",-10);
        username = sh.getString("username","");
        firstcard = findViewById(R.id.firstcard);
        secondcard = findViewById(R.id.secondcard);
        thirdcard = findViewById(R.id.thirdcard);
        fourthcard = findViewById(R.id.fourthcard);
        fifthcard = findViewById(R.id.fifthcard);
        sixthcard = findViewById(R.id.sixthcard);
        firstcardbot = findViewById(R.id.firstcardbot);
        secondcardbot = findViewById(R.id.secondcardbot);
        thirdcardbot = findViewById(R.id.thirdcardbot);
        fourthcardbot = findViewById(R.id.fourthcardbot);
        fifthcardbot = findViewById(R.id.fifthcardbot);
        sixthcardbot = findViewById(R.id.sixthcardbot);
        stnButton = findViewById(R.id.standBtn);
        hitButton = findViewById(R.id.hitBtn);
        toolbar = findViewById(R.id.toolbar);
        scoreboard = findViewById(R.id.scoreboard);

        newGame();
    }
    public void newGame(){
        initVariables();
        setScore();
        startGame();

    }
    public void initVariables(){

        stnButton.setClickable(true);
        hitButton.setClickable(true);
        firstcard.setEnabled(true);
        firstcard.setImageDrawable(null);
        firstcard.setVisibility(View.VISIBLE);

        secondcard.setEnabled(true);
        secondcard.setImageDrawable(null);
        secondcard.setVisibility(View.VISIBLE);

        thirdcard.setEnabled(true);
        thirdcard.setImageDrawable(null);
        thirdcard.setVisibility(View.VISIBLE);

        fourthcard.setEnabled(true);
        fourthcard.setImageDrawable(null);
        fourthcard.setVisibility(View.VISIBLE);

        fifthcard.setEnabled(true);
        fifthcard.setImageDrawable(null);
        fifthcard.setVisibility(View.VISIBLE);

        sixthcard.setEnabled(true);
        sixthcard.setImageDrawable(null);
        sixthcard.setVisibility(View.VISIBLE);

        firstcardbot.setEnabled(true);
        secondcardbot.setEnabled(true);
        thirdcardbot.setEnabled(true);
        fourthcardbot.setEnabled(true);
        fifthcardbot.setEnabled(true);
        sixthcardbot.setEnabled(true);

        firstcardbot.setImageDrawable(null);
        secondcardbot.setImageDrawable(null);
        thirdcardbot.setImageDrawable(null);
        fourthcardbot.setImageDrawable(null);
        fifthcardbot.setImageDrawable(null);
        sixthcardbot.setImageDrawable(null);

        aceplayer = 0;
        acebot = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putInt("win", win);
        myEditor.putInt("lose",lose);
        myEditor.apply();

        eventCount = 0;
        eventCountBot = 0;
        cardMap = new HashMap<>();
        cardValueMap = new HashMap<>();
        hand_dealer = 0;
        hand_player = 0;
        initDeck();
    }
    public void onClickLogout(View v){
        finish();
    }
    public void initDeck(){

        cardMap.put(1,"ace");
        cardMap.put(2,"two");
        cardMap.put(3,"three");
        cardMap.put(4,"four");
        cardMap.put(5,"five");
        cardMap.put(6,"six");
        cardMap.put(7,"seven");
        cardMap.put(8,"eight");
        cardMap.put(9,"nine");
        cardMap.put(10,"ten");
        cardMap.put(11,"jack");
        cardMap.put(12,"queen");
        cardMap.put(13,"king");

        cardValueMap.put("ace",1);
        cardValueMap.put("two", 2);
        cardValueMap.put("three", 3);
        cardValueMap.put("four", 4);
        cardValueMap.put("five", 5);
        cardValueMap.put("six", 6);
        cardValueMap.put("seven", 7);
        cardValueMap.put("eight", 8);
        cardValueMap.put("nine", 9);
        cardValueMap.put("ten", 10);
        cardValueMap.put("jack", 10);
        cardValueMap.put("queen", 10);
        cardValueMap.put("king", 10);

    }
    public void setScore(){
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("users", MODE_APPEND );
        scoreboard.setText("Hello " + sh.getString("username","") + "Win: "+ sh.getInt("win",0) +" Lose: " + sh.getInt("lose",0));
    }
    public  int getRandom() {
        Random rand = new Random();
        int rand1;
        rand1= rand.nextInt(13) + 1;
        return rand1;
    }
    public void onClickHit(View v){
        if(hand_player > 21){
            Toast.makeText(BlackjackActivity.this, "DEFEAT! YOU BUSTED "  + hand_player  +" ace value: " + aceplayer, Toast.LENGTH_LONG).show();
            lose++;
        }else
            switch (eventCount){
                case 0:
                    addCardPlayerToScreen(thirdcard, cardMap.get(getRandom()));
                    eventCount++;
                    break;
                case 1:
                    addCardPlayerToScreen(fourthcard,cardMap.get(getRandom()));
                    eventCount++;
                    break;
                case 2:
                    addCardPlayerToScreen(fifthcard,cardMap.get(getRandom()));
                    eventCount++;
                    break;
                case 3:
                    addCardPlayerToScreen(sixthcard,cardMap.get(getRandom()));
                    eventCount++;
                    break;
        }

    }
    public void addCardPlayerToScreen(ImageView card, String cardname){
        int temp = cardValueMap.get(cardname);
        if(temp == 1 && aceplayer == 0) {
            hand_player += 11;
            aceplayer++;
        }else if(temp == 1 && aceplayer > 0) {
            hand_player += +11 - 10;
        }else {
            hand_player += cardValueMap.get(cardname);
        }
        if(hand_player > 21 && aceplayer >= 1){
            while(aceplayer > 0) {
                hand_player = hand_player - 10;
                aceplayer--;
            }
        }
        card.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
    }
    public void addCardBotToScreen(ImageView card, String cardname){
        int temp = cardValueMap.get(cardname);
        if(temp == 1 && acebot == 0){
            hand_dealer += 11;
            acebot++;
        }else if( temp == 1 && acebot > 0){
            hand_dealer = + 1 - 10 ;
        }else{
            hand_dealer += cardValueMap.get(cardname);
        }
        if(hand_dealer > 21 && acebot >= 1) {
            while (acebot>0) {
                hand_dealer = hand_dealer - 10;
                acebot--;
            }
        }
        card.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
    }

    public void startGame(){
        addCardPlayerToScreen(firstcard,cardMap.get(getRandom()));
        addCardPlayerToScreen(secondcard,cardMap.get(getRandom()));
        if(hand_player == 21 ) {
            win++;
            Toast.makeText(BlackjackActivity.this, "YOU GOT A BLACKJACK", Toast.LENGTH_LONG).show();
            return;
        }
        addCardBotToScreen(firstcardbot,cardMap.get(getRandom()));
    }
    public void checkWinner(){
        if(hand_player > 21) {
            lose++;
            Toast.makeText(BlackjackActivity.this, "DEFEAT! YOU BUSTED "  + hand_player  +" ace value: " + aceplayer, Toast.LENGTH_LONG).show();
            return;
        }else if (hand_dealer > 21) {
                Toast.makeText(BlackjackActivity.this, "VICTORY! Dealer BUSTED " + hand_dealer, Toast.LENGTH_LONG).show();
                win++;
             return;
            } else if (hand_player > hand_dealer) {
                Toast.makeText(BlackjackActivity.this, "VICTORY! " + hand_player + " - " + hand_dealer, Toast.LENGTH_LONG).show();
                win++;
             return;
            } else if (hand_player < hand_dealer) {
                Toast.makeText(BlackjackActivity.this, "DEFEAT! " + hand_dealer + " -  " + hand_player, Toast.LENGTH_LONG).show();
                lose++;
                return;
            }else
                Toast.makeText(BlackjackActivity.this, "DRAW! " + hand_player + " - " + hand_dealer, Toast.LENGTH_LONG).show();
        setScore();
    }
    public void addCardBot(){
        addCardBotToScreen(secondcardbot,cardMap.get(getRandom()));
        while(true) {
            if (hand_dealer == 21) {
                lose++;
                Toast.makeText(BlackjackActivity.this, "DEALER GOT A BLACKJACK", Toast.LENGTH_LONG).show();
                break;
            } else if (hand_dealer >= 18) {
                onClickStandBot();
                break;
            } else
                onClickHitBot();
        }
    }
    public void onClickHitBot(){
        switch (eventCountBot){
            case 0:
                addCardBotToScreen(thirdcardbot, cardMap.get(getRandom()));
                eventCountBot++;
                break;
            case 1:
                addCardBotToScreen(fourthcardbot,cardMap.get(getRandom()));
                eventCountBot++;
                break;
            case 2:
                addCardBotToScreen(fifthcardbot,cardMap.get(getRandom()));
                eventCountBot++;
                break;
            case 3:
                addCardBotToScreen(sixthcardbot,cardMap.get(getRandom()));
                eventCountBot++;
                break;
        }
    }
    public void onClickStand(View v){
        stnButton.setClickable(false);
        hitButton.setClickable(false);
        addCardBot();

    }
    public void onClickStandBot(){
        checkWinner();
    }
    public void onClickNewGame(View view) {
        newGame();
    }
}