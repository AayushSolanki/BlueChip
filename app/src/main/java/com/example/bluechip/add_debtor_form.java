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

public class add_debtor_form extends AppCompatActivity {
    EditText debtor_name, opening_balance;
    Button button;
    ProgressDialog addDebotrprogressDialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor_form);

        debtor_name = findViewById(R.id.editTextDebtorName);

        opening_balance = findViewById(R.id.editTextOpeningBAlance);

        button = findViewById(R.id.addDebtorButton);

        addDebotrprogressDialog = new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDebtor();
            }
        });


    }

    private void addDebtor() {
        String Name = debtor_name.getText().toString().trim();
        String OpeningBalance = opening_balance.getText().toString().trim();

        if (Name.equals("")) {
            debtor_name.setError("Debtor name is required");
        }
        else if(OpeningBalance.equals("")){
           opening_balance.setError("Opening Balance cannot be null");

        }
        else {
addDebotrprogressDialog.setMessage("Adding debtor");
        addDebotrprogressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urls.add_debtor_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Debtor Added Succesfully", Toast.LENGTH_LONG).show();
                addDebotrprogressDialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(error!=null && error.getMessage()!=null){
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            addDebotrprogressDialog.hide();

                        }
                    }
                }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("Name", Name);
                params.put("Opening_Balance", OpeningBalance);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
}