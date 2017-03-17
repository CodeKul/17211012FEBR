package com.codekul.uithread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(v -> new MyTask().execute(0,100));

        handler = new Handler(Looper.getMainLooper());
    }

    private void simpleFor() {
        for (int i = 0; i < 100; i++) {
            ((TextView) findViewById(R.id.txtCntr)).setText("" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadedFor() {
        new Thread(this::simpleFor).start();
    }

    int i = 0;

    private void handler() {
        new Thread(() -> {
            for (i = 0; i < 100; i++) {
                handler.post(() -> ((TextView) findViewById(R.id.txtCntr)).setText("" + i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class MyTask extends AsyncTask<Integer/*Params*/, Integer /*Progress*/, Boolean /*Result*/>  {

       private ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Ui thread

            pd = ProgressDialog.show(MainActivity.this, "Counter", "Counting Beats");
        }

        @Override
        protected Boolean/*Result*/ doInBackground(Integer... params/*params*/) {
            // worker thread
            for(int i = params[0] ; i < params[1]; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(new Integer[]{i}/*progress*/);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean/*Result*/) {
            super.onPostExecute(aBoolean);
            // Ui thread

            pd.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values/*Progress*/) {
            super.onProgressUpdate(values);
            ((TextView) findViewById(R.id.txtCntr)).setText("" + values[0]);
            // Ui thread
        }
    }
}
