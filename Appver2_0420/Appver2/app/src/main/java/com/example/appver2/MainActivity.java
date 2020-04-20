package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appver2.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
//    TextView cardStatus1, cardStatus2, cardStatus3, cardStatus4, cardStatus5;
    Button button,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

    }

    public void ctbt(View v){
        if(v.getId() == R.id.button){
            Intent intent =
                    new Intent(getApplicationContext(),
                            RegisterActivity.class);



            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            Intent intent =
                    new Intent(getApplicationContext(),
                            LoginActivity.class);

            startActivity(intent);
        }else if (v.getId() == R.id.button3) {
            Intent intent =
                    new Intent(getApplicationContext(),
                            MapActivity.class);

            startActivity(intent);
        }else if (v.getId() == R.id.button4) {
            Intent intent =
                    new Intent(getApplicationContext(),
                            CheckOutActivity.class);

            startActivity(intent);
        }


    }



}
