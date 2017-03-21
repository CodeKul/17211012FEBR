package com.codekul.externalstorage;

import android.os.Environment;
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
        if(isAvailable())  writeAppPublic();
        else Log.i(TAG, "Media UnAvailable");
    }

    public void onRead(View view) {
        if(isAvailable()) readAppPublic();
    }

    public void onInfo(View view) {
    }

    private Boolean isAvailable() {
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    private void saveAppPrivate() {
        // /storage/emulated/0/Android/data/com.codekul.externalstorage/files/myApp/my.txt
        try {
            File root = new File(getExternalFilesDir("myApp"), "my.txt");
            FileOutputStream fos = new FileOutputStream(root);
            fos.write("this is codekul".getBytes());
            fos.close();
            Log.i(TAG, "File saved at - "+root.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readAppPrivate() {
        try {
            File root = new File(getExternalFilesDir("myApp"), "my.txt");
            FileInputStream fis = new FileInputStream(root);
            StringBuilder builder = new StringBuilder();
            while(true) {
                int ch = fis.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
            fis.close();
            Log.i(TAG, "data is - "+builder.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeAppPublic() {
        try {
            File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "my.txt");
            FileOutputStream fos = new FileOutputStream(root);
            fos.write("this is codekul".getBytes());
            fos.close();
            Log.i(TAG, "File saved at - "+root.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readAppPublic() {
        try {
            File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "my.txt");
            FileInputStream fis = new FileInputStream(root);
            StringBuilder builder = new StringBuilder();
            while(true) {
                int ch = fis.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
            fis.close();
            Log.i(TAG, "data is - "+builder.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
