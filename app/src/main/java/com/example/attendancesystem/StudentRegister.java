package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegister extends AppCompatActivity {

    EditText txtName, txtScholar_no, txtPassword, txtBatch;
    Button studentRegister;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        txtName = (EditText) findViewById(R.id.etEnterStudentName);
        txtScholar_no = (EditText) findViewById(R.id.etEnterStudentScholarNo);
        txtPassword = (EditText) findViewById(R.id.etEnterStudentPassword);
        txtBatch = (EditText) findViewById(R.id.etEnterStudentBatch);
        studentRegister = (Button) findViewById(R.id.btnStudentRegister);
        studentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent(view);
            }
        });

    }

    public boolean validationCheck(){
        String msg = "field cannot be empty";
        if(TextUtils.isEmpty(txtName.getText().toString())){
            txtName.setError("Name :" + msg);
            return false;
        }
        if(TextUtils.isEmpty(txtScholar_no.getText().toString())){
            txtScholar_no.setError("ScholarNo :" + msg);
            return false;
        }
        if(TextUtils.isEmpty(txtPassword.getText().toString())){
            txtPassword.setError("Password :" + msg);
            return false;
        }
        if(TextUtils.isEmpty(txtBatch.getText().toString())){
            txtBatch.setError("Batch :" + msg);
            return false;
        }
        return true;
    }

    public void clearField(){
        txtName.setText("");
        txtBatch.setText("");
        txtPassword.setText("");
        txtScholar_no.setText("");
    }

    public void addStudent(View view){
        if(validationCheck()){
            databaseReference = FirebaseDatabase.getInstance().getReference("Students");
            ModelStudent student = new ModelStudent();
            student.setName(txtName.getText().toString());
            student.setBatch(txtBatch.getText().toString());
            student.setPassword(txtPassword.getText().toString());
            student.setScholar_no(txtScholar_no.getText().toString());
            databaseReference.child(student.getScholar_no()).setValue(student);
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show();
            clearField();
        }
    }

}