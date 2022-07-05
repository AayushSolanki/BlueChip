package com.example.bluechip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //projectr created

        Button addDebtorbutton, addvoucherButton, addStockButton;

        addDebtorbutton = findViewById(R.id.addDebtorButton);
 addvoucherButton = findViewById(R.id.createVoucherButton);
 addStockButton = findViewById(R.id.addStockButton);
 addvoucherButton.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent = new Intent(view.getContext() , voucher_form.class);
         startActivity(intent);
     }
 });

 addStockButton.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent = new Intent(view.getContext() , add_stock_form.class);
         startActivity(intent);
     }
 });

        addDebtorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , add_debtor_form.class);
            startActivity(intent);
            }
            }

        );
    }
}