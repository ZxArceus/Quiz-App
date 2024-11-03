package com.example.quiz_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class question extends AppCompatActivity {
    TextView questionText;
    Button option1,option2,option3,option4;

int quesNo=0;
    String ans;
    ArrayList<String> opt;
    int questionIndex=0;
    int correctIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        AndroidNetworking.initialize(this);


        start(quesNo);



    }
    void init(){
        questionText = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);


        // Fetch questions from API

    }
     void  fetchQuestion(int quesN0){
        disableOptions();
         opt=new ArrayList<>();
         Intent intent=getIntent();
         String apiUrl=intent.getStringExtra("key");

        AndroidNetworking.get(apiUrl)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("yes",response.toString());
                        try {
                            JSONArray jAraay=response.getJSONArray("results");
                            JSONObject jObject=jAraay.getJSONObject(quesNo);
                            ans = jObject.getString("correct_answer");
                            String question=jObject.getString("question");
                            String mainQues=correct(question);
                            questionText.setText(mainQues);

                            JSONArray wrongOptions=jObject.getJSONArray("incorrect_answers");
                            opt.clear();

                            opt.add( correct(wrongOptions.getString(0)));
                            opt.add( correct(wrongOptions.getString(1)));
                            opt.add( correct(wrongOptions.getString(2)));
                            opt.add(correct(ans));
                            Collections.shuffle(opt);
                            correctIndex=opt.indexOf(ans);

                            option1.setText(opt.get(0));

                            option2.setText(opt.get(1));
                            option3.setText(opt.get(2));
                            option4.setText(opt.get(3));
                            enableOptions();

                        }catch (Exception e){
                            e.printStackTrace();
                            gotoMainActivity();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.d("error", anError.toString());
                        gotoMainActivity();
                    }
                });
    }
    void check(){
    option1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(correctIndex==0){

                startNewQuestion();
            }
            else {

                gotoMainActivity();
            }
        }
    });
    option2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(correctIndex==1){

                startNewQuestion();
            }
            else {

                gotoMainActivity();
            }
        }
    });
    option3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(correctIndex==2){

                startNewQuestion();
            }
            else {


                gotoMainActivity();
            }
        }
    });
    option4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(correctIndex==3){


                startNewQuestion();
            }
            else {


                gotoMainActivity();
            }
        }
    });





     }


    void gotoMainActivity(){

        int a = correctIndex+1;
        Toast.makeText(this, "correct option is option  "+a, Toast.LENGTH_SHORT).show();
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent=new Intent(question.this, MainActivity.class);
              startActivity(intent);
          }
      },1000);
    }
    void startNewQuestion(){
        Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        quesNo++;
        start(quesNo);
    }
    void start(int quesNo){
        fetchQuestion(quesNo);
        check();
    }
    void enableOptions(){
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }
    void disableOptions(){
        option4.setEnabled(false);
        option3.setEnabled(false);
        option1.setEnabled(false);
        option2.setEnabled(false);



    }
    String correct(String str){
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!='@'|| str.charAt(i)!='#'|| str.charAt(i)!='&'|| str.charAt(i)!=';'){
                sb.append(str.charAt(i));
            }
        }
        return sb.toString().replace("quot","'").replace("039","'").replace("&","").replace(";","");
    }




}
