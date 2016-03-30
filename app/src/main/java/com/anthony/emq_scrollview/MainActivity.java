package com.anthony.emq_scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.anthony.emq_scrollview.Objects.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadMoreTransactions();

    }

    private void loadMoreTransactions() {

        JsonArrayRequest request = new JsonArrayRequest(DataHandler.getInstance().getUrl(),
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject jo = response.getJSONObject(i);

                                int id, amount;
                                Date createdAt = null;
                                String senderName, senderNote, recipient, currency;

                                try {
                                    String dateStr = jo.getString("created");
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    createdAt = sdf.parse(dateStr);
                                } catch (ParseException e) {

                                }

                                id = jo.getInt("id");
                                senderName = jo.getJSONObject("source").getString("sender");
                                senderNote = jo.getJSONObject("source").getString("note");
                                recipient = jo.getJSONObject("destination").getString("recipient");
                                currency = jo.getJSONObject("destination").getString("currency");
                                amount = jo.getJSONObject("destination").getInt("amount");

                                DataHandler.getInstance().addTransaction(new Transaction(id, createdAt, senderName, senderNote, recipient, amount, currency));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //TODO update adapter

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(MainActivity.class.getName(), error.toString());
            }
        });

        Volley.newRequestQueue(MainActivity.this).add(request);

    }

}
