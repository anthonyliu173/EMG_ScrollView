package com.anthony.emq_scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new TransactionAdapter(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);

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
                                    String dateStr = jo.getString(Transaction.CREATEDAT);
                                    createdAt = sdf.parse(dateStr);
                                } catch (ParseException e) {

                                }

                                id = jo.getInt(Transaction.ID);
                                senderName = jo.getJSONObject(Transaction.SOURCE).getString(Transaction.SENDER);
                                senderNote = jo.getJSONObject(Transaction.SOURCE).getString(Transaction.NOTE);
                                recipient = jo.getJSONObject(Transaction.DESTINATION).getString(Transaction.RECIPIENT);
                                currency = jo.getJSONObject(Transaction.DESTINATION).getString(Transaction.CURRENCY);
                                amount = jo.getJSONObject(Transaction.DESTINATION).getInt(Transaction.AMOUNT);

                                DataHandler.getInstance().addTransaction(new Transaction(id, createdAt, senderName, senderNote, recipient, amount, currency));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //TODO update adapter
                        adapter.notifyDataSetChanged();

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
