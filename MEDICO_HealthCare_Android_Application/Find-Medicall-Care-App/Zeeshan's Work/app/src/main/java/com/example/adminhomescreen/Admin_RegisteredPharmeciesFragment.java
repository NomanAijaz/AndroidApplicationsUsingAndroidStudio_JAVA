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

public class Admin_RegisteredPharmeciesFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    ArrayList<ServiceAvailbleForCustomer> serviceAvailbleForCustomers;
    private List<ListItemInfoContainer_RegesteredPharmacies> lstFav;



    public Admin_RegisteredPharmeciesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_reg_addeventnew_fragment,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.pharmacies_recyclerview);

        RegesteredPharamacies_RecyclerViewAdapeter recyclerAdapter = new RegesteredPharamacies_RecyclerViewAdapeter(getContext(),lstFav);
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
        lstFav.add(new ListItemInfoContainer_RegesteredPharmacies("Poppular Medical shop","The Aga Khan Hospitals are a network of international hospitals based in Dar es Salaam, Mumbai, Kisumu, Mombasa, Nairobi and Pakistan.",R.drawable.pharmacy));
        lstFav.add(new ListItemInfoContainer_RegesteredPharmacies("NAD Medical Store","The Liaquat National Hospital (LNH), is located at Stadium Road, Karachi, Sindh, Pakistan. The hospital was established on October 16, 1958.",R.drawable.pharmacy));
        lstFav.add(new ListItemInfoContainer_RegesteredPharmacies("MEdical Store","Islamabad International Hospital & Research Center, FECHS E 11/3 E-11, Islamabad, Islamabad Capital Territory, Sector E, Islamabad",R.drawable.pharmacy));
        lstFav.add(new ListItemInfoContainer_RegesteredPharmacies("Store","Islamabad International Hospital & Research Center, FIslamabad International Hospital & Research Center, F",R.drawable.pharmacy));


    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing frag 4");
        }
    }




    }





