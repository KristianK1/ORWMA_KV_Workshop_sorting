package com.example.workshopsorting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements handshake{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ScreenSlidePagerAdapter pagerAdapter;
    String Single_ID;
    Component Single=new Component();
    //u normali "X", u single item viewu sadrži ID single-a

    List<Component> complete_list=new ArrayList<>();

    public void item(String ID, int screen){
        //definirat sve text-viewove
        //funkcije za tipke imat u mainactivityu
        setContentView(R.layout.fragment_single_view);
        int i=0;
        for(i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getID().contentEquals(ID)) break;
        }
        Single.setID(ID);
        Single.setName(complete_list.get(i).getName());
        Single.setGroup(complete_list.get(i).getGroup());
        Single.setLink(complete_list.get(i).getLink());
        Single.setLocation(complete_list.get(i).getLocation());
        Single.setQuantity(""+complete_list.get(i).getQuantity());

        TextView single_name=findViewById(R.id.single_name);
        TextView single_group=findViewById(R.id.single_group);
        TextView single_link=findViewById(R.id.single_link);
        TextView single_quantity=findViewById(R.id.single_quantity);
        TextView single_location=findViewById(R.id.single_location);

        single_name.setText(complete_list.get(i).getName());
        single_group.setText(complete_list.get(i).getGroup());
        single_link.setText(complete_list.get(i).getLink());
        single_quantity.setText(""+complete_list.get(i).getQuantity());
        single_location.setText(complete_list.get(i).getLocation());
        Single_ID=ID;
    }


    public void goBackfromSingle(View v){
        setContentView(R.layout.activity_main);
        initViews();
        setUpPager();
    }

    public void deleteSingle(View v){
        AsyncTask<String, String, String> execute = new CallAPI().execute("https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/remove/"+Single_ID);
        String stranica="";
        try{
            stranica = execute.get();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        setContentView(R.layout.activity_main);
        initViews();
        setUpPager();
    }

    public void Single_plus(View v){
        Single.incrementQuantity();
        TextView single_quantity=findViewById(R.id.single_quantity);
        single_quantity.setText(""+Single.getQuantity());
    }

    public void Single_minus(View v){
        Single.decrementQuantity();;
        TextView single_quantity=findViewById(R.id.single_quantity);
        single_quantity.setText(""+Single.getQuantity());
    }

    public void SingleSave(View v){
        AsyncTask<String, String, String> execute = new CallAPI().execute("https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/remove/"+Single_ID);
        String stranica="";
        try{
            stranica = execute.get();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        String link_web="https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/put/?";
        link_web=link_web+"name=";
        link_web=link_web+Single.getName();

        link_web=link_web+"&location=";
        link_web=link_web+Single.getLocation();

        link_web=link_web+"&link=";
        link_web=link_web+Single.getLink();

        link_web=link_web+"&group=";
        link_web=link_web+Single.getGroup();

        link_web=link_web+"&quantity=";
        link_web=link_web+Single.getQuantity();

        add_new_component_web(link_web);

        setContentView(R.layout.activity_main);
        initViews();
        setUpPager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupAppData();
        setUpPager();
    }


    public void UpdateSpinners() {
        List<String> groups=new ArrayList<>();
        List<String> locations=new ArrayList<>();
        if (complete_list.isEmpty() == false) {
            for (int i = 0; i < complete_list.size(); i++) {
                String temp = complete_list.get(i).getGroup();
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


            for (int i = 0; i < complete_list.size(); i++) {
                String temp = complete_list.get(i).getLocation();
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
            String[] groups_polje = groups.toArray(new String[0]);
            //fragment.setGruops(groups_polje);
            String[] locations_polje = locations.toArray(new String[0]);
            //fragment.setLocations(locations_polje);

            if(pagerAdapter.getPage1()!=null){
                pagerAdapter.getPage1().setLocations(locations_polje);
            }
            if(pagerAdapter.getPage2()!=null){
                pagerAdapter.getPage2().setGruops(groups_polje);
            }
            if(pagerAdapter.getPage3()!=null){
                pagerAdapter.getPage3().setLocations(locations_polje);
                pagerAdapter.getPage3().setGruops(groups_polje);
            }
        }
    }


    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tab);
    }

    private void setUpPager() {
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), complete_list, this);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }



    public void setupAppData() {
        complete_list = Load_state();
    }

    public List<Component> Load_state(){
        List<Component> list = new ArrayList<>();

        AsyncTask<String, String, String> execute = new CallAPI().execute("https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/get/");
        String stranica="";
        try{
            stranica = execute.get();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        //Log.i("mytag", stranica);
        list=deserijalizacija(stranica);
        return list;
    }


    public void search_button_pressed(View view){
        complete_list=check_net();
        pagerAdapter.getPage0().getAdapter().addData(complete_list);
        if(pagerAdapter.getPage0()!=null) pagerAdapter.getPage0().setComplete_list(complete_list);
        if(pagerAdapter.getPage1()!=null) pagerAdapter.getPage1().setComplete_list(complete_list);
        if(pagerAdapter.getPage2()!=null) pagerAdapter.getPage2().setComplete_list(complete_list);
        if(pagerAdapter.getPage3()!=null) pagerAdapter.getPage3().setComplete_list(complete_list);

        String text=pagerAdapter.getPage0().get_edit_tekst();

        pagerAdapter.getPage0().getAdapter().ClearAll();

        List<Component> found = new ArrayList<>();
        for(int i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getName().contains(text)){
                found.add(complete_list.get(i));
            }
        }
        pagerAdapter.getPage0().getAdapter().addData(found);
        //pagerAdapter.getPage3().setList(complete_list);

    }


    public void add_new_item_online(View v){
        EditText et_name = findViewById(R.id.et_name);
        EditText et_group = findViewById(R.id.et_group);
        EditText et_location = findViewById(R.id.et_location);
        EditText et_link = findViewById(R.id.et_link);
        EditText et_quantity = findViewById(R.id.et_quantity);
        Spinner spinner_group = (Spinner) findViewById(R.id.spinner_group);
        Spinner spinner_location = (Spinner) findViewById(R.id.spinner_location);
        //Toast.makeText(getApplicationContext(), "klikkkkk", Toast.LENGTH_SHORT).show();

        String name=et_name.getText().toString();
        String location=et_location.getText().toString();
        String link=et_link.getText().toString();
        String quantity=et_quantity.getText().toString();
        String group=et_group.getText().toString();


        if(name.contentEquals("")) return;
        if(link.contentEquals("")) return;
        if(quantity.contentEquals("")) return;

        String link_web="https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/put/?";
        link_web=link_web+"name=";
        link_web=link_web+name;

        link_web=link_web+"&location=";
        if(pagerAdapter.getPage3().getLocationTicked().contentEquals("Nova lokacija")==false){
            location=pagerAdapter.getPage3().getLocationTicked();
        }
        else{
            if(location.contentEquals("")) return;
        }
        link_web=link_web+location;

        link_web=link_web+"&link=";
        link_web=link_web+link;

        link_web=link_web+"&group=";
        if(pagerAdapter.getPage3().getGroupTicked().contentEquals("Nova grupa")==false){
            group=pagerAdapter.getPage3().getGroupTicked();
        }
        else{
            if(group.contentEquals("")) return;
        }
        link_web=link_web+group;

        link_web=link_web+"&quantity=";
        link_web=link_web+quantity;

        add_new_component_web(link_web);
    }

    public void group_search_pressed(View v){
        complete_list=check_net();
        if(pagerAdapter.getPage0()!=null) pagerAdapter.getPage0().setComplete_list(complete_list);
        if(pagerAdapter.getPage1()!=null) pagerAdapter.getPage1().setComplete_list(complete_list);
        if(pagerAdapter.getPage2()!=null) pagerAdapter.getPage2().setComplete_list(complete_list);
        if(pagerAdapter.getPage3()!=null) pagerAdapter.getPage3().setComplete_list(complete_list);
        String trazena_grupa = pagerAdapter.getPage2().getGroupTicked();
        List<Component> trazena_lista = new ArrayList<>();
        for(int i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getGroup().contentEquals(trazena_grupa)){
                trazena_lista.add(complete_list.get(i));
            }
        }
        pagerAdapter.getPage2().getAdapter().addData(trazena_lista);
    }

    public void location_search_pressed(View v){
        complete_list=check_net();
        if(pagerAdapter.getPage0()!=null) pagerAdapter.getPage0().setComplete_list(complete_list);
        if(pagerAdapter.getPage1()!=null) pagerAdapter.getPage1().setComplete_list(complete_list);
        if(pagerAdapter.getPage2()!=null) pagerAdapter.getPage2().setComplete_list(complete_list);
        if(pagerAdapter.getPage3()!=null) pagerAdapter.getPage3().setComplete_list(complete_list);
        String trazena_lokacija = pagerAdapter.getPage1().getLocationTicked();
        List<Component> trazena_lista = new ArrayList<>();
        for(int i=0;i<complete_list.size();i++){
            if(complete_list.get(i).getLocation().contentEquals(trazena_lokacija)){
                trazena_lista.add(complete_list.get(i));
            }
        }
        pagerAdapter.getPage1().getAdapter().addData(trazena_lista);
    }

    public void add_new_component_web(String link){
        /*SharedPreferences mPrefs = getSharedPreferences("label", 0);
        int number_of_components = mPrefs.getInt("tag", 0);*/
        AsyncTask<String, String, String> execute = new CallAPI().execute(link);
        String stranica="";
        try{
            stranica = execute.get();
            complete_list=check_net();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        complete_list=check_net();
        if(pagerAdapter.getPage0()!=null) pagerAdapter.getPage0().setComplete_list(complete_list);
        if(pagerAdapter.getPage1()!=null) pagerAdapter.getPage1().setComplete_list(complete_list);
        if(pagerAdapter.getPage2()!=null) pagerAdapter.getPage2().setComplete_list(complete_list);
        if(pagerAdapter.getPage3()!=null) pagerAdapter.getPage3().setComplete_list(complete_list);
    }


    public List<Component> check_net(){
        List<Component> list = new ArrayList<>();

        /*SharedPreferences mPrefs = getSharedPreferences("label", 0);
        int number_of_components = mPrefs.getInt("tag", 0);*/
        AsyncTask<String, String, String> execute = new CallAPI().execute("https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/get/");
        String stranica="";
        try{
            stranica = execute.get();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        //Log.i("mytag", stranica);
        list=deserijalizacija(stranica);
        return list;
    }



    public void klik(View v){
        //Toast.makeText(getApplicationContext(), "You mashed the button, dude.", Toast.LENGTH_SHORT).show();
        AsyncTask<String, String, String> execute = new CallAPI().execute("https://iotstoragebox.ivica-matic.com/59d21f9f-e50c-4f73-850d-a34d5ecbaddd/get/");
        String stranica="";
        try{
            stranica = execute.get();
        }
        catch (Exception e){
            //sta da mu ja radim
        }
        Log.i("mytag", stranica);
        deserijalizacija(stranica);
    }


    public List<Component> deserijalizacija(String JSON){
        List<Component> lista= new ArrayList<>();
        if(JSON.contains("\"")==false){
            Toast.makeText(getApplicationContext(), "PRAZNO", Toast.LENGTH_SHORT).show();
            return lista; //pokriva slucaj ako je prazno
        }
        int place = 0;
        while(true){ //pocinje dio koji ce se kasnije ponavljati

            while (JSON.charAt(place) != '\"') place++;

            int place_2 = place + 1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String ID="";
            for(int i=place+1;i<place_2;i++){
                ID=ID+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+ID.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();

            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;

            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String name="";
            for(int i=place;i<place_2;i++){
                name=name+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+name.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();


            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;

            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String location="";
            for(int i=place;i<place_2;i++){
                location=location+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+location.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), location, Toast.LENGTH_SHORT).show();

            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;

            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String link="";
            for(int i=place;i<place_2;i++){
                link=link+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+link.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), link, Toast.LENGTH_SHORT).show();



            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;




            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String group="";
            for(int i=place;i<place_2;i++){
                group=group+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+group.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), group, Toast.LENGTH_SHORT).show();

            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;

            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String quantity="";
            for(int i=place;i<place_2;i++){
                quantity=quantity+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+quantity.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), quantity, Toast.LENGTH_SHORT).show();


            place=place_2+1;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;
            while (JSON.charAt(place) != '\"') place++;
            place++;

            place_2=place+1;
            while (JSON.charAt(place_2) != '\"') place_2++;
            String timestamp="";
            for(int i=place;i<place_2;i++){
                timestamp=timestamp+JSON.charAt(i);
            }
            //Toast.makeText(getApplicationContext(), "Duzina: "+timestamp.length(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), timestamp, Toast.LENGTH_SHORT).show();


            lista.add(new Component(ID, name, location, link, group, quantity));
            place=place_2+1;

            while (JSON.charAt(place) != '}') place++;
            if(JSON.charAt(place+1)!=','){
                //Toast.makeText(getApplicationContext(), "gotovo", Toast.LENGTH_SHORT).show();
                break;
            }
            else{
                place++;
            }
        }
        return lista;
    }
}