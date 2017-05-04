package com.example.manar.grades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class teacherHomePage extends AppCompatActivity {

    ImageButton atendanc;
    ImageButton marks,logout,HW;
     DBHandler db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);
        db = new DBHandler(getApplicationContext());
        marks =(ImageButton) findViewById( R.id.Marks);
        logout = (ImageButton) findViewById(R.id.logoutBT);
        HW = (ImageButton) findViewById(R.id.hw) ;
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.setUserName(teacherHomePage.this,SaveSharedPreference.getUserName(teacherHomePage.this));
                Intent intent = new Intent(teacherHomePage.this,marksActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear sharedPrefrence values and go to Login Page
               SaveSharedPreference.clearUserName(teacherHomePage.this);
                Intent intent = new Intent(teacherHomePage.this, LogIn_activity.class);
                startActivity(intent);
            }
        });

        HW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(teacherHomePage.this,hwandwxamActivity.class);
                startActivity(intent);
            }
        });


        //call check sharedprefernces to stay logged or loggout
        chechSavedSharedPreference();

    }



    public  void chechSavedSharedPreference()
    {
        String sharedName=SaveSharedPreference.getUserName(teacherHomePage.this);
        if(SaveSharedPreference.getUserName(teacherHomePage.this).length() == 0)
        {
            Intent intent =new Intent(this , LogIn_activity.class);
            startActivity(intent);
        }
        else
        if(db.isTeacher(sharedName))
        {
            // Stay at the current activity.

        }

    }



}
