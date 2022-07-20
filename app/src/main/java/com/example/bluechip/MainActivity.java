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

        Button addDebtorbutton, addvoucherButton, addStockButton , debtor_leadger;

        addDebtorbutton = findViewById(R.id.addDebtorButton);
 addvoucherButton = findViewById(R.id.createVoucherButton);
 addStockButton = findViewById(R.id.addStockButton);
 debtor_leadger = findViewById(R.id.debtors_ledger);

 debtor_leadger.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent = new Intent(v.getContext() , debtor_ledger.class);
         startActivity(intent);
     }
 });

 addvoucherButton.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent = new Intent(view.getContext() , sales_voucher.class);
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