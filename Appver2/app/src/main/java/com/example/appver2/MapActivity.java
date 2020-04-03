package com.example.appver2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.List;


public class MapActivity extends AppCompatActivity {
    //데이터 베이스
    FirebaseDatabase database;

    //데이터베이스의 정보
    DatabaseReference ref;

    //정보 담을 객체
    List<User> userList = new ArrayList<>();

    //화면에 뿌려줄 뷰
   ListView listView;

    //데이터 넣어줄 어뎁터
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //파이어 베이스와 연결
        database = FirebaseDatabase.getInstance();
        Log.i("start", "start");

        // 파이어 베이스에서 레퍼런스를 가져옴
        ref = database.getReference("User");

        // 파이어베이스 에서 데이터를 가져 옴
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 부모가 User 인데 부모 그대로 가져오면 User 각각의 데이터 이니까 자식으로 가져와서 담아줌
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    User user = snapshot.getValue(User.class);
                    user.User = snapshot.getKey();
                    Log.i("id", user.User);
                    Log.i("Userid", user.getUserid());
                    Log.i("UserAge", user.getUserage());

                    userList.add(user);
                }

                //어뎁터한테 데이터 넣어줬다고 알려줌 (안하면 화면에 안나온다)
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
            }
        });

        // 4. 리스트뷰에 목록 세팅
       listView = (ListView) findViewById(R.id.listview);
        adapter = new ListAdapter(userList, this);
        listView.setAdapter(adapter);


    }

//    class BoardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return null;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//    }
}