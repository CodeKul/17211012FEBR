package com.codekul.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSave(View view) {

        try {
            FileOutputStream fos = openFileOutput("my.txt", MODE_APPEND);
            fos.write("This codekul.com".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDisplay(View view) {
        try {
            FileInputStream fis = openFileInput("my.txt");
            StringBuilder builder = new StringBuilder();
            while (true) {
                int ch = fis.read();
                if (ch == -1) break;
                else builder.append((char) ch);
            }
            Log.i(TAG, "Data From File " + builder.toString());
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onInfo(View view) {
        // /data/user/0/com.codekul.internalstorage/files
        // /data/user/0/com.codekul.internalstorage/shared_prefs
        // /data/user/0/com.codekul.internalstorage/database
        // /data/user/0/com.codekul.internalstorage/cache

        File file = getFilesDir();
        Log.i(TAG, "Internal Storage Path - "+file.getAbsolutePath());

        File myDir = new File(file, "myDir");
        Log.i(TAG, "is directory created ? "+myDir.mkdir());

        String []files = fileList();
        for(String name : files) Log.i(TAG, "File is "+name);
    }
}
