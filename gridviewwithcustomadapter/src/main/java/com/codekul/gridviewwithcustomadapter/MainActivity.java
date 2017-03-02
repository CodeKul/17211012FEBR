package com.codekul.gridviewwithcustomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GridItem> dataSet = new ArrayList<>();
        dataSet.add(new GridItem(1, R.mipmap.ic_launcher, "Call"));
        dataSet.add(new GridItem(2, R.mipmap.ic_launcher, "Gallery"));
        dataSet.add(new GridItem(3, R.mipmap.ic_launcher, "Settings"));
        dataSet.add(new GridItem(4, R.mipmap.ic_launcher, "Camera"));
        dataSet.add(new GridItem(5, R.mipmap.ic_launcher, "Location"));
        dataSet.add(new GridItem(6, R.mipmap.ic_launcher, "Map"));

        final MyAdapter adapter = new MyAdapter(this, dataSet);
        ((GridView)findViewById(R.id.gridMenus)).setAdapter(adapter);
        ((GridView)findViewById(R.id.gridMenus)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GridItem item = (GridItem) adapter.getItem(position);
            }
        });

    }
}
