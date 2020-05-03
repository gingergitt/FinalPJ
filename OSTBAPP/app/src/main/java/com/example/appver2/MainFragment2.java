package com.example.appver2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainFragment2 extends Fragment {

    DriveActivity activity;
    Fragment fragment1,fragment2;
    FragmentManager fragmentManager;
    Button button5;
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        activity = (MainActivity) getActivity();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        activity = null;
//    }

//    public static MainFragment newInstance() {
//        MainFragment fragment = new MainFragment();
//        Bundle args = new Bundle();
//        //args.putString(ARG_PARAM1, param1);
//       // args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static MainFragment2 newInstance() {
        return new MainFragment2();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        Button button5 = (Button) getActivity().findViewById(R.id.button5);
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.activity_drive,container,false);
        Button button = (Button) getActivity().findViewById(R.id.button);


        
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        return rootview;
    }



}
