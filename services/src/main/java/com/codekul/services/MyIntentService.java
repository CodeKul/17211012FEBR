package com.codekul.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i = 0; i < 10000; i++){
            // your heavy code
        }
    }
}
