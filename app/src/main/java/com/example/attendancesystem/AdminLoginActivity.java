package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    EditText adminUserName;
    EditText adminPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminUserName = (EditText) findViewById(R.id.etAdminUserName);
        adminPassword = (EditText) findViewById(R.id.etAdminPassword);
    }
    public void openActivity(View v) throws Exception{
        isUser();
    }

    private void isUser() throws Exception{
        final  String userEnteredUserName = adminUserName.getText().toString().trim();
        final  String userEnteredPassword = adminPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Admin");
        Query checkUser = reference.child(userEnteredUserName);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String passwordFromDB = snapshot.child("password").getValue(String.class);
                if(userEnteredPassword.equals(passwordFromDB)){
                    Toast.makeText(AdminLoginActivity.this, "logging in....", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLoginActivity.this, AdminMain.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AdminLoginActivity.this, "Incorrect username password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}