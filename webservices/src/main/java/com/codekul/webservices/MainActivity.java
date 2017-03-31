package com.codekul.webservices;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.codekul.webservices.domain.Joke;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnJoke).setOnClickListener(this::joke);
    }

    private void joke(View view) {
        pd = ProgressDialog.show(this, "Random", "fetching random joke");
        ((App) getApplication()).q().add(new StringRequest("http://api.icndb.com/jokes/random", this::onJoke, this::onError));
    }

    private void onError(VolleyError volleyError) {
        Log.i("@codekul", "Error" + volleyError);
        pd.dismiss();
        ;
    }

    private void onJoke(String s) {
        Joke joke = ((App) getApplication()).gson().fromJson(s, Joke.class);
        ((TextView) findViewById(R.id.textView)).setText(joke.getValue().getJoke());

        Log.i("@codekul", "Joke - " + s);

        postJoke(joke);
    }

    private void postJoke(Joke joke) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("joke", joke.getValue().getJoke());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((App) getApplication()).q()
                .add(new JsonObjectRequest("https://digital-shelter-153912.firebaseio.com/jokes.json",
                        obj,
                        this::onPostJoke,
                        this::onError));

        pd.dismiss();
    }

    private void onPostJoke(JSONObject jsonObject) {
        Log.i("@codekul", "Post Status - " + jsonObject.toString());
    }
}
