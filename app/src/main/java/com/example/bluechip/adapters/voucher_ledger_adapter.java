package com.example.bluechip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluechip.R;
import com.example.bluechip.models.voucher_ledger_model;

import java.util.ArrayList;

public class voucher_ledger_adapter extends RecyclerView.Adapter<voucher_ledger_adapter.ViewHolder> {
Context context;
ArrayList<voucher_ledger_model>  voucher_ledger_models;

    public voucher_ledger_adapter(Context context, ArrayList<voucher_ledger_model> voucher_ledger_models) {

   this.context=context;
   this.voucher_ledger_models=voucher_ledger_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.voucher_type_recyclerview,null);

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull voucher_ledger_adapter.ViewHolder holder, int position) {

        holder.date.setText(voucher_ledger_models.get(position).getDate());
        holder.vocuher_type.setText(voucher_ledger_models.get(position).getVoucher_type());


    }

    @Override
    public int getItemCount() {
        return voucher_ledger_models.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView date,vocuher_type;

        voucher_ledger_adapter voucher_ledger_adapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            vocuher_type= itemView.findViewById(R.id.voucherType);

        }
    }


}
