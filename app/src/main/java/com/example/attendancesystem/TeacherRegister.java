package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherRegister extends AppCompatActivity {

    EditText txtName, txtUserId, txtPassword;
    Button teacherRegister;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        txtName = (EditText) findViewById(R.id.etEnterTeacherName);
        txtUserId = (EditText) findViewById(R.id.etEnterTeacherUserId);
        txtPassword = (EditText) findViewById(R.id.etEnterTeacherPassword);
        teacherRegister = (Button) findViewById(R.id.btnTeacherRegister);
       teacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTeacher(view);
            }
        });

    }


    public boolean validationCheck(){
        String msg = "field cannot be empty";
        if(TextUtils.isEmpty(txtName.getText().toString())){
            txtName.setError("Name :" + msg);
            return false;
        }
        if(TextUtils.isEmpty(txtPassword.getText().toString())){
            txtPassword.setError("Password :" + msg);
            return false;
        }
        if(TextUtils.isEmpty(txtUserId.getText().toString())){
            txtPassword.setError("UserID :" + msg);
            return false;
        }
        return true;
    }

    public void clearField(){
        txtName.setText("");
        txtPassword.setText("");
        txtUserId.setText("");
    }

    public void addTeacher(View view){
        if(validationCheck()){
            databaseReference = FirebaseDatabase.getInstance().getReference("Teachers");
            ModelTeacher teacher = new ModelTeacher();
            teacher.setName(txtName.getText().toString());
            teacher.setPassword(txtPassword.getText().toString());
            teacher.setUser_id(txtUserId.getText().toString());

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(teacher.getUser_id())){
                        txtUserId.setError("Enter valid user_id");
                        Toast.makeText(TeacherRegister.this, "Enter valid userid", Toast.LENGTH_SHORT).show();
                    }else{
                        databaseReference.child(teacher.getUser_id()).setValue(teacher);
                        Toast.makeText(TeacherRegister.this, "Teacher added successfully!", Toast.LENGTH_SHORT).show();
                        clearField();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}