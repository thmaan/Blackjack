package com.example.blackjacksembug;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

public class BlackjackActivity extends AppCompatActivity {
    ArrayList<String> cards;
    ArrayList<String> cardsFigures;
    ArrayList<Integer> cardsValue;
    int hand_player;
    int hand_dealer;
    ArrayList<Integer> botCards;
    LinearLayout firstcard;
    LinearLayout secondcard;
    LinearLayout thirdcard;
    LinearLayout fourthcard;
    LinearLayout fifthcard;
    LinearLayout firstcardbot;
    LinearLayout secondcardbot;
    LinearLayout thirdcardbot;
    LinearLayout fourthcardbot;
    LinearLayout fifthcardbot;
    int eventCount;
    Toolbar toolbar;
    int win;
    int lose;
    String username;
    boolean restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        Bundle bundle = getIntent().getExtras();
        win = 0;
        lose = 0 ;
        username = bundle.getString("username");
        setScore();
        botCards = new ArrayList<>();
        setContentView(R.layout.activity_blackjack);
        firstcard = findViewById(R.id.firstcard);
        secondcard = findViewById(R.id.secondcard);
        thirdcard = findViewById(R.id.thirdcard);
        fourthcard = findViewById(R.id.fourthcard);
        fifthcard = findViewById(R.id.fifthcard);


        firstcardbot = findViewById(R.id.firstcarbot);
        secondcardbot = findViewById(R.id.secondcarbot);
        thirdcardbot = findViewById(R.id.thirdcardbot);
        fourthcardbot = findViewById(R.id.fourthcardbot);
        fifthcardbot = findViewById(R.id.fifthcardbot);

        eventCount = 0;

        //game
        cards = new ArrayList<>();
        cardsFigures = new ArrayList<>();
        cardsValue = new ArrayList<>();
        initDeck(cards, cardsFigures,cardsValue);
        hand_dealer = 0;
        hand_player = 0;

        addCard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(this,BlackjackActivity.class));
    }

    public static void initDeck(ArrayList<String> deckText, ArrayList<String> deckFigures, ArrayList<Integer> deckValue){
        deckText.add("ace");
        deckText.add("two");
        deckText.add("three");
        deckText.add("four");
        deckText.add("five");
        deckText.add("six");
        deckText.add("seven");
        deckText.add("eight");
        deckText.add("nine");
        deckText.add("ten");
        deckText.add("jack");
        deckText.add("king");
        deckText.add("queen");

        deckFigures.add("ten");
        deckFigures.add("jack");
        deckFigures.add("king");
        deckFigures.add("queen");

        deckValue.add(1);
        deckValue.add(2);
        deckValue.add(3);
        deckValue.add(4);
        deckValue.add(5);
        deckValue.add(6);
        deckValue.add(7);
        deckValue.add(8);
        deckValue.add(9);
        deckValue.add(10);
        deckValue.add(10);
        deckValue.add(10);
        deckValue.add(10);

    }
    public void setScore(){
        getSupportActionBar().setTitle("Hello " + username + " Win: " + win + " Lose: " + lose);
        restart = true;
    }

    public  int getRandom() {
        Random rand = new Random();
        int rand1 = rand.nextInt(13);
        Log.i("random",""+ rand1) ;
        return rand1;
    }
    public int getRandomFigures(){
        Random rand = new Random();
        int rand1 = rand.nextInt(3);
        Log.i("randomFigures",""+ rand1) ;
        return rand1;
    }
    public void onClickHit(View v){
        switch (eventCount){
            case 0:
                addCardBotToScreen(thirdcardbot,cardsValue.get(getRandom()));
                addCardPlayerToScreen(thirdcard, cardsValue.get(getRandom()));
                checkSum();
                eventCount++;
                break;
            case 1:
                addCardBotToScreen(fourthcardbot,cardsValue.get(getRandom()));
                addCardPlayerToScreen(fourthcard,cardsValue.get(getRandom()));
                checkSum();
                eventCount++;
                break;
            case 2:
                addCardBotToScreen(fifthcardbot,cardsValue.get(getRandom()));
                addCardPlayerToScreen(fifthcard,cardsValue.get(getRandom()));
                checkSum();
                eventCount++;
                break;
        }

    }
    public void addCardPlayerToScreen(LinearLayout card, int temp_card){
        ImageView cardImage = new ImageView(this);
        String cardname;
        if (temp_card >= 9){
            int temp = getRandomFigures();
            cardname = "" + cardsFigures.get(temp);
            hand_player += temp_card;
        }else {
            cardname = "" + cards.get(temp_card);
            hand_player += temp_card + 1;
        }
        cardImage.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
        card.addView(cardImage);
    }
    public void addCard(){
        int temp_card = cardsValue.get(getRandom());
        addCardPlayerToScreen(firstcard,temp_card);
        temp_card = cardsValue.get(getRandom());
        addCardPlayerToScreen(secondcard,temp_card);

        temp_card = cardsValue.get(getRandom());
        addCardBotToScreen(firstcardbot,temp_card);

        temp_card = cardsValue.get(getRandom());
        addCardBotToScreen(secondcardbot,temp_card);

        checkSum();
    }
    public void showBotCards(){
        ImageView cardImage = new ImageView(this);
        String cardname;
        for(int i =0 ; i< botCards.size();i++){
            if (botCards.get(i) >= 9){
                int temp = getRandomFigures();
                cardname = "" + cardsFigures.get(temp);
            }else {
                cardname = "" + cards.get(i);
            }
            cardImage.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
            if(firstcardbot.getParent() != null)
                ((ViewGroup)firstcardbot.getParent()).removeView(firstcardbot);
            firstcardbot.addView(cardImage);
            /*switch (i){
                case 0:

                    break;
                case 1:
                    if(secondcardbot.getParent() == null)
                        ((ViewGroup)secondcardbot.getParent()).removeView(secondcardbot);
                    secondcardbot.addView(cardImage);
                    break;
                case 2:
                    thirdcardbot.removeAllViews();
                    thirdcardbot.addView(cardImage);
                    break;
                case 3:
                    fourthcardbot.removeAllViews();
                    fourthcardbot.addView(cardImage);
                    break;
                case 4:
                    fifthcardbot.invalidate();
                    fifthcardbot.addView(cardImage);
                    break;*/

        }

    }
    public void addCardBotToScreen(LinearLayout card, int temp_card){
        botCards.add(temp_card);
        ImageView cardImage = new ImageView(this);
        String cardname;
        if (temp_card >= 9){
            int temp = getRandomFigures();
            cardname = "" + cardsFigures.get(temp);
            hand_dealer += temp_card;
        }else {
            cardname = "" + cards.get(temp_card);
            hand_dealer += temp_card + 1;
        }
        cardname = "yellow_back";
        cardImage.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
        card.addView(cardImage);
    }
    public void checkWinner(){
        if(hand_player > hand_dealer && hand_player <= 21){
            Toast.makeText(BlackjackActivity.this, "VICTORY! " + hand_player +" - " + hand_dealer, Toast.LENGTH_LONG).show();
            win++;
            setScore();
        }
        if(hand_player < hand_dealer && hand_dealer <= 21) {
            Toast.makeText(BlackjackActivity.this, "DEFEAT! " + hand_dealer + " -  " + hand_player, Toast.LENGTH_LONG).show();
            lose++;
            setScore();
        }else
            Toast.makeText(BlackjackActivity.this, "DRAW! " + hand_player +" - " + hand_dealer, Toast.LENGTH_LONG).show();
    }
    public void onClickStand(View v){
        checkWinner();
    }
    public void checkSum() {
        if (hand_player > 21) {
            Toast.makeText(BlackjackActivity.this, "DEFEAT! " + hand_player, Toast.LENGTH_SHORT).show();
            lose++;
            setScore();
        }else
            Toast.makeText(BlackjackActivity.this, "" + hand_player, Toast.LENGTH_LONG).show();

    }
}