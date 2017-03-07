package com.codekul.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(R.drawable.ic_boy);
    }

    public void loadFragment(int imgId) {

        Bundle bundle = new Bundle();
        bundle.putInt("imgId", imgId);
        ImageFragment imgFrag = new ImageFragment();
        imgFrag.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction txn = manager.beginTransaction();
        txn.replace(R.id.frameContainer, imgFrag);
        txn.commit();
    }
}
