package com.example.signupandsignin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    Spinner spinnerGender,spinnerCity;
    ArrayAdapter<String> myAdapter;

    String genderOption,cityOption;
    String getData[] = new String[5];
    EditText firstName,lastName,email,address,contactNumber;
    Button btnContinue;
    int contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //-------------------this is code just to hide the action or title bar from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //-----------------------end------------------------------------

        setContentView(R.layout.activity_sign_up);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);

        InitializationOfViews();

        System.out.println("Iam 1");

        //TODO :: Event Continue Button of signUP
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                InitializationOfArray();
                System.out.println("Iam 4");
                if(((contactNumber.getText().toString().trim().isEmpty()))){
                    Toast.makeText(SignUpActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                    System.out.println("Iam 5");
                    return;
                }
                    progressBar.setVisibility(View.VISIBLE);
                    btnContinue.setVisibility(View.INVISIBLE);

                    //TODO:: set the authentication for OTP
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + contactNumber.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SignUpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                btnContinue.setVisibility(View.VISIBLE);


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                btnContinue.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                btnContinue.setVisibility(View.VISIBLE);

                                //TODO :: new Intent will be called for OTP
                                System.out.println("Iam 6");
                                Intent intent = new Intent(SignUpActivity.this,verifyOTPActivity.class);
                                intent.putExtra("number",contactNumber.getText().toString());
                                intent.putExtra("verificationID",verificationID);
                                startActivity(intent);

                            }
                        }

                );


            }
        });

        SpinnerGender();
        SpinnerCity();


    }

    public void InitializationOfViews() {
        //TODO :: giving references to the views of sign up
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        contactNumber = (EditText) findViewById(R.id.numberid);
        address = (EditText) findViewById(R.id.address);
        btnContinue = (Button) findViewById(R.id.continuebtn);
    }
    public void InitializationOfArray(){
        //TODO :: storing data from fields to string from sign up
        getData[0] = firstName.getText().toString();
        getData[1] = lastName.getText().toString();
        getData[2] = email.getText().toString();
        getData[3] = contactNumber.getText().toString();
        getData[4] = address.getText().toString();
    }
    //TODO :: spinners for signup
    public void SpinnerGender(){
        //TODO:: list to add gender in spinner
        ArrayList<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        list.add("N/A");
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        SetUpSpinner(list,spinnerGender);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO :: Select Gender from signup
                genderOption = spinnerGender.getSelectedItem().toString();
                System.out.println(genderOption);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void SpinnerCity(){
        //TODO:: list to add cities in spinner
        ArrayList<String> list = new ArrayList<String>();
        list.add("City");
        list.add("Sukkur");
        list.add("Larkana");
        list.add("Kumb");

        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        SetUpSpinner(list,spinnerCity);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO :: Select City from signup
                cityOption = spinnerCity.getSelectedItem().toString();
                System.out.println(cityOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    //TODO :: Adapter Method for spinner
    public void SetUpSpinner(ArrayList<String> list,Spinner sp){
        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        sp.setAdapter(myAdapter);
    }
}