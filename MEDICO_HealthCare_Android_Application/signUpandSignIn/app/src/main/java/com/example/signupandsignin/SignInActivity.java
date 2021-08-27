package com.example.signupandsignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {


       private EditText numberField,passwordField;
       private TextView forgotPassword;
       private ImageView signInWithGoogle,getSignInWithFacebook;
       private Button continueBtn;

       private String userNumber, userPassword,userNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-------------------this is code just to hide the action or title bar from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------end------------------------------------

        setContentView(R.layout.activity_sign_in);
        System.out.println("0");
        InitializationOfViews();
        //TODO :: catch the thrown name of the user
        userNameString = getIntent().getStringExtra("user");


        //TODO :: signIn Button


    }

    public void InitializationOfViews(){
        numberField = (EditText) findViewById(R.id.numberField);
        passwordField=(EditText)findViewById(R.id.passwordField);
        forgotPassword =(TextView) findViewById(R.id.forgotPassword);
        signInWithGoogle=(ImageView) findViewById(R.id.signInWithGoogle);
        getSignInWithFacebook =(ImageView)findViewById(R.id.signInWithFacebook);
    }
    public String getNumber(){return userNumber;}
    public String getPass(){return userPassword;}
    public String getIndentification(){return userNameString;}

}