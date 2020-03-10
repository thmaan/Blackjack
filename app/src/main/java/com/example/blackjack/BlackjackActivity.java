package com.example.blackjack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    int eventCount;
    int win;
    int lose;
    String username;
    ConstraintLayout toolbar;
    TextView scoreboard;
    HashMap<Integer,String> cardMap;
    HashMap<String, Integer> cardValueMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);
        Bundle bundle = getIntent().getExtras();
        firstcard = findViewById(R.id.firstcard);
        secondcard = findViewById(R.id.secondcard);
        thirdcard = findViewById(R.id.thirdcard);
        fourthcard = findViewById(R.id.fourthcard);
        fifthcard = findViewById(R.id.fifthcard);
        toolbar = findViewById(R.id.toolbar);
        scoreboard = findViewById(R.id.scoreboard);

        firstcardbot = findViewById(R.id.firstcarbot);
        secondcardbot = findViewById(R.id.secondcarbot);
        thirdcardbot = findViewById(R.id.thirdcardbot);
        fourthcardbot = findViewById(R.id.fourthcardbot);
        fifthcardbot = findViewById(R.id.fifthcardbot);
        username = bundle.getString("username");
        newGame();
    }
    public void newGame(){
        initVariables();
        addCard();
        setScore();
    }
    public void initVariables(){
        win = 0;
        lose = 0 ;
        eventCount = 0;
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
        scoreboard.setText("Hello " + username + " Win: " + win + " Lose: " + lose);
    }

    public  int getRandom() {
        Random rand = new Random();
        int rand1 = rand.nextInt(13) + 1;
        Log.i("random",""+ rand1) ;
        return rand1;
    }
    public void onClickHit(View v){
        switch (eventCount){
            case 0:
                addCardBotToScreen(thirdcardbot,cardMap.get(getRandom()));
                addCardPlayerToScreen(thirdcard, cardMap.get(getRandom()));
                checkSum();
                eventCount++;
                break;
            case 1:
                addCardBotToScreen(fourthcardbot,cardMap.get(getRandom()));
                addCardPlayerToScreen(fourthcard,cardMap.get(getRandom()));
                checkSum();
                eventCount++;
                break;
            case 2:
                addCardBotToScreen(fifthcardbot,cardMap.get(getRandom()));
                addCardPlayerToScreen(fifthcard,cardMap.get(getRandom()));
                checkSum();
                eventCount++;
                break;
        }

    }
    public void addCardPlayerToScreen(ImageView card, String cardname){
        int temp = cardValueMap.get(cardname);
        Log.i("teste addCardPlayer", ""+temp);
        if(temp == 1 && hand_player < 21){
            hand_player += cardValueMap.get(cardname) + 10;
        }else
            hand_player += cardValueMap.get(cardname);
        card.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
    }
    public void addCard(){
        addCardPlayerToScreen(firstcard,cardMap.get(getRandom()));
        addCardPlayerToScreen(secondcard,cardMap.get(getRandom()));
        addCardBotToScreen(firstcardbot,cardMap.get(getRandom()));
        addCardBotToScreen(secondcardbot,cardMap.get(getRandom()));
    }
    public void addCardBotToScreen(ImageView card, String cardname){
        int temp = cardValueMap.get(cardname);
        if(temp == 1 && hand_dealer < 21){
            hand_dealer += cardValueMap.get(cardname) + 10;
        }else
            hand_dealer += cardValueMap.get(cardname);
        cardname = "yellow_back";
        card.setImageResource(getResources().getIdentifier(cardname,"drawable",BlackjackActivity.this.getPackageName()));
    }
    public void checkWinner(){
        if (hand_player > 21 && hand_dealer < 21) {
            Toast.makeText(BlackjackActivity.this, "DEFEAT! " + hand_dealer + " -  " + hand_player, Toast.LENGTH_LONG).show();
            lose++;
            setScore();
        }else
            if(hand_dealer > 21 && hand_player < 21){
                Toast.makeText(BlackjackActivity.this, "VICTORY! " + hand_player +" - " + hand_dealer, Toast.LENGTH_LONG).show();
                win++;
                setScore();
            }else
                if(hand_player > hand_dealer){
                    Toast.makeText(BlackjackActivity.this, "VICTORY! " + hand_player +" - " + hand_dealer, Toast.LENGTH_LONG).show();
                    win++;
                    setScore();
                }else {
                    if (hand_player < hand_dealer) {
                        Toast.makeText(BlackjackActivity.this, "DEFEAT! " + hand_dealer + " -  " + hand_player, Toast.LENGTH_LONG).show();
                        lose++;
                        setScore();
                    } else
                        Toast.makeText(BlackjackActivity.this, "DRAW! " + hand_player + " - " + hand_dealer, Toast.LENGTH_LONG).show();
                }
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

    public void onClickNewGame(View view) {
        newGame();
    }
}