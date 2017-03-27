package com.codekul.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekul.aidl.IComman;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IComman comman;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            comman = IComman.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            comman = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bind(View view) {

        Intent intent = new Intent("com.codekul.action.COMMAN");

        bindService(convertToExplicit(intent), conn, BIND_AUTO_CREATE);
    }

    public void unBind(View view) {
        unbindService(conn);
    }

    public void convert(View view) {

        try {
            Log.i("@codekul", "Day is ---- "+ comman.getName(1));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Intent convertToExplicit(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        Intent explicitIntent = new Intent(implicitIntent);

        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
