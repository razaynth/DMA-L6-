package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.SplittableRandom;

public class ScoreActivity extends AppCompatActivity {
 TextView tvName, tvScore;
 Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        String p_name =getIntent().getStringExtra("p_name");
         int score = getIntent().getIntExtra("p_score", 0);
        tvName = (TextView) findViewById(R.id.tv_activity_score_name);
        tvScore = (TextView) findViewById(R.id.tv_activity_score_score);
        tvName.setText(p_name);
        tvScore.setText(Integer.toString(score));

        btnFinish = (Button) findViewById(R.id.btn_activity_score_finish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }
        });
    }
}