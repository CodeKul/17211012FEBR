package com.codekul.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAndroid).setOnClickListener(this::onMango);
        findViewById(R.id.btnApple).setOnClickListener(this::onApple);
    }

    private void onApple(View view) {

        Class<NextActivity> cls = NextActivity.class;

        Bundle bundle = new Bundle();
        bundle.putString("one", "iOS6");
        bundle.putString("two", "iOS5");

        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);

        startActivityForResult(intent, 5698);
        //finish();
    }

    private void onMango(View view) {

        Class<NextActivity> cls = NextActivity.class;

        Bundle bundle = new Bundle();
        bundle.putString("one", "Nougat");
        bundle.putString("two", "Marshmallow");

        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);

        //startActivity(intent);

        startActivityForResult(intent, 5698);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult");

        if (requestCode == 5698) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {

                String res = bundle.getString("res");
                Log.i(TAG, "Result is "+res);

                ((TextView)findViewById(R.id.txtRes)).setText(res);
            }
        }
    }
}
