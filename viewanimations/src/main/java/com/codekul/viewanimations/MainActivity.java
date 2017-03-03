package com.codekul.viewanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRotate).setOnClickListener(this::rotate);
        findViewById(R.id.btnZoom).setOnClickListener(this::zoom);
        findViewById(R.id.btnMixed).setOnClickListener(this::mixed);
    }

    private void mixed(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mixed);
        findViewById(R.id.imgCen).startAnimation(animation);
    }

    private void zoom(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        findViewById(R.id.imgCen).startAnimation(animation);
    }

    private void rotate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        findViewById(R.id.imgCen).startAnimation(animation);
    }
}
