package com.example.a48900.ledsistem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import com.example.a48900.ledsistem.response;

public class MainActivity extends AppCompatActivity {
    Button button_login;
    EditText username;
    String answerType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username   = (EditText)findViewById(R.id.txt_username);
        answerType = getQuestion(username.getText().toString());
        //method pindah layout
        button_login= (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerType.equals("freetext")){
                Intent intent= new Intent(getApplicationContext(),Main3Activity.class);
                intent.putExtra("username", username.getText().toString());

                startActivity(intent);

                }
                else{
                Intent i= new Intent(getApplicationContext(),Main2Activity.class);
                 intent.putExtra("username", username.getText().toString());
                startActivity(i);

                }

            }


        });
    }

    private String getQuestion(String username){
        String serviceUrl = "http://localhost:7003/automation/api/search/pendingquestion/";
        URL url = null;
        String input = "username="+username;
        try {
            Log.d(TAG, "call rest service to get json response");
            url = new URL(serviceUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset","utf-8");
            connection.setRequestProperty("Content-Length",""+Integer.toString(input.getBytes().length))
            connection.connect();
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(input);
            os.flush();
            os.close();
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //pass buffered reader to convert json to java object using gson
            return convertJsonToObject(bufferedReader);

        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
        return null;

    }
    public String convertJsonToObject(BufferedReader bufferedReader){
        //instantiate Gson
        final Gson gson = new Gson();

        //pass root element type to fromJson method along with input stream
        PendingQuestionResponse pendingQuestionResponse= gson.fromJson(bufferedReader, PendingQuestionResponse.class);

        String answerType = pendingQuestionResponse.answerType();
        Log.e(TAG, "number of coupons from json response after gson parsing"+cpnlst.size());

        return answerType;
    }

}

