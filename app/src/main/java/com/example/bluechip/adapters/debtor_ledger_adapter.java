package com.example.bluechip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluechip.R;
import com.example.bluechip.models.debtor_ledger_model;

import java.util.List;

public class debtor_ledger_adapter extends  RecyclerView.Adapter<debtor_ledger_adapter.viewHolder> {
    Context context;
            List<debtor_ledger_model> debtor_ledger_models;

    public debtor_ledger_adapter(Context context, List<debtor_ledger_model> debtor_ledger_models) {
        this.context = context;
        this.debtor_ledger_models = debtor_ledger_models;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=  LayoutInflater.from(context).inflate(R.layout.debotrs_ledger_recyclerview_layout,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
holder.debtor_name.setText(debtor_ledger_models.get(position).getDebtorName());

    }

    @Override
    public int getItemCount() {
        return debtor_ledger_models.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
TextView debtor_name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            debtor_name = itemView.findViewById(R.id.debtorNameinLedger);

        }
    }
}
