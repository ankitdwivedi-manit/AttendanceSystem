package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {
    EditText enteredUserName;
    EditText enteredPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        enteredUserName = (EditText) findViewById(R.id.etAdminUserName);
        enteredPassword = (EditText) findViewById(R.id.etAdminPassword);
    }
    public void openActivity(View v) throws Exception{
        isUser();
    }

    private void isUser() throws Exception{
        final  String userEnteredUserName = enteredUserName.getText().toString().trim();
        final  String userEnteredPassword = enteredPassword.getText().toString().trim();
        if(!validationCheck()){
            return;
        }
        if(userEnteredUserName.equals("admin")){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Admin");
            Query checkUser = reference.child(userEnteredUserName);
            checkUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String passwordFromDB = snapshot.child("password").getValue(String.class);
                    if(userEnteredPassword.equals(passwordFromDB)){
                        Toast.makeText(AdminLoginActivity.this, "logging in....", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminLoginActivity.this, AdminMain.class);
                        clearField();
                        startActivity(intent);
                    }else{
                        Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            try{
                Long userId = Long.parseLong(enteredUserName.getText().toString());
                if(userId >= 19212001 && userId <= 192120094){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
                    Query checkUser = reference.child(userEnteredUserName);
                    checkUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String passwordFromDB = snapshot.child("password").getValue(String.class);
                            if(userEnteredPassword.equals(passwordFromDB)){
                                Toast.makeText(AdminLoginActivity.this, "logging in....", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AdminLoginActivity.this, StudentMain.class);
                                clearField();
                                startActivity(intent);
                            }else{
                                Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else if(userId >= 12345 && userId <= 12445){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Teachers");
                    Query checkUser = reference.child(userEnteredUserName);
                    checkUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String passwordFromDB = snapshot.child("password").getValue(String.class);
                            if(userEnteredPassword.equals(passwordFromDB)){
                                Toast.makeText(AdminLoginActivity.this, "logging in....", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AdminLoginActivity.this, TeacherMain.class);
                                clearField();
                                startActivity(intent);
                            }else{
                                Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean validationCheck(){
        String msg = "field cannot be empty";
        if(TextUtils.isEmpty(enteredUserName.getText().toString())){
            enteredUserName.setError(msg);
            return false;
        }
        if(TextUtils.isEmpty(enteredPassword.getText().toString())){
            enteredPassword.setError(msg);
            return false;
        }
        return true;
    }


    private void clearField(){
        enteredUserName.setText("");
        enteredPassword.setText("");
    }

}