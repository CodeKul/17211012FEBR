package com.codekul.compoundviews;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG =MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAdd).setOnClickListener(this::createNewView);

        LinearLayout layoutInner = (LinearLayout) findViewById(R.id.layoutInner);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.compound_view, layoutInner, false);
    }

    private void createNewView(View view) {

        LinearLayout layoutInner = (LinearLayout) findViewById(R.id.layoutInner);
        //LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = LayoutInflater.from(this);
        LayoutInflater inflater = getLayoutInflater();
        View compoundView = inflater.inflate(R.layout.compound_view, layoutInner, false);
        compoundView.setOnLongClickListener(this::onLongClick);
        ImageView imgVw = (ImageView) compoundView.findViewById(R.id.imgVw);
        TextView txtVw = (TextView) compoundView.findViewById(R.id.txtVw);
        txtVw.setText(String.valueOf(System.currentTimeMillis() % 2));

        layoutInner.addView(compoundView);
    }

    private boolean onLongClick(View view) {
        Log.i(TAG, "Is Compound " + (view.getId() == R.id.layoutCompound));
        LinearLayout layoutInner = (LinearLayout) findViewById(R.id.layoutInner);
        layoutInner.removeView(view);
        return true;
    }


}
