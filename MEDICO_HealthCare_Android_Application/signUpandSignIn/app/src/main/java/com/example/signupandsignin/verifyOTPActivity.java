package com.example.signupandsignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class verifyOTPActivity extends AppCompatActivity {

    private EditText inputCode1,inputCode2,inputCode3,inputCode4,inputCode5,inputCode6;
    private String verificationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-------------------this is code just to hide the action or title bar from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------end------------------------------------

        setContentView(R.layout.activity_verify_o_t_p);

        TextView textMobile =(TextView) findViewById(R.id.textMobile);
        textMobile.setText(getIntent().getStringExtra("number"));

        initializationView();
        setOTPInputs();

        final ProgressBar progressBar = findViewById(R.id.progressbar);
        final Button btnVerify = (Button) findViewById(R.id.buttonVerify);
        verificationID = getIntent().getStringExtra("verificationID");

        //TODO :: click OTP verify button
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCode1.getText().toString().trim().isEmpty()
                ||inputCode2.getText().toString().trim().isEmpty()
                ||inputCode3.getText().toString().trim().isEmpty()
                ||inputCode4.getText().toString().trim().isEmpty()
                ||inputCode5.getText().toString().trim().isEmpty()
                ||inputCode6.getText().toString().trim().isEmpty()){
                    Toast.makeText(verifyOTPActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO :: combining the input from verify OTP
                String code = inputCode1.getText().toString()+
                        inputCode2.getText().toString()+
                        inputCode3.getText().toString()+
                        inputCode4.getText().toString()+
                        inputCode5.getText().toString()+
                        inputCode6.getText().toString();
                System.out.println("1");
                //TODO :: if Verification is not null
                if(verificationID!=null){
                    System.out.println("2");
                    progressBar.setVisibility(View.VISIBLE);
                    System.out.println("2.0");
                    btnVerify.setVisibility(View.INVISIBLE);
                    System.out.println("2.5");
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationID, code);
                    System.out.println("3");
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnVerify.setVisibility(View.VISIBLE);

                                    if(task.isSuccessful()){
                                        System.out.println("4");
                                        Toast.makeText(verifyOTPActivity.this, "Wow you are Valid", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        System.out.println("5");
                                        Toast.makeText(verifyOTPActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );





                }



            }
        });

        //TODO :: here is ResendOTP Event Call
        (findViewById(R.id.textResendOTP)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + getIntent().getStringExtra("number"),
                                60,
                                TimeUnit.SECONDS,
                                verifyOTPActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(verifyOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String newVerificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                         verificationID = newVerificationID;
                                        Toast.makeText(verifyOTPActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }

                        );


                    }
                }
        );

    }

    public void initializationView(){
        inputCode1 = (EditText) findViewById(R.id.inputCode1);
        inputCode2 = (EditText) findViewById(R.id.inputCode2);
        inputCode3 = (EditText) findViewById(R.id.inputCode3);
        inputCode4 = (EditText) findViewById(R.id.inputCode4);
        inputCode5 = (EditText) findViewById(R.id.inputCode5);
        inputCode6 = (EditText) findViewById(R.id.inputCode6);
    }
    private void setOTPInputs(){
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}