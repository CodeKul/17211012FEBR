package com.codekul.activitystatemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TXT_VW = "txtVw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt("onCreate");

        if (savedInstanceState != null) {
            State state = (State) savedInstanceState.getSerializable(KEY_TXT_VW);
            if (state != null)
                ((TextView) findViewById(R.id.txtVw)).setText(state.txtVwData);
        }
    }

    public void okayClicked(View view) {
        ((TextView) findViewById(R.id.txtVw)).setText(new Date().toString());
    }

    @Override
    protected void onDestroy() {
        mt("onDestroy");
        super.onDestroy();
    }

    private void mt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        State state = new State();
        state.txtVwData = ((TextView) findViewById(R.id.txtVw)).getText().toString();

        outState.putSerializable(KEY_TXT_VW, state);
    }

    class State implements Serializable {
        public String txtVwData;
    }
}
