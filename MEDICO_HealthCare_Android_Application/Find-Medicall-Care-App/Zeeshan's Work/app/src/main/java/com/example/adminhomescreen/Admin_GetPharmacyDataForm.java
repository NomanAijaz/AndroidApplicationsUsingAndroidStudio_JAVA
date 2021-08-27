package com.example.adminhomescreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_GetPharmacyDataForm extends AppCompatActivity implements SelectingTimingsDailog.DialogeListener,SelectingHopitalListDailog.DialogeListener,SelectingCityListDailog.DialogeListener{
    
    RadioButton radioButton ;
    View  view;
    TextView pharmacistName, pharmacistContactNumber, pharmacistEmail, pharmacistPhysicalAddress,pharmacistPhysicalDesc;
    ImageView profileImage;
    static final int  REQUEST_PRMISION=0;
    static final int  REQUEST_PRMISION_LOAD_IMG=1;

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__get_pharmacy_data_form);

        pharmacistName = findViewById(R.id.txt_pharmacistName);
        pharmacistContactNumber = findViewById(R.id.txt_pharmacistEmail);
        pharmacistEmail = findViewById(R.id.txt_pharmacistEmail);
        pharmacistPhysicalAddress = findViewById(R.id.txt_pharmacistPhyAddress);
        pharmacistPhysicalDesc = findViewById(R.id.txt_pharmacistDesc);

        profileImage = findViewById(R.id.img_pharmisict);
        view = getWindow().getDecorView().findViewById(android.R.id.content);


        if((Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP) /*&& checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED*/)
        {
            Log.e("Requesting for permissions","Needed to request");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PRMISION);
        }



        getSupportActionBar().hide();
    }

    public  void StartRegisterProcess(View view)
    {
        Toast.makeText(getApplicationContext(),"Not in working yet",Toast.LENGTH_LONG);
        //TODO: Connect to fire base and register Pharmacy
        //Here got selected choices we have array of check boxex.
        //Here have access to all fields of data like name email numer etc.
        DataBaseConnector.RegisterPharmacy("","","","","",null,"" );
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void importProfileImage(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_PRMISION:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Permissions Granted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Could get get permissions",Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_PRMISION_LOAD_IMG:
                if (requestCode == RESULT_FIRST_USER) {
                    Toast.makeText(getApplicationContext(),"Loading image",Toast.LENGTH_LONG).show();
                    try {

                        Uri selectedimg = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedimg, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        profileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        //TODO: HEre your got the profile the image of pharmist
                        Log.e("Lodingg img", picturePath);
                    }catch (Exception e)
                    {

                    }
                }
        }
    }

    public  void selectCityCall(View view)
    {
        SelectingCityListDailog dailog = new SelectingCityListDailog();
        dailog.showNow(getSupportFragmentManager(),"Number Of quantities");
    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void selectHopital(View view)
    {
        SelectingHopitalListDailog dailog =  new SelectingHopitalListDailog();
        dailog.showNow(getSupportFragmentManager(),"Number Of quantities");
    }

    public void selectTimings(View  view)
    {
     SelectingTimingsDailog dailog = new SelectingTimingsDailog();
        dailog.showNow(getSupportFragmentManager(),"Number Of quantities");

    }
    @Override
    public void getCity(RadioButton[] radioButtons) {
        //TODO : Here you get the name of selected city.
    }

    @Override
    public void getHospital(RadioButton[] radioButtons) {

    }

    @Override
    public String getTimings(String timings) {

        return "";

    }
}