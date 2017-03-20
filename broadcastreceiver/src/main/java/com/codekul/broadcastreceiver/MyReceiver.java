package com.codekul.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("@codekul", " Air plane mode changed");
        Toast.makeText(context, "Air plane mode changed", Toast.LENGTH_SHORT).show();
    }
}
