package com.codekul.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekul.domain.Hello;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        findViewById(R.id.btnOwner).setOnClickListener(this::parseOwners);
        findViewById(R.id.btnParent).setOnClickListener(this::parseParent);
    }

    private void parseParent(View view) {
        parentNJP();
    }

    private void parseOwners(View view) {
        List<String> owners = ownersGson();
        for (String owner : owners) {
            Log.i("@codekul", "Owner is - "+owner);
        }
    }


    private String readFile() {

        StringBuilder builder = new StringBuilder();
        try {
            InputStream is = getAssets().open("hello.json");

            while(true) {
                int ch = is.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  builder.toString();
    }

    private List<String> ownersNJP() {
        List<String> owners = new ArrayList<>();
        try {
            JSONObject rootObj = new JSONObject(readFile());
            JSONArray arrOwner = rootObj.getJSONArray("owners");
            for(int i = 0; i < arrOwner.length(); i++){
                owners.add(arrOwner.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return owners;
    }

    private List<String> ownersGson() {
        Hello hello = gson.fromJson(readFile(), Hello.class);
        return hello.getOwners();
    }

    private void parentNJP() {
        try {
            JSONObject rootObj = new JSONObject(readFile());
            JSONObject parent = rootObj.getJSONObject("parent");
            Log.i("@codekul", "Model - "+parent.getString("model"));
            Log.i("@codekul", "Gravity - "+parent.getString("gravity"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
