package com.example.fragmentex;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import msg.Msg;

public class MainFragment extends Fragment {
    //
    // fragment_main.xml 파일과 인플레이션으로 연결해주는것을 메모리 객체화를 시켜주어야한다
    private Socket clientSocket;

    Msg msg;

    ImageView DriveStart;
    Button button5, button6;

    FrameLayout container;
    String id = "id01";
//    msg = new Msg(id, "1", null);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 인플레이션이 가능하다, container 이쪽으로 붙여달라, fragment_main을
        final ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);
        Button button5 = (Button) rootview.findViewById(R.id.button5);
        container = (FrameLayout) rootview.findViewById(R.id.container);

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                MainActivity mainActivity = (MainActivity) getActivity();
//                mainActivity.onFragmentChanged(0);
                    boolean clicks=true;
                Log.d("--------", "setOnClickListener");
                if(id !=null) {
                    Log.d("---------id는:", id);
                    if(clicks == true) {

                            Toast.makeText(getActivity(),"1번째클릭",Toast.LENGTH_SHORT).show();
                            Log.d("--------","setOnClickListener");
//                        new MainActivity.ConnectThread("70.12.225.143", 8888).start(); //Thread시작

                            clicks=false;
                        } else {
                            Toast.makeText(getActivity(),"2번째클릭",Toast.LENGTH_SHORT).show();
                            //엔진off (0을 여기서 보내면 시동이 꺼진다.)
//                            new MainActivity.DisconnectThread("70.12.225.143", 8888).start(); //Thread시작
                        }

                }


            }
        });

//        Button button5 = getActivity().findViewById(R.id.button5);
        return rootview;            // 플레그먼트 화면으로 보여주게 된다.
    }






}
