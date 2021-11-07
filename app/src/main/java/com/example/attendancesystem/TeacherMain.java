package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherMain extends AppCompatActivity {

    Button teacherLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        teacherLogout = (Button) findViewById(R.id.btnTeacherLogout);
        teacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(null, AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}