package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appver2.ui.login.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // MainFragment fragment1;
   // PaymentFragment fragment2;
    public ArrayList<VerticalData> temparray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fragment1 = new MainFragment();
        //fragment2 = new PaymentFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, MainFragment.newInstance()).commit();



        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit();




            }
        });


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container,PaymentFragment.newInstance()).commit(); // 여러개의 명을 만들어서 쓴다

            }
        });


    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }



    public void onFragmentChange(int index){
        if(index ==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit();

        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,PaymentFragment.newInstance()).commit();
        }
    }

}
