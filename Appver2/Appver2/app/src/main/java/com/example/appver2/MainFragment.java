package com.example.appver2;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
//    MainActivity activity;
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

    public static MainFragment newInstance() {
        return new MainFragment();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.activity_drive,container,false);
        // rootview가 플래그먼트 화면으로 보이게 된다. 부분화면을 보여주고자하는 틀로 생각하면 된다.

//
//        Button button = (Button) rootview.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            // 요청을 보내야 하는데 메인 액티비티에 다가 메소드를 하나 만들어야 한다.
//            @Override
//            public void onClick(View v) {
//                activity.onFragmentChange(1);
//
//            }
//        });


//        getActivity();      // 액티비티위에 올라가게 한다.



        return rootview;
    }
    // 하나의 fragment 완성
}
