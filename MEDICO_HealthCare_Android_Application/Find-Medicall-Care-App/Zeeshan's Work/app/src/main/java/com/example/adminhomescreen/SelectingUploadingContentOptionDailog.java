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
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatDialogFragment;

public class SelectingUploadingContentOptionDailog extends AppCompatDialogFragment  {
    DialogeListener listener;
    RadioButton cbRadioPic,cbRadioVid;

    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater Inflater = getActivity().getLayoutInflater();
        View view = Inflater.inflate(R.layout.selecting_uploading_content_dailog,null);
        builder.setView(view).setTitle("Choose option").setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String response = "uploadPic";
                if(!cbRadioPic.isChecked())response="uploadVid";
                listener.GetUploadingRepsonse(response);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.GetUploadingRepsonse(null);
            }
        });

        cbRadioPic = view.findViewById(R.id.cbPic);
        cbRadioVid = view.findViewById(R.id.cbVideo);





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
                ex.printStackTrace();
        }
    }
    public  interface DialogeListener
    {
        void GetUploadingRepsonse(String response);
    }
}
