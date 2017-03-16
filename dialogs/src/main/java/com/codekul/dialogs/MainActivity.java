package com.codekul.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAlert).setOnClickListener(v -> showAppDialog(AppDialog.DIALOG_ALERT));
        findViewById(R.id.btnDate).setOnClickListener(v -> showAppDialog(AppDialog.DIALOG_DATE));
        findViewById(R.id.btnTime).setOnClickListener(v -> showAppDialog(AppDialog.DIALOG_TIME));
        findViewById(R.id.btnProgress).setOnClickListener(v -> showAppDialog(AppDialog.DIALOG_PROGRESS));
        findViewById(R.id.btnCustom).setOnClickListener(v -> showAppDialog(AppDialog.DIALOG_CUSTOM));
    }

    private void showAppDialog(String dialog) {
        AppDialog appDialog = new AppDialog();
        appDialog.show(getSupportFragmentManager(), dialog);
    }
}
