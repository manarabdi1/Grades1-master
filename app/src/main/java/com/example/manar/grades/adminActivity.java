package com.example.manar.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class adminActivity extends AppCompatActivity {

    ImageButton teacher,student,atendanc;
    DBHandler db;
    ImageButton Logout;
   // AdminClass admin = new AdminClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db = new DBHandler(getApplicationContext());
        teacher = (ImageButton) findViewById(R.id.teacherbt);
        student = (ImageButton) findViewById(R.id.studentbt);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, addnewTeachers.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, addnewStudent.class);
                startActivity(intent);
            }
        });


        Logout = (ImageButton) findViewById(R.id.LogoutBTadmin);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear sharedPrefrence values and go to Login Page
                SaveSharedPreference.clearUserName(adminActivity.this);
                Intent intent = new Intent(adminActivity.this, LogIn_activity.class);
                startActivity(intent);
            }
        });
        atendanc = (ImageButton) findViewById(R.id.attend);
        atendanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this,attendaceActivity.class);
                startActivity(intent);
            }
        });

        //call check sharedprefernces to stay logged or loggout
        chechSavedSharedPreference();
    }

    public  void chechSavedSharedPreference()
    {
        String sharedName=SaveSharedPreference.getUserName(adminActivity.this);
        if(SaveSharedPreference.getUserName(adminActivity.this).length() == 0)
        {
            Intent intent =new Intent(this , LogIn_activity.class);
            startActivity(intent);
        }
        else
        if(sharedName.equals(LogIn_activity.admin.getName()))
        {
            //Stay in this activity
        }

    }


}


