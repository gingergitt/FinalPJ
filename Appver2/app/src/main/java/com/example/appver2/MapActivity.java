package com.example.appver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ListView listView = (ListView) findViewById(R.id.notification_list);
        adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);


    }


}
