package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    ListView mlistView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
         mlistView = (ListView) findViewById(R.id.notification_list);

        DatabaseReference peopleReference = FirebaseDatabase.getInstance().getReference()
                .child("people");


//        mlistView.setAdapter(new FirebaseListAdapter<Person>(this, Person.class,
//                android.R.layout.notification_list, peopleReference) {
//
//            // Populate view as needed
//            @Override
//            protected void populateView(View view, Person person, int position) {
//                ((TextView) view.findViewById(android.R.id.text1)).setText(person.getName());
//            }
//        });
    }


}
