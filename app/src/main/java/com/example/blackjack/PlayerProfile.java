package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlayerProfile extends AppCompatActivity {
    int noOfPlayers;
    Button nextBtn;
    ImageButton user1,user2,user3,user4,user5,user6;
    Dialog myDialog;
    String[] names;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        Intent intent = getIntent();
        final String nop = intent.getStringExtra("noOfPlayers");
        noOfPlayers = Integer.parseInt(nop);
        names = new String[noOfPlayers];
        index=0;
        myDialog = new Dialog(this);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Gameplay.class);
                intent.putExtra("noOfPlayers",nop+"");

                for(int i=0;i<noOfPlayers;i++)
                {
                    intent.putExtra(i+"",names[i]+"");
                }
                startActivity(intent);
            }
        });
    }

    public void ShowPopup(View v) {

        TextView txtclose;
        final EditText player_name;
        Button btnOk;
        myDialog.setContentView(R.layout.popup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        player_name = (EditText)myDialog.findViewById(R.id.player_name);
        btnOk = (Button) myDialog.findViewById(R.id.btnfollow);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names[index] = player_name.getText()+"";

//                Log.d("player name : ",names[index]);
                index++;

                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


    }
}