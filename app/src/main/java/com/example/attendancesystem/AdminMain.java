package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMain extends AppCompatActivity {

    Button adminAddTeacher, adminAddStudent, adminChangePassword, adminLogout, adminCreateAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        adminAddTeacher = (Button)findViewById(R.id.btnAdminAddTeacher);
        adminAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, TeacherRegister.class);
                startActivity(intent);
            }
        });

        adminAddStudent = (Button)findViewById(R.id.btnAdminAddStudent);
        adminAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, StudentRegister.class);
                startActivity(intent);
            }
        });

        adminCreateAttendance = (Button)findViewById(R.id.btnAdminCreateAttendance);
        adminCreateAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, CreateAttendance.class);
                startActivity(intent);
            }
        });

        adminChangePassword = (Button)findViewById(R.id.btnAdminChangePassword);
        adminChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        adminLogout = (Button)findViewById(R.id.btnAdminLogout);
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(null, AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}