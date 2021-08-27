package com.example.adminhomescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SelectingTimingsDailog extends AppCompatDialogFragment  {
    DialogeListener listener;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater Inflater = getActivity().getLayoutInflater();
        View view = Inflater.inflate(R.layout.selecting_timings_dailog,null);
        builder.setView(view).setTitle("Select Timings").setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            listener.getTimings(null);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.getTimings(null);
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
        String getTimings(String timings);
    }
}
