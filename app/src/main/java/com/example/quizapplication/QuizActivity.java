package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion,  tvTimer,tvQuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    Button btnNext, btnprevious;
    private List<QuestionModel> questionsList;
    private String user_Name;

    int totalQuestions;
    int counter = 0;
    int score = 0;
    ColorStateList dfRbcolor;
    boolean answered;
    CountDownTimer countDownTimer;


    private QuestionModel currentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.tv_activity_quiz_quiz);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.rg_quiz_activity_options);

        rb1 = findViewById(R.id.rb_activity_quiz_opt1);
        rb2 = findViewById(R.id.rb_activity_quiz_opt2);
        rb3 = findViewById(R.id.rb_activity_quiz_opt3);
        rb4 = findViewById(R.id.rb_activity_quiz_opt4);
        btnNext = (Button) findViewById(R.id.btn_activity_quiz_next);
        btnprevious = (Button) findViewById(R.id.btn_activity_quiz_previous);

        dfRbcolor = rb1.getTextColors();

        addquestions();

        totalQuestions = questionsList.size();
        showNextQuestion();


        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(answered == false){

                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();
                    }
                    else{
                        Toast.makeText(QuizActivity.this,"Please select an option",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    showNextQuestion();
                }
            }
        });

        user_Name = getIntent().getStringExtra("p_name");
        //setTitle(getResources().getString(R.string.app_name)+" : "+user_Name);

        // Toast toast = Toast.makeText(QuizActivity.this,User_Name,Toast.LENGTH_LONG);
        //  toast.show();

//        btn_next = (Button) findViewById(R.id.btn_quiz_activity_next);
//        btn_previous=(Button) findViewById(R.id.btn_quiz_activity_previous);
//        txt_question = (TextView) findViewById(R.id.txt_quiz_activity_question);
//        rb1 = (RadioButton) findViewById(R.id.rb_quiz_activity_opt1);
//        rb2 = (RadioButton) findViewById(R.id.rb_quiz_activity_opt2);
//        rb3 = (RadioButton) findViewById(R.id.rb_quiz_activity_opt3);
//        rb4 = (RadioButton) findViewById(R.id.rb_quiz_activity_opt4);
//        rgquiz = (RadioGroup) findViewById(R.id.rg_quiz_activity);
//        questionList = getQuestion();
//        score = 0;
//        displayQuestions();
//
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getScore();
//                if(numberofQuestions==4) {
//                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
//                    intent.putExtra("User_Name",user_Name);
//                    intent.putExtra("score",score);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    numberofQuestions++;
//                    displayQuestions();
//                }
//            }
//        });
//
//        btn_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(numberofQuestions > 0 ){
//                    numberofQuestions--;
//                    displayQuestions();
//                }
//            }
//        });
    }

    private void checkAnswer() {

        answered = true;
        RadioButton rbselected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbselected) + 1;
        if(answerNo == currentQuestion.getCorrect()){

            score++;
        }
        //rb1.setTextColor(Red);

        if(counter < totalQuestions){
            btnNext.setText("Next");
        }
        else{
            btnNext.setText("Finish");
            btnNext.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuizActivity.this,ScoreActivity.class);
                    intent.putExtra("p_score",score);
                    intent.putExtra("p_name",user_Name);
                    startActivity(intent);
                    finish();
                }
            });

        }

    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbcolor);
        rb2.setTextColor(dfRbcolor);
        rb3.setTextColor(dfRbcolor);
        rb4.setTextColor(dfRbcolor);


        if(counter < totalQuestions){
            timer();
            currentQuestion = questionsList.get(counter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            counter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question : "+counter+"/"+totalQuestions);
            answered = false;
        }
        else{
            finish();
        }
    }
    private void timer(){
        countDownTimer =  new CountDownTimer(20000,1000){
            @Override
            public void onTick(long l) {
                tvTimer.setText("00 : " + l/1000);

            }

            @Override
            public void onFinish(){
             showNextQuestion();
            }
        }.start();
    }

    private void addquestions() {
        questionsList.add(new QuestionModel("Which of the following is not a programming language ?", "Java", "Php", "Python", "HTML", 4));
        questionsList.add(new QuestionModel("Which of the following is not a RDBMS ?", "MS_SQL", "Oracle", "MongoDB", "MySQL", 3));
        questionsList.add(new QuestionModel("Which of the following is not an OS ?", "Windows", "Linux", "Casendra", "Ubuntu", 3));
        questionsList.add(new QuestionModel("Which of the following language is supported in android development ?", "Kotlin", "Php", "Python", "VB", 1));
        questionsList.add(new QuestionModel("MS-WORD is an example of ____ ?", "An operating system", "A processing device", "Application software", "An input device", 3));
        questionsList.add(new QuestionModel("What is a default file extension for all word documents ?", "TXT", "WRD", "FIL", "DOC", 4));
        questionsList.add(new QuestionModel("Junk e-mail is also called ?", "Spam", "Spoof", "Sniffer Script", "Spool", 1));
        questionsList.add(new QuestionModel("A ___ is a software program used to view Web pages ?", "Site", "Host", "Link", "Browser", 4));
        questionsList.add(new QuestionModel("The first computer was programmed using ?", "Assembly Language", "Machine language", "Spaghetti Code", "Source Code", 2));
        questionsList.add(new QuestionModel("A ___ is approximately one billion bytes ?", "Megabyte", "Gigabyte", "Terabyte", "Kilobyte", 1));


    }

}
