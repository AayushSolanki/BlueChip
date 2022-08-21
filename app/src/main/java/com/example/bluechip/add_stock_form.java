package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class add_stock_form extends AppCompatActivity {
EditText productName;
Button addProductButton;
ProgressDialog addProductprogressDialog;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_form);

        productName = findViewById(R.id.editTextProductName);
        addProductButton = findViewById(R.id.addProductButton);
        addProductprogressDialog = new ProgressDialog(this);


        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
    }
    private void addProduct(){
        String ProductName = productName.getText().toString().trim();
        if (ProductName.equals("")) {
            productName.setError("Name is required");
        }
        else{
            addProductprogressDialog.setMessage("Adding Product");
            addProductprogressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    urls.add_stock_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Product Added Succesfully", Toast.LENGTH_LONG).show();
                    addProductprogressDialog.dismiss();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            addProductprogressDialog.hide();
                        }
                    }
            ) {
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("Product_Name", ProductName);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}