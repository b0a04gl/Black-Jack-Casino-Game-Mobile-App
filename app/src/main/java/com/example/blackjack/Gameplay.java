package com.example.blackjack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Card
{


    String suit,rank;
    int value;
    int imageId;
    Card(String suit,String rank,int value,int imageId)
    {
        this.suit=suit;
        this.rank=rank;
        this.value=value;
        this.imageId=imageId;
    }

    @NonNull
    @Override
    public String toString() {
        return suit+" "+rank+" "+value;
    }
}



class ComputerDealer
{
    String[] suits = {"Spade","Clover","Heart In","Diamond"};
    String[] ranks = {"ace","two","three","four","five","six","seven","eight","nine","ten","king","queen","jack"};
    int[] values = {1,2,3,4,5,6,7,8,9,10};

    List<Card> deck;
    HashSet<Integer> used;
    HashMap<String,Integer> lookup;
    HashMap<String,Integer> imageLookup;
    Random picker;
    int index;
    ComputerDealer()
    {
//        Log.d("class created","computerdealer class created");
        deck = new ArrayList<>();
        picker = new Random();
        used = new HashSet<>();
        lookup = new HashMap<>();
        imageLookup = new HashMap<>();
        index = 0;

        fillImageLookup();
        fillLookup();
        createDeck();

    }

    void fillImageLookup()
    {
        imageLookup.put("Clover ace",R.mipmap.clover_ace);
        imageLookup.put("Clover two",R.mipmap.clover_two);
        imageLookup.put("Clover three",R.mipmap.clover_three);
        imageLookup.put("Clover four",R.mipmap.clover_four);
        imageLookup.put("Clover five",R.mipmap.clover_five);
        imageLookup.put("Clover six",R.mipmap.clover_six);
        imageLookup.put("Clover seven",R.mipmap.clover_seven);
        imageLookup.put("Clover eight",R.mipmap.clover_eight);
        imageLookup.put("Clover nine",R.mipmap.clover_nine);
        imageLookup.put("Clover ten",R.mipmap.clover_ten);
        imageLookup.put("Clover king",R.mipmap.clover_king);
        imageLookup.put("Clover queen",R.mipmap.clover_queen);
        imageLookup.put("Clover jack",R.mipmap.clover_jack);

        imageLookup.put("Diamond ace",R.mipmap.diamond_ace);
        imageLookup.put("Diamond two",R.mipmap.diamond_two);
        imageLookup.put("Diamond three",R.mipmap.diamond_three);
        imageLookup.put("Diamond four",R.mipmap.diamond_four);
        imageLookup.put("Diamond five",R.mipmap.diamond_five);
        imageLookup.put("Diamond six",R.mipmap.diamond_six);
        imageLookup.put("Diamond seven",R.mipmap.diamond_seven);
        imageLookup.put("Diamond eight",R.mipmap.diamond_eight);
        imageLookup.put("Diamond nine",R.mipmap.diamond_nine);
        imageLookup.put("Diamond ten",R.mipmap.diamond_ten);
        imageLookup.put("Diamond king",R.mipmap.diamond_king);
        imageLookup.put("Diamond queen",R.mipmap.diamond_queen);
        imageLookup.put("Diamond jack",R.mipmap.diamond_jack);

        imageLookup.put("Heart In ace",R.mipmap.heart_in_ace);
        imageLookup.put("Heart In two",R.mipmap.heart_in_two);
        imageLookup.put("Heart In three",R.mipmap.heart_in_three);
        imageLookup.put("Heart In four",R.mipmap.heart_in_four);
        imageLookup.put("Heart In five",R.mipmap.heart_in_five);
        imageLookup.put("Heart In six",R.mipmap.heart_in_six);
        imageLookup.put("Heart In seven",R.mipmap.heart_in_seven);
        imageLookup.put("Heart In eight",R.mipmap.heart_in_eight);
        imageLookup.put("Heart In nine",R.mipmap.heart_in_nine);
        imageLookup.put("Heart In ten",R.mipmap.heart_in_ten);
        imageLookup.put("Heart In king",R.mipmap.heart_in_king);
        imageLookup.put("Heart In queen",R.mipmap.heart_in_queen);
        imageLookup.put("Heart In jack",R.mipmap.heart_in_jack);

        imageLookup.put("Spade ace",R.mipmap.spade_ace);
        imageLookup.put("Spade two",R.mipmap.spade_two);
        imageLookup.put("Spade three",R.mipmap.spade_three);
        imageLookup.put("Spade four",R.mipmap.spade_four);
        imageLookup.put("Spade five",R.mipmap.spade_five);
        imageLookup.put("Spade six",R.mipmap.spade_six);
        imageLookup.put("Spade seven",R.mipmap.spade_seven);
        imageLookup.put("Spade eight",R.mipmap.spade_eight);
        imageLookup.put("Spade nine",R.mipmap.spade_nine);
        imageLookup.put("Spade ten",R.mipmap.spade_ten);
        imageLookup.put("Spade king",R.mipmap.spade_king);
        imageLookup.put("Spade queen",R.mipmap.spade_queen);
        imageLookup.put("Spade jack",R.mipmap.spade_jack);


    }


    void fillLookup()
    {
        for(int i=0;i<10;i++)
        {
            lookup.put(ranks[i],values[i]);
        }

        lookup.put("king",10);
        lookup.put("queen",10);
        lookup.put("jack",10);

    }
    void createDeck()
    {
        for(String suit:suits)
        {
            for(String rank: ranks)
            {
                Card newCard = new Card(suit,rank,lookup.get(rank),imageLookup.get(suit+" "+rank));
                deck.add(newCard);
//                Log.d("class card",newCard.toString());
            }
        }
    }

    void shuffle()
    {
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }

    Card requestCard()
    {
        int temp = 0;

        while(true && used.size()<=52)
        {
            temp = picker.nextInt(52);
            if(used.contains(temp)==false)
            {
                index = temp;
                break;
            }
//            Log.d("edge card value",temp+" already slashed");
        }
//        Log.d("card value",index+"");
        used.add(index);

        return deck.get(index);
    }

}

class Player
{
    String name;
    int value;
    List<Card> ownSet;

    Player(String name)
    {
        this.name=name;
        this.value=0;
        ownSet = new ArrayList<Card>();
    }

    void add(Card current)
    {
        ownSet.add(current);
        value+=current.value;
    }

}

public class Gameplay extends AppCompatActivity {

    int iindex;
    ComputerDealer kingsHand;
    String[] names;
    Player[] players;
    Player currPlayer;
    int noOfPlayers;
    TextView cardValue,playerName;
    Dialog myDialog,myDialog2;
    ImageView[] display;
    ImageView currentCard;
    Button continueBtn;
    int index;
    HashMap<String,Integer> stayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        cardValue = (TextView) findViewById(R.id.cardValue);
        continueBtn = (Button) findViewById(R.id.continueBtn);
        myDialog = new Dialog(this);
        myDialog2 = new Dialog(this);
        playerName = (TextView) findViewById(R.id.player_name);
        display = new ImageView[10];
        currentCard = (ImageView)findViewById(R.id.currCard);
        Intent intent = getIntent();
        stayList = new HashMap<>();
        display[0] = (ImageView)findViewById(R.id.cardimg0);
        display[1] = (ImageView)findViewById(R.id.cardimg1);
        display[2] = (ImageView)findViewById(R.id.cardimg2);
        display[3] = (ImageView)findViewById(R.id.cardimg3);
        display[4] = (ImageView)findViewById(R.id.cardimg4);
        display[5] = (ImageView)findViewById(R.id.cardimg5);
        display[6] = (ImageView)findViewById(R.id.cardimg6);
        display[7] = (ImageView)findViewById(R.id.cardimg7);
        display[8] = (ImageView)findViewById(R.id.cardimg8);
        display[9] = (ImageView)findViewById(R.id.cardimg9);

        setNullImage();



        kingsHand = new ComputerDealer();
//        Bundle b = getIntent().getExtras();
//        names = b.getStringArray("names");
        final String nop = intent.getStringExtra("noOfPlayers");
         noOfPlayers = Integer.parseInt(nop);
        names = new String[noOfPlayers];

        for(int i=0;i<noOfPlayers;i++)
        {
            String temp= intent.getStringExtra(i+"");
//            Log.d("name",temp+"");
            names[i]=temp;
        }

        Log.d("noOfPlayers : ",noOfPlayers+"");
        players = new Player[noOfPlayers];

        for(int i=0;i<noOfPlayers-1;i++) {
            players[i] = new Player(names[i]);
            initialDistribution(players[i]);
        }

        names[noOfPlayers-1]="Computer Dealer";
        players[noOfPlayers-1] = new Player(names[noOfPlayers-1]);
        initialDistribution(players[noOfPlayers-1]);

        index=0;
        currPlayer = players[index];
        iindex=0;
        playerName.setText(currPlayer.name+"'s turn");
        fillImage(currPlayer.ownSet.get(iindex),iindex);
        iindex++;
        fillImage(currPlayer.ownSet.get(iindex),iindex);
        iindex++;
        cardValue = (TextView)findViewById(R.id.cardValue);
        String val = currPlayer.value+"";
        cardValue.setText(val);
        currentCard.setBackgroundResource(currPlayer.ownSet.get(1).imageId);
    }

     void initialDistribution(Player currPlayer)
    {
        Card one = kingsHand.requestCard();
        currPlayer.add(one);
//        cardValue.setText(String.valueOf(currPlayer.value));
        Log.d("initial card",currPlayer.name+" "+one.toString());
        Card two = kingsHand.requestCard();
        currPlayer.add(two);
        Log.d("initial card",currPlayer.name+" "+two.toString());
//        cardValue.setText(String.valueOf(currPlayer.value));

    }


    String checkGameStatus(Player currPlayer)
    {
        if(currPlayer.value==21)
            return "black jack";

        else if(currPlayer.value>21)
            return "out burst";

//        else if(currPlayer.value>=1 && currPlayer.value<21)
            return  "continue";

    }

    void declareWinner(Player currPlayer)
    {
        Log.d("Winner is ",currPlayer.name+"");

//        winnerPopup(currPlayer.name);
    }

    void fillImage(Card temp,int imgIndex)
    {
       display[imgIndex].setBackgroundResource(temp.imageId);
    }

    public void setNullImage()
    {
        display[0].setBackgroundResource(0);
        display[1].setBackgroundResource(0);
        display[2].setBackgroundResource(0);

        display[3].setBackgroundResource(0);
        display[4].setBackgroundResource(0);
        display[5].setBackgroundResource(0);

        display[6].setBackgroundResource(0);
        display[7].setBackgroundResource(0);
        display[8].setBackgroundResource(0);
        display[9].setBackgroundResource(0);
    }

    public void ShowPopup(View v) {
//        if(index>noOfPlayers)
//            myDialog.dismiss();

        final Button hit,stay;
        myDialog.setContentView(R.layout.choice_popup);

        hit = (Button) myDialog.findViewById(R.id.hitBtn);
        stay = (Button) myDialog.findViewById(R.id.stayBtn);

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Card slashedCard = kingsHand.requestCard();
                Log.d("current card",slashedCard.toString());
                currPlayer.add(slashedCard);
                cardValue.setText(String.valueOf(currPlayer.value));
                fillImage(slashedCard,iindex);
                iindex++;

                currentCard.setBackgroundResource(slashedCard.imageId);

                Log.d("current card",slashedCard.toString());
                Log.d("player value",currPlayer.name+"---"+currPlayer.value);
                String status = checkGameStatus(currPlayer);
                Log.d("status ",status+"");
                if(status.equals("black jack"))
                {
                    declareWinner(currPlayer);

                    myDialog2.setContentView(R.layout.winner);
                    TextView won = (TextView)myDialog2.findViewById(R.id.won_player);
                    ImageView closeBtn = (ImageView)myDialog2.findViewById(R.id.closeBtn);
                    won.setText(currPlayer.name);

                    closeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            myDialog2.dismiss();
                        }
                    });

                    myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog2.show();

                   myDialog.dismiss();

                }
                if(status.equals("out burst"))
                {
                    ++index;


                    if(index>=noOfPlayers-1 && (stayList.size()>=1))
                    {
                        decideWinner();
                        myDialog.dismiss();
                    }

                    currPlayer = players[index];
                    iindex=0;

                   playerName.setText(currPlayer.name+"'s turn");

                   setNullImage();
                    fillImage(currPlayer.ownSet.get(iindex),iindex);
                    iindex++;
                    fillImage(currPlayer.ownSet.get(iindex),iindex);
                    iindex++;

                    currentCard.setBackgroundResource(currPlayer.ownSet.get(1).imageId);
                  //  cardValue = (TextView)findViewById(R.id.cardValue);
                    String val = currPlayer.value+"";
                    cardValue.setText(val);
                    myDialog.dismiss();

                }

                myDialog.dismiss();

            }
        });

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stayList.put(currPlayer.name,currPlayer.value);

                ++index;


                if(index>=noOfPlayers-1 && (stayList.size()>=1))
                {
                    Log.d("index",index+" "+noOfPlayers);
                    decideWinner();
                    Log.d("stayed player",currPlayer.name+" index : "+index);
                    myDialog.dismiss();
                }

                currPlayer = players[index];

                iindex=0;

                playerName.setText(currPlayer.name+"'s turn");

                setNullImage();
                fillImage(currPlayer.ownSet.get(iindex),iindex);
                iindex++;
                fillImage(currPlayer.ownSet.get(iindex),iindex);
                iindex++;

                currentCard.setBackgroundResource(currPlayer.ownSet.get(1).imageId);
               // cardValue = (TextView)findViewById(R.id.cardValue);
                String val = currPlayer.value+"";
                cardValue.setText(val);
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


    }

    public void decideWinner()
    {


        Player winner=null;
        int maxVal = 0;
        for(Map.Entry<String,Integer>e:stayList.entrySet())
        {
            if(maxVal<e.getValue())
            {
                maxVal=e.getValue();
                winner = new Player(e.getKey()+"");
            }
        }

        declareWinner(winner);
        myDialog2.setContentView(R.layout.winner);
        TextView won = (TextView)myDialog2.findViewById(R.id.won_player);
        ImageView closeBtn = (ImageView)myDialog2.findViewById(R.id.closeBtn);
        won.setText(winner.name);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                myDialog2.dismiss();
            }
        });

        myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog2.show();
    }



}