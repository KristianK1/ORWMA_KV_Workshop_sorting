package com.example.workshopsorting;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroupSearchFragment extends Fragment implements NameClick{
    private Spinner Group_spinner;
    private Button group_search;
    handshake Handshake;
    private RecyclerView recyler;
    private SearchResultRecycleAdapter Adapter;

    AdapterView spinner_group;
    ArrayAdapter<String> adapter_group;

    private List<Component> complete_list=new ArrayList<>();

    String[] groups = { "Pasivne komponente", "Aktivne komponente", "Mikrokontoleri", "Moduli", "Kablovi/vodiči" };

    public static GroupSearchFragment newInstance(List<Component> lista, handshake mHandshake) {
        GroupSearchFragment fragment = new GroupSearchFragment();
        fragment.Handshake=mHandshake;
        //fragment.setList(lista);

        List<String> groups=new ArrayList<>();
        if(lista.isEmpty()==false) {
            for (int i = 0; i < lista.size(); i++) {
                String temp = lista.get(i).getGroup();
                int ubaci = 1;
                for (int j = 0; j < groups.size(); j++) {
                    if (groups.get(j).contentEquals(temp)) {
                        ubaci = 0;
                    }
                }
                if (ubaci == 1) {
                    groups.add(temp);
                }
            }
            for (int i = 0; i < groups.size(); i++) {
                groups.set(i, groups.get(i).replace("_", " "));
            }
        }

        String[] groups_polje = groups.toArray(new String[0]);
        fragment.setGruops(groups_polje);
        return fragment;
    }

    public void setGruops(String[] groups){
        this.groups=groups;
    }

    public void setComplete_list(List<Component> complete_list) {
        this.complete_list = complete_list;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.group_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler(view);
        List<Component> listtt = new ArrayList<>();
        setupRecyclerData(listtt);
        Group_spinner = view.findViewById(R.id.spinner_search_group);
        group_search = view.findViewById(R.id.group_button_search);
        spinner_group = (Spinner) view.findViewById(R.id.spinner_search_group);

        adapter_group = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groups);

        adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_group.setAdapter(adapter_group);
    }

    public SearchResultRecycleAdapter getAdapter(){
        return Adapter;
    }
    public String getGroupTicked(){
        return spinner_group.getSelectedItem().toString();
    }

   /* public void setList(List<Component> list) {
        this.complete_list = list;
        setupRecyclerData(list);
    }*/

    public void setupRecyclerData(List<Component> list){
        Adapter.addData(list);
    }

    public List<Component> getComplete_list(){
        return complete_list;
    }

    public void setupRecycler(@NonNull View view){

        //Toast.makeText(getActivity(), "hello"+ Toast.LENGTH_SHORT).show();
        recyler = view.findViewById(R.id.rvGroupList);
        recyler.setLayoutManager(new LinearLayoutManager(getContext())); //ok
        Adapter= new SearchResultRecycleAdapter(this); //ok
        recyler.setAdapter(Adapter); //ok
    }


    public void Show_item(String ID){
        int i=0;
        Log.i("chose", "ID: "+ID);
        //Log.i("chose", "size: "+complete_list.size());

        for(i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getID().contentEquals(ID)) break;
        }
        Log.i("chose", "i: "+i);
        //Component focus= complete_list.get(i);

        //Log.i("chose", "ID: "+focus.getID());
        Handshake.item(complete_list.get(i).getID(), 2);
    }



    public void onNameClick(int position){
        Toast.makeText(getContext(), "pozicija"+position, Toast.LENGTH_SHORT).show();
        Show_item(Adapter.getDataList().get(position).getID());
        for(int i=0;i<complete_list.size();i++){
            Log.i("debugg", "ID: "+complete_list.get(i).getID());
        }
    }
}
