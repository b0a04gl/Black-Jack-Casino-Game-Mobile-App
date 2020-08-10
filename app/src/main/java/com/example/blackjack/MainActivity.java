package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edt = (EditText)findViewById(R.id.editTextNumber);
        proceed = (Button)findViewById(R.id.proceedBtn);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),PlayerProfile.class);
               int nop = Integer.parseInt(edt.getText()+"");
               nop++;
               intent.putExtra("noOfPlayers",nop+"");
               startActivity(intent);
            }
        });

    }
}