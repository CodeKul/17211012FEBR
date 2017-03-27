package com.codekul.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MyService extends Service {


    private class Impl extends IComman.Stub {

        @Override
        public String getName(int id) throws RemoteException {
            return id == 1 ? "MON" : "SUN";
        }
    }

    private Impl impl = new Impl();


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Bind", Toast.LENGTH_SHORT).show();
        return impl;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "UnBind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
