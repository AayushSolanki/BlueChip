package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bluechip.adapters.debtor_ledger_adapter;
import com.example.bluechip.adapters.voucher_ledger_adapter;
import com.example.bluechip.models.debtor_ledger_model;
import com.example.bluechip.models.voucher_ledger_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class debtor_ledger_view extends AppCompatActivity {

    voucher_ledger_adapter Voucher_ledger_adapter;
    RequestQueue voucherLoadrequestQueue;
    RecyclerView recyclerView;
    List<voucher_ledger_model> voucher_ledger_modelList = new ArrayList<>();
String debtor_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtor_ledger_view);



        recyclerView= findViewById(R.id.recyclerViewVoucherledger);

        TextView tv = findViewById(R.id.Debtor_Name);

        String debtor_id ;

        debtor_id = getIntent().getStringExtra("debtor_id");

        tv.setText(debtor_id);
debtor_name=debtor_id;

loadvouchers();
    }
    private void setAdapter(List<voucher_ledger_model> voucher_ledger_models){

        Voucher_ledger_adapter = new voucher_ledger_adapter(this, (ArrayList<voucher_ledger_model>) voucher_ledger_models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       recyclerView.setLayoutManager(linearLayoutManager);
       recyclerView.setAdapter(Voucher_ledger_adapter);

    }

    public void loadvouchers(){

        voucherLoadrequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.167.184/apis/get_voucher_list.php?party_ledger="+"'"+debtor_name+"'", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String s1 = response.toString();
                String s2 = "{\"status\":\"1\",\"message\":\"empty\"}";

                if (s1.compareTo(s2)==0) {
                    Log.e("errr",response.toString());
                    Toast.makeText(debtor_ledger_view.this, "No vouchers for the debtor", Toast.LENGTH_LONG).show();
                } else {
                    try {
                    JSONArray jsonArray = response.getJSONArray("vouchers");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String date = jsonObject.getString("Date");
                        String type = jsonObject.getString("type");
                        voucher_ledger_modelList.add(new voucher_ledger_model(date, type));
                    }
                    setAdapter(voucher_ledger_modelList);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(debtor_ledger_view.this,error.getMessage(),Toast.LENGTH_LONG).show();
Log.e("error",error.getMessage());

                    }
                });

        voucherLoadrequestQueue.add(jsonObjectRequest);
    }
}