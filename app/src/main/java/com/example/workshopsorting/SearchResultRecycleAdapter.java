package com.example.workshopsorting;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;


public class SearchResultRecycleAdapter extends RecyclerView.Adapter<NameViewHolder> {
    private List<Component> dataList = new ArrayList<>();
    private NameClick clickListener;

    public SearchResultRecycleAdapter(NameClick listener/*, List<Component> list*/){
        //setupData();
        this.clickListener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(nameViewHolder, position, payloads);
        nameViewHolder.setName(dataList.get(position).getName());
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_look, parent, false);
        return new NameViewHolder(cellView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int position){
        nameViewHolder.setName(dataList.get(position).getName());
        nameViewHolder.setNumber(dataList.get(position).getQuantity());
    }

    public void ClearAll(){
        dataList.clear();
        //notifyDataSetChanged();
    }

    public void setupData(List<Component> list){
        dataList=list;
    }

    public List<Component> getDataList(){
        return dataList;
    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }

    public void addData(List<Component> data){
        //Toast.makeText(this, "lol" + complete_list.size(), Toast.LENGTH_LONG).show();
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }
    public void addNewCell(Component Item, int position){
        if(dataList.size()>=position){
            dataList.add(position, Item);
            notifyItemInserted(position);
        }
    }


    public void removeCell(int position){
        if(dataList.size()>position){
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }
}
