package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class topic extends AppCompatActivity {
ListView lView;
String[] topics={"Mixed","Science ","Film","Music","Television","Mathematics","Mythology","Sports",
"Geography","History","Politics","Animals","Art"};
String quesField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        Intent intent=new Intent(topic.this, question.class);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,topics);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               switch (position){
                   case 0:
                       quesField="https://opentdb.com/api.php?amount=50&difficulty=easy&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case  1:
                       quesField="https://opentdb.com/api.php?amount=50&category=17&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;

                   case 2:
                       quesField="https://opentdb.com/api.php?amount=50&category=11&type=multiple";
                       //getQuestion(quesField);
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 3:
                       quesField="https://opentdb.com/api.php?amount=50&category=12&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case  4:
                       quesField="https://opentdb.com/api.php?amount=50&category=14&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 5:
                       quesField="https://opentdb.com/api.php?amount=50&category=19&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 6:
                       quesField="https://opentdb.com/api.php?amount=50&category=20&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 7:
                       quesField="https://opentdb.com/api.php?amount=50&category=21&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 8:
                       quesField="https://opentdb.com/api.php?amount=50&category=22&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 9:
                       quesField="https://opentdb.com/api.php?amount=50&category=23&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 10:
                       quesField="https://opentdb.com/api.php?amount=50&category=24&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 11:
                       quesField="https://opentdb.com/api.php?amount=50&category=27&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
                   case 12:
                       quesField="https://opentdb.com/api.php?amount=50&category=25&type=multiple";
                       intent.putExtra("key",quesField);
                       startActivity(intent);
                       break;
               }

            }
        });
       }
    void init(){
        lView=findViewById(R.id.chooseTopic);
    }
    void getQuestion(String quesField){
        Intent intent=new Intent(this, question.class);
        //intent.putExtra("key",quesField);
        startActivity(intent);
    }

}