package com.example.adminhomescreen;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Admin_RegisteredHopitalsFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;

    ListView listView;
    static TextView topicName;
    static String subjectName;
    private List<ListItemInfoContainer_RegesteredHopitals> lstFav;
    ArrayList <ServiceAvailbleForCustomer> serviceAvailbleForCustomers;


    public Admin_RegisteredHopitalsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_reg_hopitals_fragment,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.hopital_recyclerview);

        RegesteredHopital_RecyclerViewAdapeter recyclerAdapter = new RegesteredHopital_RecyclerViewAdapeter(getContext(),lstFav,serviceAvailbleForCustomers);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);


        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstFav = new ArrayList<>();
        serviceAvailbleForCustomers = new ArrayList<>();

        //This will sent to add in list.
        lstFav.add(new ListItemInfoContainer_RegesteredHopitals("Aga khan hopital","The Aga Khan Hospitals are a network of international hospitals based in Dar es Salaam, Mumbai, Kisumu, Mombasa, Nairobi and Pakistan.",R.drawable.hosital));
        lstFav.add(new ListItemInfoContainer_RegesteredHopitals("The Liaquat National Hospital (LNH)","The Liaquat National Hospital (LNH), is located at Stadium Road, Karachi, Sindh, Pakistan. The hospital was established on October 16, 1958.",R.drawable.hosital));
        lstFav.add(new ListItemInfoContainer_RegesteredHopitals("Islamabad International Hospital & Research Center","Islamabad International Hospital & Research Center, FECHS E 11/3 E-11, Islamabad, Islamabad Capital Territory, Sector E, Islamabad",R.drawable.hosital));
        lstFav.add(new ListItemInfoContainer_RegesteredHopitals("(LNH)","Islamabad International Hospital & Research Center, FIslamabad International Hospital & Research Center, F",R.drawable.hosital));



    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing requests" );


        }
    }





}
