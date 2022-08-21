package com.example.bluechip;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bluechip.adapters.debtor_ledger_adapter;
import com.example.bluechip.models.debtor_ledger_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class debtor_ledger extends AppCompatActivity {

    debtor_ledger_adapter Debtor_ledger_adapter;
    RequestQueue debtorledgerrequestQueue;
    RecyclerView debtorLedgerrecyclerView;
    List<debtor_ledger_model> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtor_ledger);
        initialize();
        loadDebtorlist();
    }


    private void setAdapter(List<debtor_ledger_model> debtor_ledger_models){

        Debtor_ledger_adapter = new debtor_ledger_adapter(this,debtor_ledger_models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        debtorLedgerrecyclerView.setLayoutManager(linearLayoutManager);
debtorLedgerrecyclerView.setAdapter(Debtor_ledger_adapter);

    }

    private  void initialize(){
        debtorLedgerrecyclerView = findViewById(R.id.recyclerView_debtor_ledger);

    }
    public void loadDebtorlist(){
        debtorledgerrequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urls.debtor_list_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("debtors");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                  String name = jsonObject.getString("Debtor_Name");
                 list.add(new debtor_ledger_model(name));
                    }
                    setAdapter(list);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(debtor_ledger.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        debtorledgerrequestQueue.add(jsonObjectRequest);


    }


}