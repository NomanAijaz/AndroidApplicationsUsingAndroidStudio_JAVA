package com.example.adminhomescreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Admin_RegisteredDoctorsFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    ArrayList<ServiceAvailbleForCustomer> serviceAvailbleForCustomers;
    private List<ListItemInfoContainer_RegesteredDoctors> lstFav;

    public Admin_RegisteredDoctorsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_reg_pharmacies_fragment,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.doctor_recyclerview);

        RegesteredDoctors_RecyclerViewAdapeter recyclerAdapter = new RegesteredDoctors_RecyclerViewAdapeter(getContext(),lstFav,serviceAvailbleForCustomers);
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
        lstFav.add(new ListItemInfoContainer_RegesteredDoctors("Dr. Zeeshan","The Aga Khan Hospitals are a network of international hospitals based in Dar es Salaam, Mumbai, Kisumu, Mombasa, Nairobi and Pakistan.",R.drawable.doctor));
        lstFav.add(new ListItemInfoContainer_RegesteredDoctors("Dr. Nadir","The Liaquat National Hospital (LNH), is located at Stadium Road, Karachi, Sindh, Pakistan. The hospital was established on October 16, 1958.",R.drawable.doctor));
        lstFav.add(new ListItemInfoContainer_RegesteredDoctors("Dr. Noman","Islamabad International Hospital & Research Center, FECHS E 11/3 E-11, Islamabad, Islamabad Capital Territory, Sector E, Islamabad",R.drawable.doctor));
        lstFav.add(new ListItemInfoContainer_RegesteredDoctors("Dr. Rizwan","Islamabad International Hospital & Research Center, FIslamabad International Hospital & Research Center, F",R.drawable.doctor));

    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing frag 3");

        }
    }

}
