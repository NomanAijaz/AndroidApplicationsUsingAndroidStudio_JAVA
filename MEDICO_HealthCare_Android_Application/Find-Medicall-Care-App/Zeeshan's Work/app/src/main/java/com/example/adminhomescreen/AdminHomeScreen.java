package com.example.adminhomescreen;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class AdminHomeScreen extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new Admin_HomeFragment(),"Home");
        adapter.AddFragment(new Admin_RegisteredHopitalsFragment(),"Hopitals");
        adapter.AddFragment(new Admin_RegisteredDoctorsFragment(),"Doctors");
        adapter.AddFragment(new Admin_RegisteredPharmeciesFragment(),"Pharmacies");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

       // tabLayout.getTabAt(0).setIcon(R.drawable.service);
       // tabLayout.getTabAt(1).setIcon(R.drawable.request);
       // tabLayout.getTabAt(2).setIcon(R.drawable.star);
       // tabLayout.getTabAt(3).setIcon(R.drawable.complete);


     //   ActionBar actionBar = getSupportActionBar();
     //   assert actionBar != null;
     //   actionBar.setElevation(0);

    }
    public void check_About(View view)
    {
       // Intent about = new Intent(CustomerDashBorad_MainActivity.this,info_account.class);
       // startActivity(about);
    }
    public void check_History(View view)
    {
       // Intent about = new Intent(CustomerDashBorad_MainActivity.this,History_data.class);
       // startActivity(about);
    }
}