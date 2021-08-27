package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.Extension;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText txtName,txtNumber;
    int randNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.btn);
        txtName = (EditText) findViewById(R.id.name);
        txtNumber = (EditText) findViewById(R.id.phoneNumber);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String APIkey = "apikey=" + "eadb2ab3a1msh016ad59c64bd6fbp147859jsn0be1789e1713";
                    Random next = new Random();
                    randNumber = next.nextInt(99999);
                    String message ="&message="+"Hello "+txtName.getText().toString()+" your OTP is "+randNumber;
                    String sender = "&sender="+"NomanAijaz";
                    String number ="&number="+txtNumber.getText().toString();

                    HttpURLConnection conn = (HttpURLConnection) new URL("https://rapidapi.com/d7admin/api/d7sms?").openConnection();
                    String data = APIkey+number+message+sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length",Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));

                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer  stringBuffer = new StringBuffer();

                    String line;
                    while((line = rd.readLine()) !=null){
                           stringBuffer.append(line);
                    }
                      rd.close();
                        Toast.makeText(getApplicationContext(),"OTP send Successfully",Toast.LENGTH_LONG).show();

                }catch(ProtocolException | UnsupportedEncodingException e){} catch (MalformedURLException e) {
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"WE found an error", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"WE found an error in sec catch", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(MainActivity.this, OTP_A2.class);
                intent.putExtra("Name",txtName.getText().toString());
                startActivity(intent);
            }
        });





    }
}