package com.example.adminhomescreen;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_NewFeed_UploadPicPostForm extends AppCompatActivity{
    

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__news_feed_upload_pic_post_form);
        getSupportActionBar().hide();
    }



}