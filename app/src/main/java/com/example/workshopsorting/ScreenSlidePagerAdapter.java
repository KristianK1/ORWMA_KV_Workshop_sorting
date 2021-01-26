package com.example.workshopsorting;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 4;
    handshake Handshake;
    List<Component> complete_list=new ArrayList<>();

    SearchRecycleFragment page0;
    LocationSearchFragment page1;
    GroupSearchFragment page2;
    Add_new_fragment page3;

    public SearchRecycleFragment getPage0(){
        return page0;
    }

    public LocationSearchFragment getPage1() {
        return page1;
    }

    public GroupSearchFragment getPage2() {
        return page2;
    }

    public Add_new_fragment getPage3() {
        return page3;
    }

    public ScreenSlidePagerAdapter(FragmentManager fm, List<Component> list, handshake mHandshake) {
        super(fm);
        complete_list=list;
        Handshake=mHandshake;
        Log.i("mytag", "Duzina podataka u screen slideru: "+list.size());
    }

    @Override public Fragment getItem(int position) {

        switch (position) {
            case 0: {
                page0=SearchRecycleFragment.newInstance(Handshake);
                return page0;
            }
            case 1: {
                page1=LocationSearchFragment.newInstance(complete_list, Handshake);
                return page1;
            }
            case 2: {
                page2=GroupSearchFragment.newInstance(complete_list, Handshake);
                return page2;
            }
            default: {
                page3=Add_new_fragment.newInstance(complete_list);
                page3.setList(complete_list); //vjv ne treba
                return page3;
            }
        }
    }


    @Nullable
    @Override public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return String.format(Locale.getDefault(), "%s", "Search");
            case 1:
                return String.format(Locale.getDefault(), "%s", "Location");
            case 2:
                return String.format(Locale.getDefault(), "%s", "Group");
            default:
                return String.format(Locale.getDefault(), "%s", "Add");
        }
    }


    @Override public int getCount() {
        return NUM_PAGES;
    }

    public void funkcija(){

    }
}