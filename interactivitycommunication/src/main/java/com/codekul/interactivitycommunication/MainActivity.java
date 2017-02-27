package com.codekul.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAndroid).setOnClickListener(this::onMango);
        findViewById(R.id.btnApple).setOnClickListener(this::onApple);
    }

    private void onApple(View view) {

        Class<NextActivity> cls = NextActivity.class;

        Bundle bundle = new Bundle();
        bundle.putString("one", "iOS6");
        bundle.putString("two", "iOS5");

        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void onMango(View view) {

        Class<NextActivity> cls = NextActivity.class;

        Bundle bundle = new Bundle();
        bundle.putString("one", "Nougat");
        bundle.putString("two", "Marshmallow");

        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
