package com.codekul.fragmentsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(LoginFragment.fragment());
    }

    public void loadFragment(Fragment fragment) {

        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();

        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) {
            FragmentTransaction txn = manager.beginTransaction();
            txn.replace(R.id.frameContainer, fragment);
            txn.addToBackStack(backStateName);
            txn.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1 // if u r on login page
                || getSupportFragmentManager().getBackStackEntryCount() == 2)  { // u r on courses pages
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
