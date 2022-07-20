package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class sales_voucher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_voucher);

        RequestQueue stockrequestQueue;
        FloatingActionButton openAlertDailog ;
        openAlertDailog = findViewById(R.id.openalertdailog);
        ArrayList<String> stockList = new ArrayList<>();
        stockrequestQueue = Volley.newRequestQueue(this);

        LayoutInflater layoutInflater = LayoutInflater.from(sales_voucher.this);

        View view = layoutInflater.inflate(R.layout.spinner_in_alertdailog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(sales_voucher.this);

        builder.setView(view);

        builder.setTitle("Select item");

        AlertDialog alertDialog = builder.create();

        Spinner spinner = view.findViewById(R.id.spinnerselectitem);

        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(sales_voucher.this,spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT) .show();
                alertDialog.dismiss();
            }
        });
        openAlertDailog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urls.stock_list_url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("stocks");
                                 for(int i=0; i<jsonArray.length();i++) {
                                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                                     String stockName = jsonObject.optString("Pname");
                                     stockList.add(stockName);

                                 }
                                ArrayAdapter<String> stockAdapter= new ArrayAdapter<>(sales_voucher.this,
                                    android.R.layout.simple_spinner_item, stockList);
                                stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(stockAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();}}
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });


                stockrequestQueue.add(jsonObjectRequest);

                alertDialog.show();


            }});



    }




}