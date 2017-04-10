package com.codekul.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager manager;
    private List<ScanResult> scanned;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            scanned = manager.getScanResults();

            for (ScanResult scanResult : scanned) {
                Log.i("@codekul", "SSID - "+scanResult.SSID);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void connected(View view) {
        List<WifiConfiguration> configured = manager.getConfiguredNetworks();
        for (WifiConfiguration wifiConfiguration : configured) {
            Log.i("@codekul", "SSID - "+wifiConfiguration.SSID);
            Log.i("@codekul", "BSSID - "+wifiConfiguration.BSSID);
        }
    }

    public void connect(View view) {

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", "YourCodekul");
        wifiConfig.preSharedKey = String.format("\"%s\"", "#code.KUL123#");

        int netId = manager.addNetwork(wifiConfig);
        manager.disconnect();
        manager.enableNetwork(netId, true);
        manager.reconnect();
    }

    public void scan(View view) {
        manager.startScan();
    }
}
