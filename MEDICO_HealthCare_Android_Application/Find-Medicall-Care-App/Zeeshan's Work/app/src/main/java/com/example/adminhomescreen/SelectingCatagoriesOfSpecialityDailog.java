package com.example.adminhomescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatDialogFragment;

public class SelectingCatagoriesOfSpecialityDailog extends AppCompatDialogFragment  {
    LinearLayout listOfOptions;
    DialogeListener listener;
    static CheckBox[] checkBoxes;
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater Inflater = getActivity().getLayoutInflater();
        View view = Inflater.inflate(R.layout.selecting_catagories_of_speciality_dailog,null);
        builder.setView(view).setTitle("Select any category").setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            listener.GetValues(checkBoxes);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.GetValues(null);
            }
        });

        listOfOptions = view.findViewById(R.id.listOfOptions);
        loadDataIntoList(view);




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
    private  CheckBox[] loadDataIntoList(View view)
    {
        //TODO:Load the catagories from firebase and set inthe form of check box.


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        checkBoxes = new CheckBox[3];
        for(int i=0;i<3;i++)
       {

    checkBoxes[i] = new CheckBox(getContext());
    checkBoxes[i].setLayoutParams(params);
    checkBoxes[i].setHeight(50);
    checkBoxes[i].setWidth(20);
    checkBoxes[i].setText("Heat specialist."+i);
    checkBoxes[i].setTextSize(20);
    checkBoxes[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


    listOfOptions.addView(checkBoxes[i]);
    }
        return  checkBoxes;

    }
    public  interface DialogeListener
    {
        void GetValues(CheckBox[] checkBoxes);
    }
}
