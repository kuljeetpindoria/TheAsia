package com.example.theasia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private RecyclerView recyclerView;
    List<Country> countryList;
    private static String url = "https://restcountries.eu/rest/v2/region/asia";
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.countryName);
        imageView = findViewById(R.id.flag);
        recyclerView = findViewById(R.id.recyclerView);
        countryList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this,countryList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
        getData();
        recyclerViewAdapter.notifyDataSetChanged();
    }

        private void getData() {
            RequestQueue requestQueue  = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url ,null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Country country = new Country();
                                    country.setName(jsonObject.getString("name").toString());
                                    country.setCapital(jsonObject.getString("capital").toString());
                                    country.setRegion(jsonObject.getString("region").toString());
                                    country.setSubregion(jsonObject.getString("subregion").toString());
                                    country.setPopulation(jsonObject.getString("population").toString());
                                    country.setFlags(jsonObject.getString("flag"));
                                    countryList.add(country);
                                }

                                recyclerViewAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        Log.d("Tag", error.getMessage());
                }
            });
            requestQueue.add(jsonArrayRequest);


    }
}