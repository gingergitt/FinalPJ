package com.example.appver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MapActivity extends AppCompatActivity {


    private List<lst> itemsList = new ArrayList<lst>();
    private RecyclerViewAdapter adapter;
    RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        recyclerview = findViewById(R.id.recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(this, itemsList);
        recyclerview.setAdapter(adapter);
        Set<String> noti_set = PreferenceManager.getDefaultSharedPreferences(this).getStringSet("noti_set", new HashSet<String>());
        for (String noti : noti_set) {
            String[] notification = noti.split("---");
            String title = notification[0];
            String msg = notification[1];
            lst ls = new lst();
            ls.setMsg(msg);
            ls.setTitle(title);
            itemsList.add(ls);
            adapter.notifyDataSetChanged();


        }


    }
}




//----------------------
// 파이어베이스 에서 데이터를 가져 옴
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//
//                    User user = snapshot.getValue(User.class);
//                    user.User = snapshot.getKey();
//                    Log.i("id", user.User);
//                    Log.i("Userid", user.getUserid());
//                    Log.i("UserAge", user.getUserage());
//
//                    userList.add(user);
//                }
//
//                //어뎁터한테 데이터 넣어줬다고 알려줌 (안하면 화면에 안나온다)
////                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
//            }
//        });