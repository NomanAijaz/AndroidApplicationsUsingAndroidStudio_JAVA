package com.example.adminhomescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SelectingCityListDailog extends AppCompatDialogFragment  {
    LinearLayout listOfOptions;
    DialogeListener listener;
    static RadioButton[] radioButtons;
    RadioGroup radioGroup;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater Inflater = getActivity().getLayoutInflater();
        View view = Inflater.inflate(R.layout.selecting_city_list_dailog,null);
        builder.setView(view).setTitle("Select City").setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            listener.getCity(radioButtons);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.getCity(null);
            }
        });

        listOfOptions = view.findViewById(R.id.listOfOptions);
        radioGroup = view.findViewById(R.id.grpOFrBtnsadio);

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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private  RadioButton[] loadDataIntoList(View view)
    {
        //TODO:Fire base: Here we need to load cities from firebase.

        String[] cities = DataBaseConnector.LoadCitiesNames();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        radioButtons = new RadioButton[cities.length];

        int i=0;
        for (String city:cities
             )
       {

    radioButtons[i] = new RadioButton(getContext());
    radioButtons[i].setLayoutParams(params);
    radioButtons[i].setHeight(50);
    radioButtons[i].setWidth(20);
    radioButtons[i].setText(city);
    radioButtons[i].setTextSize(20);
    radioButtons[i].setId(View.generateViewId());
    radioButtons[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


    radioGroup.addView(radioButtons[i]);
    i++;
    }
        return  radioButtons;

    }
    public  interface DialogeListener
    {
        void getCity(RadioButton[] radioButtons);
    }
}
