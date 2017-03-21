package com.codekul.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRetrieve(View view) {
        final SharedPreferences prefs = getSharedPreferences("my", MODE_PRIVATE);
        String name = prefs.getString("myName", "none");
        int age = prefs.getInt("myAge", -9);
        float sal = prefs.getFloat("mySal", -6f);

        Log.i(TAG, "Name - "+name +" Age - "+age + " Sal - "+sal);
    }

    public void onSave(View view) {

        //final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        final SharedPreferences prefs = getSharedPreferences("my", MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString("myName", "android");
        editor.putInt("myAge", 10);
        editor.putFloat("mySal", 45);
        editor.apply();
    }
}
