package com.codekul.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> dataSet = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet.add("Android");
        dataSet.add("iOS");
        dataSet.add("Rim");
        dataSet.add("Symbian");
        dataSet.add("Bada");
        dataSet.add("Windows");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSet);

        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);
        ((ListView)findViewById(R.id.listView)).setOnItemClickListener(this::onListClick);

        findViewById(R.id.btnAdd).setOnClickListener(this::newOs);
    }

    private void onListClick(AdapterView<?> adapterView, View view, int pos, long id) {
        ((EditText)findViewById(R.id.edtOs)).setText(adapter.getItem(pos));
    }

    private void newOs(View view) {
        dataSet.add(((EditText)findViewById(R.id.edtOs)).getText().toString());
        adapter.notifyDataSetChanged();
    }
}
