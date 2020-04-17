package com.example.cardcheckflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   TextView cardStatus1, cardStatus2, cardStatus3, cardStatus4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardStatus1 = findViewById(R.id.cardStatus1);
        cardStatus2 = findViewById(R.id.cardStatus2);
        cardStatus3 = findViewById(R.id.cardStatus3);
        cardStatus4 = findViewById(R.id.cardStatus4);

        Intent i = getIntent();
        String cardname = i.getExtras().getString("cardname");
        String cardNumber = i.getExtras().getString("cardNumber");
        String cardValidity = i.getExtras().getString("cardValidity");
        String cardCVV = i.getExtras().getString("cardCVV");

        cardStatus1.setText(cardname);
        cardStatus2.setText(cardNumber);
        cardStatus3.setText(cardValidity);
        cardStatus4.setText(cardCVV);
//        cardStatus.setText(cardnumber);


    }


}
