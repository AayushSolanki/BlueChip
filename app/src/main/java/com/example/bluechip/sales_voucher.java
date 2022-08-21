package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bluechip.adapters.selected_item_adapter;
import com.example.bluechip.models.selected_item_model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sales_voucher extends AppCompatActivity {

     FloatingActionButton openAlertDailog;
     RequestQueue stockrequestQueue;
    RecyclerView recyclerViewselectedItems;
   ArrayList<selected_item_model> selectedItemList;
    selected_item_adapter Selected_item_adapter;


    ProgressDialog submitVoucherProgresDailog;
    String name;
    String quantity;
    String rate;
    String total;
    String debtor_name;
    String bill_total;
    Button submitbtn ;
    int bt = 0;
Spinner debtorListSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_voucher);


        spinner();


        submitVoucherProgresDailog = new ProgressDialog(this);
        submitbtn = findViewById(R.id.vouchersubmitbtn);
        openAlertDailog = findViewById(R.id.openalertdailog);
        stockrequestQueue = Volley.newRequestQueue(this);

        openAlertDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> arr = new ArrayList<>();
                ArrayList<String> stockList = new ArrayList<>();
                LayoutInflater layoutInflater = LayoutInflater.from(sales_voucher.this);

                View view = layoutInflater.inflate(R.layout.spinner_in_alertdailog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(sales_voucher.this);
                EditText productQuantity = view.findViewById(R.id.Alertdialogquantity);

                EditText productRate = view.findViewById(R.id.alertdialogRate);

                builder.setView(view);

                builder.setTitle("Select item");

                Spinner spinner = view.findViewById(R.id.spinnerselectitem);

                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = spinner.getSelectedItem().toString();
                        quantity = productQuantity.getText().toString();
                        rate = productRate.getText().toString();
                        int q = Integer.parseInt(quantity);
                        int r = Integer.parseInt(rate);
                        total = String.valueOf(q * r);
                        selectedItemList.add(new selected_item_model(name, quantity, rate, total));

                        bt = Integer.parseInt(total)+bt;

                        bill_total=String.valueOf(bt);
                    }
                });

                AlertDialog alertDialog = builder.create();

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urls.stock_list_url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("stocks_ledger");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String stockName = jsonObject.optString("Product_Name");
                                stockList.add(stockName);

                            }
                            ArrayAdapter<String> stockAdapter = new ArrayAdapter<>(sales_voucher.this,
                                    android.R.layout.simple_spinner_item, stockList);
                            stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(stockAdapter);

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
                alertDialog.show();

            }


        });


        showList();
        saveData();
    }





    public void saveData(){

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debtor_name= debtorListSpinner.getSelectedItem().toString();

                submitVoucherProgresDailog.setMessage("Creating Voucher");
                submitVoucherProgresDailog.show();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, urls.create_voucher_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), "Voucher Created Successfully", Toast.LENGTH_LONG).show();
                        submitVoucherProgresDailog.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(error!=null && error.getMessage()!=null){
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            submitVoucherProgresDailog.hide();
                        }
                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("party_ledger", debtor_name);
                        params.put("form_array",selectedItemList.toString());
                        params.put("total",bill_total);
                        params.put("form_ledger","default");
                        params.put("whether_tax","default");
                        params.put("tax","default");

                        return params;

                    }

                };


                RequestQueue requestQueue = Volley.newRequestQueue(sales_voucher.this);
                requestQueue.add(stringRequest);


            }
        });

    }

    private  void spinner(){

        debtorListSpinner = findViewById(R.id.debtorListSpinner);


        ArrayList<String> debtorList = new ArrayList<>();

        RequestQueue debtorrequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urls.debtor_list_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("debtors");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String debtorName = jsonObject.optString("Debtor_Name");
                        debtorList.add(debtorName);
                        ArrayAdapter<String> debtorAdapter= new ArrayAdapter<>(sales_voucher.this,
                                android.R.layout.simple_spinner_item, debtorList);
                        debtorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        debtorListSpinner.setAdapter(debtorAdapter);

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


    private void showList(){
        recyclerViewselectedItems = findViewById(R.id.recyclerView_selectedItems);
        selectedItemList = new ArrayList<>();
        Selected_item_adapter = new selected_item_adapter(this,selectedItemList);

        recyclerViewselectedItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewselectedItems.setAdapter(Selected_item_adapter);



        Selected_item_adapter.notifyItemInserted(selectedItemList.size()-1);

    }

}