package com.example.workshopsorting;



import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tv_with_Name;
    private TextView tv_with_quantity;
    private NameClick clickListener;


    public NameViewHolder(@NonNull View itemView, NameClick listener){
        super(itemView);
        this.clickListener=listener;
        tv_with_Name=itemView.findViewById(R.id.tvName);
        tv_with_quantity=itemView.findViewById(R.id.twBrojac);
        itemView.setOnClickListener(this);
    }

    public void setName(String name){
        tv_with_Name.setText(name);
    }

    public void setNumber(int number){
        tv_with_quantity.setText(""+number);
    }

    @Override
    public void onClick(View view){
        clickListener.onNameClick(getAdapterPosition());
    }
}