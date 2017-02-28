package com.codekul.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NextActivity extends AppCompatActivity {

    public static final String TAG = NextActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);

        findViewById(R.id.btnBack).setOnClickListener(this::onBack);

        Intent intentResponsible = getIntent();

        Bundle bundle = intentResponsible.getExtras();
        if(bundle != null) {
            String one = bundle.getString("one");
            String two = bundle.getString("two");

            Log.i(TAG, "One - "+one + " Two - "+two);

            ((RadioButton)findViewById(R.id.radOne)).setText(one);
            ((RadioButton)findViewById(R.id.radTwo)).setText(two);
        }
    }

    private void onBack(View view) {

        int checked = ((RadioGroup)findViewById(R.id.radGrp)).getCheckedRadioButtonId();

        Bundle bundle = new Bundle();
        bundle.putString("res", ((RadioButton)findViewById(checked)).getText().toString());

        Intent intentBack = new Intent();
        intentBack.putExtras(bundle);
        setResult(RESULT_OK, intentBack);
        finish();
    }
}
