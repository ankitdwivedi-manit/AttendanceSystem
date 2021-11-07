package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class CreateAttendance extends AppCompatActivity {

    Button btnCreateAttendance;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_attendance);

        btnCreateAttendance = findViewById(R.id.btnCreateAttendance);

        Calendar calendar = Calendar.getInstance();
        final int year  = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnCreateAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAttendance.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                showDate(i, i1+1, i2);
            }
        };

    }
    private void showDate(int year, int month, int day) {
        String date = day + "-" + month + "-" + year;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Students");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        helper((Map<String,Object>) dataSnapshot.getValue(), date);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        Toast.makeText(this, "Attendance Created Successfully!", Toast.LENGTH_SHORT).show();
    }


    private void helper(Map<String,Object> users, String date) {

        for (Map.Entry<String, Object> entry : users.entrySet()){
            databaseReference = FirebaseDatabase.getInstance().getReference("Attendance");
            Map singleUser = (Map) entry.getValue();
            databaseReference.child(date).child((String) Objects.requireNonNull(singleUser.get("scholar_no"))).setValue("A");
        }

    }

}