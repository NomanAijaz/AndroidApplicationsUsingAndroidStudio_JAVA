package com.example.adminhomescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CreatingNewSelectingCatagoriesOfSpecialityDailog extends AppCompatDialogFragment   {
    DialogeListener listener;
    TextView newCatName;
   static String creationgRespons;
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater Inflater = getActivity().getLayoutInflater();
        View view = Inflater.inflate(R.layout.creating_catagories_of_speciality_dailog,null);
        builder.setView(view).setTitle("Create New Catagory").setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.getNewCatagory(creationgRespons);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.getNewCatagory(creationgRespons);
            }
        });

        newCatName = view.findViewById(R.id.txt_NewCataName);
        Button createandupate = new Button(getContext());
        createandupate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : FireBase : Create New Catagory
                DataBaseConnector.CreatNewHopital(newCatName.getText().toString());
            }
        });


        return builder.create();
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context );
        try {
            listener = (DialogeListener) context;
        }catch (Exception ex)
        {

        }

    }

    public  interface DialogeListener
    {
        void getNewCatagory(String getNewCatagory);
    }
}
