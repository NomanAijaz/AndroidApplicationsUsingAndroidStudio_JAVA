package com.example.signupandsignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

        EditText numberField,passwordField;
        TextView forgotPassword;
        ImageView signInWithGoogle,getSignInWithFacebook;
        Button continueBtn;

        String userNumber, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-------------------this is code just to hide the action or title bar from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------end------------------------------------

        setContentView(R.layout.activity_sign_in);
        InitializationOfViews();




    }

    public void InitializationOfViews(){
        numberField = (EditText) findViewById(R.id.numberField);
        passwordField=(EditText)findViewById(R.id.passwordField);
        forgotPassword =(TextView) findViewById(R.id.forgotPassword);
        signInWithGoogle=(ImageView) findViewById(R.id.signInWithGoogle);
        getSignInWithFacebook =(ImageView)findViewById(R.id.signInWithFacebook);
    }
}