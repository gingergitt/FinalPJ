package com.example.appver2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PaymentFragment extends Fragment {

//
//    MapActivity activity;
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        activity = (MapActivity) getActivity();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        activity = null;
//    }


    private RecyclerView mVerticalView;
    private VerticalAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;




    public PaymentFragment () {}



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.activity_map,container,false);
        mVerticalView = (RecyclerView) rootview.findViewById(R.id.vertical_list);
//        verticalDatas1 = VerticalData.creteContactList(5);
        mVerticalView.setHasFixedSize(true);
//        mAdapter = new VerticalAdapter(getActivity(),verticalDatas1);
        mVerticalView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVerticalView.setAdapter(mAdapter);
        mVerticalView.scrollToPosition(0);
        mVerticalView.setItemAnimator(new DefaultItemAnimator());


        Intent intent1 = new Intent(getActivity(),MapActivity.class);
        startActivity(intent1);

        Log.e("Fragment확인:","PaymentFragment");
       return rootview;
    }
}
