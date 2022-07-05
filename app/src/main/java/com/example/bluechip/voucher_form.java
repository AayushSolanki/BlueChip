package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class voucher_form extends AppCompatActivity {


    Spinner spinnerDebtor, spinnerstock;
    ArrayList<String> debtorList = new ArrayList<>();
    ArrayList<String> stockList = new ArrayList<>();

    RequestQueue debtorrequestQueue;
    RequestQueue stockrequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_form);


        spinnerDebtor = findViewById(R.id.spinner);
        spinnerstock = findViewById(R.id.spinnerStock);

        getDebtorList();
        getStockList();


    }


    private void getStockList(){
        stockrequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urls.stock_list_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("stocks");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String stockName = jsonObject.optString("Pname");
                        stockList.add(stockName);
                        ArrayAdapter<String> stockAdapter= new ArrayAdapter<>(voucher_form.this,
                                android.R.layout.simple_spinner_item, stockList);
                        stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerstock.setAdapter(stockAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stockrequestQueue.add(jsonObjectRequest);
    }


    private  void getDebtorList(){
        debtorrequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urls.debtor_list_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("debtors");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String debtorName = jsonObject.optString("Name");
                        debtorList.add(debtorName);
                        ArrayAdapter<String> debtorAdapter= new ArrayAdapter<>(voucher_form.this,
                                android.R.layout.simple_spinner_item, debtorList);
                        debtorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerDebtor.setAdapter(debtorAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        debtorrequestQueue.add(jsonObjectRequest);

    }

}