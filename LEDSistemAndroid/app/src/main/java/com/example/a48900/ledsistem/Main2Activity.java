package com.example.a48900.ledsistem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.a48900.ledsistem.response.PendingQuestionResponse;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
private RadioGroup radioGroup;
  private RadioButton radioButton;

    private Button button_submit;
    private int attemptAnswer =0;
     private String username;
PendingQuestionResponse pendingQuestionResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       username= getIntent().getStringExtra("username");
        generateQuestion(username);
        button_submit=(Button)findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkAnswer(username);
               // Intent i= new Intent(getApplicationContext(),Main3Activity.class);
               // startActivity(i);
            }
        });
    }

    private void generateQuestion(String username){
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
            convertJsonToObject(bufferedReader);

        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
         
    }
    private String convertJsonToObject(BufferedReader bufferedReader){
        //instantiate Gson
        final Gson gson = new Gson();

        //pass root element type to fromJson method along with input stream
        pendingQuestionResponse= gson.fromJson(bufferedReader, PendingQuestionResponse.class);

        String answerType = pendingQuestionResponse.answerType();
                //dari ini isi ke field2 pertanyaan yang ada di UI yak
        Log.e(TAG, "number of coupons from json response after gson parsing"+cpnlst.size());

        return answerType;
    }
    private void checkAnswer(username){
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            int selectedId = radioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            String answer = radioButton.getText();
            attemptAnswer+= 1;
            if(answer.equals(pendingQuestion.correctAnswer)){

                submitQuestion(pendingQuestion,username);
                submitHistory(pendingQuestion,username,answer);
            }else{
                submitHistory(pendingQuestion,username,answer);
            }

    }
    private void submitQuestion(PendingQuestionResponse pendingQuestion,String username){
        String serviceUrl = "http://localhost:7003/automation/api/insert/submitquestion";
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        URL url = null;
        String input = "username="+username+
                    "&idquestion="+pendingQuestion.id+
                    "&attemptanswer="+attemptanswer+
                    "&answerstatus=Passed"+
                    "&createdby="+username+
                    "&createddate="+date;
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
          //  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //pass buffered reader to convert json to java object using gson
            //convertJsonToObject(bufferedReader);

        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
    }
    private void submitHistory(PendingQuestionResponse pendingQuestion,String username,String answer){
        String serviceUrl = "http://localhost:7003/automation/api/insert/history";
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        URL url = null;
        String input = "username="+username+
                    "&idquestion="+pendingQuestion.id+
                    "&answerquestion="+answer+
                    "&createdby="+username+
                    "&createddate="+date;
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
          //  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //pass buffered reader to convert json to java object using gson
            //convertJsonToObject(bufferedReader);

        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
    }
}
