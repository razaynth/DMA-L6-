package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    Button btn_next, btn_previous;
    RadioGroup rgQuiz;
    String [][] questionList;
    TextView tvQuestion;
    EditText txtName;
    RadioButton rbopt1,rbopt2,rbopt3,rbopt4;
    int questionNo=0, score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String user = getIntent().getStringExtra("p_name");

        setTitle(getResources().getString(R.string.app_name)+": "+user);

        txtName = (EditText)findViewById(R.id.txt_activity_name_name);
        tvQuestion = (TextView)findViewById(R.id.tv_activity_quiz_quiz);
        rgQuiz = (RadioGroup)findViewById(R.id.rg_quiz_activity_options);
        rbopt1 = (RadioButton)findViewById(R.id.rb_activity_quiz_opt1);
        rbopt2 = (RadioButton)findViewById(R.id.rb_activity_quiz_opt2);
        rbopt3 = (RadioButton)findViewById(R.id.rb_activity_quiz_opt3);
        rbopt4 = (RadioButton)findViewById(R.id.rb_activity_quiz_opt4);
        btn_next = (Button)findViewById(R.id.btn_activity_quiz_next);
        btn_previous = (Button)findViewById(R.id.btn_activity_quiz_previous);
        questionList = getQuestionList();

        displayQuestion();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScore();
                if(questionNo==4){
                Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                intent.putExtra("p_name", user);
                intent.putExtra("p_score",score);
                startActivity(intent);
                finish();}
                else {
                    questionNo++;
                    displayQuestion();
                }
            }
        });


        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (questionNo > 0) {
                    questionNo--;
                    displayQuestion();



                  }
            }

        });
    }

    String[][] getQuestionList() {
        String[][] questionList = new String[5][6];
        String [] questions = getResources().getStringArray(R.array.question);
        for(int i=0; i<5; i++)
        {
          String [] question= questions[i].split(",");

            for(int j=0; j<6; j++)
            {
            questionList[i][j]=question[j];

            }

        }
        return questionList;
    }
    void displayQuestion() {
        tvQuestion.setText(questionList[questionNo][0]);
        rbopt1.setText(questionList[questionNo][1]);
        rbopt2.setText(questionList[questionNo][2]);
        rbopt3.setText(questionList[questionNo][3]);
        //rbopt4.setText(questionList[questionNo][4]);
        ((RadioButton)rgQuiz.getChildAt(3)).setText(questionList[questionNo][4]);
        rbopt1.setChecked(false);
        rbopt2.setChecked(false);
        rbopt3.setChecked(false);
        rbopt4.setChecked(false);

    }
     void getScore(){
        int answerID =rgQuiz.getCheckedRadioButtonId();
        RadioButton answer = (RadioButton) findViewById(answerID);
        //if(answer.getText().toString()==questionList[questionNo][5]){
         if answerID == questionList.();
        score++;
        }
     }

}