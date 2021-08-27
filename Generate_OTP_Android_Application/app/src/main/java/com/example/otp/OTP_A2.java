package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OTP_A2 extends AppCompatActivity {

    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__a2);
        txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText("Hello "+getIntent().getStringExtra("Name"));

    }
}