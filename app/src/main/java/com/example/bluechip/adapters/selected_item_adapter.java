package com.example.bluechip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluechip.R;
import com.example.bluechip.models.selected_item_model;
import com.example.bluechip.sales_voucher;

import java.util.ArrayList;

public class selected_item_adapter extends RecyclerView.Adapter<selected_item_adapter.ViewHolder> {
Context context;

ArrayList<selected_item_model> Selected_item_models;

    public selected_item_adapter(Context context, ArrayList<selected_item_model> selected_item_models) {

        this.context = context;
        this.Selected_item_models=selected_item_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.selected_item_recyclerview,  null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

holder.itemName.setText(Selected_item_models.get(position).getName());
holder.itemQuantity.setText(Selected_item_models.get(position).getQuantity());
holder.itemRate.setText(Selected_item_models.get(position).getRate());
holder.itemTotal.setText(Selected_item_models.get(position).getTotal());


    }

    @Override
    public int getItemCount() {

        return Selected_item_models.size() ;
    }

    public class ViewHolder    extends RecyclerView.ViewHolder{

TextView itemName,itemQuantity,itemRate,itemTotal;
ImageButton imageButton;


selected_item_adapter selectedItemAdapter;
        public ViewHolder( View itemView) {
            super(itemView);


itemName = itemView.findViewById(R.id.selectedItemName);
itemQuantity=itemView.findViewById(R.id.selectedItemQuantity);
itemRate=itemView.findViewById(R.id.selectedItemRate);
itemTotal=itemView.findViewById(R.id.selectedItemTotal);
imageButton = itemView.findViewById(R.id.imageButton2);

imageButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Selected_item_models.remove(getAdapterPosition());


        notifyItemRemoved(getAdapterPosition());


    }
});

        }
    }

}
