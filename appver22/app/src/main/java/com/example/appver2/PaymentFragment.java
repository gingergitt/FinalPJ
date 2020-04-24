package com.example.appver2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.appver2.ui.login.LoginActivity.idbt;
import static com.example.appver2.ui.login.LoginActivity.pwdbt;


public class PaymentFragment extends Fragment {


    private RecyclerView mVerticalView;
    private VerticalAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private static ArrayList<VerticalData> data;
    private int MAX_ITEM_COUNT = 5;


    public PaymentFragment () {}

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        data = new ArrayList<>();
        ArrayList<ImageView> img = new ArrayList<>();


        int i = 0;
        while (i < MAX_ITEM_COUNT) {
            if(i==0){
                data.add(new VerticalData(R.drawable.registercard, i+"번째 데이터"));
            }else if(i==1){
                data.add(new VerticalData(R.drawable.hana, i+"번째 데이터"));
            }
            else if(i==2){
                data.add(new VerticalData(R.drawable.shinhan, i+"번째 데이터"));
            }else if(i==3){
                data.add(new VerticalData(R.drawable.woori, i+"번째 데이터"));
            }else if(i==4){
                data.add(new VerticalData(R.drawable.ibk, i+"번째 데이터"));
            }
            i++;
        }

        View view = inflater.inflate(R.layout.activity_map, container, false);

        mVerticalView = view.findViewById(R.id.vertical_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mVerticalView.setLayoutManager(layoutManager);
      //  mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mAdapter = new VerticalAdapter();
        mAdapter.setData(data);
        mVerticalView.setAdapter(mAdapter);
        return view;

      //  Log.e("Fragment확인:","PaymentFragment");

    }
}
