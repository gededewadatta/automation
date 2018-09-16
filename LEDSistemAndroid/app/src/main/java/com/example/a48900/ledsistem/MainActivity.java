package com.example.a48900.ledsistem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.util.HashMap;
import java.util.Map;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
public class MainActivity extends AppCompatActivity {

    EditText txt_username, txt_password;
    Button button_login;
    String url = "http://140.213.15.233/led/api/automation/search/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        button_login = (Button) findViewById(R.id.button_login);

        Button button_login = (Button) findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("userName", txt_username.getText().toString());
                    postData.put("password", txt_password.getText().toString());


                    new SendDeviceDetails.execute("http://140.213.15.233/led/api/automation/search/", postData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

