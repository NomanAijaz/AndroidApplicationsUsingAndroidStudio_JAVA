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

public class Admin_GetNewsFeedOptionForm extends AppCompatActivity implements SelectingUploadingContentOptionDailog.DialogeListener{
    

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__get_newsandfeed_options_form);



        getSupportActionBar().hide();
    }
    public void uploadPicPost(View  view)
    {
        Intent intent = new Intent(Admin_GetNewsFeedOptionForm.this,Admin_NewFeed_UploadPicPostForm.class);
        startActivity(intent);

    }
    public void uploadViewPost(View  view)
    {
        Intent intent = new Intent(Admin_GetNewsFeedOptionForm.this,Admin_NewFeed_UploadVidoePostForm.class);
        startActivity(intent);

    }


    @Override
    public void GetUploadingRepsonse(String response) {

    }
}