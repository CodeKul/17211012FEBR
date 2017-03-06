package com.codekul.simplevalueresources;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String txtHdr = getResources().getString(R.string.txtHdr);
        int white = getResources().getColor(R.color.white);
        white = ContextCompat.getColor(this, R.color.white);
    }
}
