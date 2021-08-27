package com.example.signupandsignin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;

    private Button btndoctor,btnpatient,btnpharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-------------------this is code just to hide the action or title bar from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------end------------------------------------

        setContentView(R.layout.activity_main);

        btnSignIn = (Button) findViewById(R.id.signIn);
        btnSignUp = (Button) findViewById(R.id.signUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:: user SignUp Activity
                Intent signInActivity = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signInActivity);

            }
        });

    }
    public void openDialog(){

        Dialog dialog = new Dialog(MainActivity.this,R.style.customDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.pop_layout,null);


        btndoctor = view.findViewById(R.id.doctor);
        btnpatient = view.findViewById(R.id.patient);
        btnpharmacy=view.findViewById(R.id.pharmacy);



        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: call the login for Doctor from Popup
                Intent signInActivity = new Intent(MainActivity.this,SignInActivity.class);
                signInActivity.putExtra("user","doctor");
                startActivity(signInActivity);
            }
        });

        btnpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: call the login for patient from popUp
                Intent signInActivity = new Intent(MainActivity.this,SignInActivity.class);
                signInActivity.putExtra("user","patient");
                startActivity(signInActivity);
            }
        });

        btnpharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: call the Login for Pharmacy from popup
                Intent signInActivity = new Intent(MainActivity.this,SignInActivity.class);
                signInActivity.putExtra("user","pharmacy");
                startActivity(signInActivity);
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }



}

/*
*               Intent signInActivity = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(signInActivity);
* */