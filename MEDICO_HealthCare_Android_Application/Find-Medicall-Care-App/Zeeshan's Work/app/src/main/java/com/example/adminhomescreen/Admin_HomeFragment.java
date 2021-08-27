package com.example.adminhomescreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Admin_HomeFragment extends Fragment implements SelectingUploadingContentOptionDailog.DialogeListener {

    View v;
    private RecyclerView myrecyclerview;

    public Admin_HomeFragment() {
    }

    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_home_fragment,container,false);

        ImageButton addHopital = v.findViewById(R.id.addHopital);
        ImageButton addDoctor = v.findViewById(R.id.addDoctor);
        ImageButton addPharmacy = v.findViewById(R.id.addPharmacy);
        ImageButton addEvent = v.findViewById(R.id.addEventNews);
        addHopital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openHoptialRegForm = new Intent(getActivity(),Admin_GetHopitalDataForm.class);
                startActivity(openHoptialRegForm);

            }
        });
        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDoctorRegForm = new Intent(getActivity(),Admin_GetDoctorlDataForm.class);
                startActivity(openDoctorRegForm);

            }
        }); addPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPgramacryRegForm = new Intent(getActivity(),Admin_GetPharmacyDataForm.class);
                startActivity(openPgramacryRegForm);
            }
        }); addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Tested",Toast.LENGTH_LONG).show();
                Intent openPgramacryRegForm = new Intent(getActivity(),Admin_GetNewsFeedOptionForm.class);
                startActivity(openPgramacryRegForm);
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


         }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing Services ");
        }
    }




    @Override
    public void GetUploadingRepsonse(String response) {

    }
}
