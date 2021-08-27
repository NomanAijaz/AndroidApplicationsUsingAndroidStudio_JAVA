package com.example.adminhomescreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_GetHopitalDataForm extends AppCompatActivity implements SelectingCatagoriesOfSpecialityDailog.DialogeListener,CreatingNewSelectingCatagoriesOfSpecialityDailog.DialogeListener,SelectingCityListDailog.DialogeListener{
    
    LinearLayout catagoriesHorizontalList;
    CheckBox[] CatagoryChoicecheckBoxes;
    static int indexCheckBoxexList;
    View  view;
    TextView hopitalName,hopitalContactNumber,hopitalEmail,hopitalPhysicalAddress,hopitalDescription;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__get_hopital_data_form);

        catagoriesHorizontalList = findViewById(R.id.horizanotal_list);
        hopitalName = findViewById(R.id.txt_HopitalName);
        hopitalContactNumber = findViewById(R.id.txt_HospitalNumber);
        hopitalEmail = findViewById(R.id.txt_HopitalEmail);
        hopitalPhysicalAddress = findViewById(R.id.txt_HopitalDesc);

        indexCheckBoxexList=0;
        view = getWindow().getDecorView().findViewById(android.R.id.content);

        getSupportActionBar().hide();
    }

    public  void StartRegisterProcess(View view)
    {
        Toast.makeText(getApplicationContext(),"Not in working yet",Toast.LENGTH_LONG);
        //TODO: Connect to fire base and register hopital.
        //Here got selected choices we have array of check boxex.
        //Here have access to all fields of data like name email numer etc.
        DataBaseConnector.RegisterHopital("","","","","","");
    }

    public  void createCatagorycall(View view)
    {
        CreatingNewSelectingCatagoriesOfSpecialityDailog dailog = new CreatingNewSelectingCatagoriesOfSpecialityDailog();
        dailog.showNow(getSupportFragmentManager(),"Number Of quantities");
    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addCatCall(View view)
    {
        SelectingCatagoriesOfSpecialityDailog Dialog = new SelectingCatagoriesOfSpecialityDailog();
        Dialog.showNow(getSupportFragmentManager(),"Number Of quantities");
    }

    public void SelectCityCall(View view)
    {
            //TODO: Here  we have to open the map activity will get the required data back.
            SelectingCityListDailog dailog = new SelectingCityListDailog();
            dailog.showNow(getSupportFragmentManager(),"Number Of quantities");
            Toast.makeText(getApplicationContext(),"Not in working yet",Toast.LENGTH_LONG);
    }



    @Override
    public void GetValues(CheckBox[] checkBoxes) {
        Log.e("Movement ","getValues start");

        if(checkBoxes==null)return;

        CatagoryChoicecheckBoxes = new CheckBox[checkBoxes.length];
        indexCheckBoxexList=0;
        for (CheckBox checkBoxFetchd:checkBoxes) {

            if(checkBoxFetchd.isChecked())
            {

                Space space = new Space(getApplicationContext());
                space.setScaleX(20);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);

                CheckBox checkBox = new CheckBox(getApplicationContext());

                checkBox.setLayoutParams(params);
                checkBox.setGravity(Gravity.START);
                checkBox.setTextColor(Color.YELLOW);
                checkBox.setText(checkBoxFetchd.getText());
                checkBox.setChecked(true);


                if(checkBox.getParent() != null) {
                    Log.i("Check box : ",checkBox.getWidth()+"");
                    ((ViewGroup)checkBox.getParent()).removeView(checkBox); // <- fix
                }
                if(space.getParent() != null) {
                    ((ViewGroup)space.getParent()).removeView(space); // <- fix
                }
                Log.i("Check box : ",checkBox.getWidth()+"");

                CatagoryChoicecheckBoxes[indexCheckBoxexList++]=checkBox;
                catagoriesHorizontalList.addView(checkBox);
                catagoriesHorizontalList.addView(space);

            }
        }
    }


    @Override
    public void getNewCatagory(String getNewCatagory)
    {
            //TODO:here we will get the newly created city.

    }

    @Override
    public void getCity(RadioButton[] radioButtons)
    {
        //TODO:here we will get city

    }
}