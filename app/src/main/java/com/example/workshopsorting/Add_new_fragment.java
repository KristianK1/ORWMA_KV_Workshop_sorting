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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Add_new_fragment extends Fragment {
    private Button add_new;
    private EditText name, group, location, link, quantity;

    AdapterView spinner_group,spinner_location;
    ArrayAdapter<String> adapter_group,adapter_location;

    List<Component> list= new ArrayList<>();

    String[] locations = { "Velika kutija", "Mala kutija", "Dupla kutija" };
    String[] groups = { "Pasivne komponente", "Aktivne komponente", "Mikrokontoleri", "Moduli", "Kablovi/vodiči" };

    public void setList(List<Component> list) {
        this.list = list;
    }
    public void setComplete_list(List<Component> complete_list) {
        this.list = complete_list;
    }//dupla funkcija, nema veze
    public void setGruops(String[] groups){
        this.groups=groups;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public static Add_new_fragment newInstance(List<Component> lista){
        Log.i("last", "ovdje sam");
        Add_new_fragment fragment = new Add_new_fragment();
        fragment.setList(lista);

        //UPDATE SPINNERS
        List<String> groups=new ArrayList<>();
        List<String> locations = new ArrayList<>();
        if(!lista.isEmpty()) {
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

            Log.i("last", "ovdje sam");



            for (int i = 0; i < lista.size(); i++) {
                String temp = lista.get(i).getLocation();
                int ubaci = 1;
                for (int j = 0; j < locations.size(); j++) {
                    if (locations.get(j).contentEquals(temp)) {
                        ubaci = 0;
                    }
                }
                if (ubaci == 1) {
                    locations.add(temp);
                }
            }
            for (int i = 0; i < locations.size(); i++) {
                locations.set(i, locations.get(i).replace("_", " "));
            }


        }


        Log.i("last", "ovdje sam");
        groups.add(groups.size(), "Nova grupa");
        locations.add(locations.size(), "Nova lokacija");

        String[] groups_polje = groups.toArray(new String[0]);
        fragment.setGruops(groups_polje);
        String[] locations_polje = locations.toArray(new String[0]);
        fragment.setLocations(locations_polje);
        fragment.refresh_spinners();

        Log.i("last", "ovdje sam");
        return fragment;
    }

    public String getLocationTicked(){
        return spinner_location.getSelectedItem().toString();
    }
    public String getGroupTicked(){
        return spinner_group.getSelectedItem().toString();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        name=view.findViewById(R.id.et_name);
        group=view.findViewById(R.id.et_group);
        location=view.findViewById(R.id.et_location);
        link=view.findViewById(R.id.et_link);
        quantity=view.findViewById(R.id.et_quantity);
        add_new=view.findViewById(R.id.bt_add_new);

        spinner_group = (Spinner) view.findViewById(R.id.spinner_group);
        spinner_location = (Spinner) view.findViewById(R.id.spinner_location);


        adapter_group = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groups);
        adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, locations);

        adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_group.notifyDataSetChanged();
        adapter_location.notifyDataSetChanged();

        spinner_group.setAdapter(adapter_group);
        spinner_location.setAdapter(adapter_location);


    }

    public void refresh_spinners(){
        //adapter_group = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groups);
        //adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, locations);

        //adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter_location.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //adapter_group.notifyDataSetChanged();
        //adapter_location.notifyDataSetChanged();

        //spinner_group.setAdapter(adapter_group);
        //spinner_location.setAdapter(adapter_location);
    }
}