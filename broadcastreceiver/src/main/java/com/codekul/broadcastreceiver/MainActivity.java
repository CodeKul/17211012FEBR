package com.codekul.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getBooleanExtra("state", false)) findViewById(R.id.imgPlane).setVisibility(View.VISIBLE);
            else findViewById(R.id.imgPlane).setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //while app is running
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction("com.codekul.broadcast.EVENT");
        registerReceiver(receiver, filter);
    }

    public void fire(View view) {
        Intent intent = new Intent("com.codekul.broadcast.EVENT");
        intent.putExtra("state", true);
        sendBroadcast(intent);
    }
}
