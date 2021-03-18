package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {
   EditText txtName;
   Button btnOk, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        btnOk = (Button)findViewById(R.id.btn_activity_name_ok);
        btnCancel = (Button)findViewById(R.id.btn_activity_name_cancel);
        txtName = (EditText)findViewById(R.id.txt_activity_name_name);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtName.getText().toString();

                Intent intent = new Intent(NameActivity.this, QuizActivity.class);
                intent.putExtra("p_name",user);
                startActivity(intent);


                finish();
            }
        });
    }
}