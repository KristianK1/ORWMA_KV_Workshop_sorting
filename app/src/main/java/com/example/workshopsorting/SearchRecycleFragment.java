package com.example.workshopsorting;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ClientInfoStatus;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class SearchRecycleFragment extends Fragment implements NameClick{
    private EditText edit_search;
    private Button button_search;
    private handshake Handshake;
    private RecyclerView recyler;
    private SearchResultRecycleAdapter Adapter;

    private List<Component> complete_list=new ArrayList<>();

    public String get_edit_tekst(){
        return edit_search.getText().toString();
    }


    public SearchResultRecycleAdapter getAdapter(){
        return Adapter;
    }

    public static SearchRecycleFragment newInstance(handshake mHandshake) {
        SearchRecycleFragment fragment = new SearchRecycleFragment();
        fragment.Handshake=mHandshake;
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    public void setComplete_list(List<Component> complete_list) {
        this.complete_list = complete_list;
    }

    public void setupRecyclerData(List<Component> list){
        Adapter.addData(list);
    }

    public List<Component> getComplete_list(){
        return complete_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler(view);
        setupRecyclerData(complete_list);
        edit_search = view.findViewById(R.id.edit_search);
        button_search = view.findViewById(R.id.button_search);

    }

    public void setupRecycler(@NonNull View view){

        //Toast.makeText(getActivity(), "hello"+ Toast.LENGTH_SHORT).show();
        recyler = view.findViewById(R.id.rvLista);
        recyler.setLayoutManager(new LinearLayoutManager(getContext())); //ok
        Adapter= new SearchResultRecycleAdapter(this); //ok
        recyler.setAdapter(Adapter); //ok
        Log.d("yey", "yey");
    }


    public void Show_item(String ID){
        int i=0;
        Log.i("chose", "ID: "+ID);
        Log.i("chose", "size: "+complete_list.size());

        for(i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getID().contentEquals(ID)) break;
        }
        Log.i("chose", "i: "+i);
        //Component focus= complete_list.get(i);

        //Log.i("chose", "ID: "+focus.getID());

        Handshake.item(complete_list.get(i).getID(), 0);
    }


    public void onNameClick(int position){
        Toast.makeText(getContext(), "pozicija"+position, Toast.LENGTH_SHORT).show();
        Show_item(Adapter.getDataList().get(position).getID());
        for(int i=0;i<complete_list.size();i++){
            Log.i("debugg", "ID: "+complete_list.get(i).getID());
        }
    }
}
