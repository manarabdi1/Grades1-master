package com.example.manar.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn_activity extends AppCompatActivity {


    EditText user , pass;
    DBHandler db;
    Button cancel,login;
   public static AdminClass admin;
    private final String adminusername="manar";
    private  final String adminpassword = "12345";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);

        // assigning values ..
        db     = new DBHandler(getApplicationContext());
        cancel = (Button) findViewById(R.id.cancel);
        login  =(Button)findViewById(R.id.login);
        user   = (EditText)findViewById(R.id.editText);
        pass   = (EditText)findViewById(R.id.editText2);
        admin = new AdminClass(adminusername ,adminpassword);



        // listeners..
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //called the login function to check the user name and password
                    logIn();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Bye bye",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        String sharedName=SaveSharedPreference.getUserName(LogIn_activity.this);
        if(SaveSharedPreference.getUserName(LogIn_activity.this).length() == 0)
        {
          //  Intent intent =new Intent(this , LogIn_activity.class);
          //  startActivity(intent);
        }
        else
        if(db.isTeacher(sharedName))
        {
            // Stay at the current activity.
            Intent intent =new Intent(this , teacherHomePage.class);
            startActivity(intent);

        }
        else
            if(db.isStudent(sharedName))
            {
                Intent intent =new Intent(this , Studentinfoactivity.class);
                startActivity(intent);
            }
        else
            if(sharedName.equals(admin.getName()))
            {
                Intent intent =new Intent(this , adminActivity.class);
                startActivity(intent);
            }

    }


    public void logIn()
    {
        // case 1: empty fields..
        if( user.getText().toString().equals("") || pass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Empty Field",Toast.LENGTH_LONG).show();
            return;
        }
        if(user.getText().toString().equals(admin.getName()))
        {
            if(pass.getText().toString().equals(admin.getPassword()))
            {
                SaveSharedPreference.setUserName(LogIn_activity.this,user.getText().toString());
                Intent i=new Intent(LogIn_activity.this, adminActivity.class);
                startActivity(i);
                finish();
            }
        }
        // case 2: students ..
        else
        if (db.isStudent(user.getText().toString())) {
            if (db.isStudentPassword(pass.getText().toString(), user.getText().toString())) {
                SaveSharedPreference.setUserName(LogIn_activity.this,user.getText().toString());
                Intent i = new Intent(LogIn_activity.this, Studentinfoactivity.class);
                         startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Password is Incorrect", Toast.LENGTH_LONG).show();

            }
        } else if (!db.isStudent(user.getText().toString()) && !db.isTeacher(user.getText().toString())) {
            Toast.makeText(getApplicationContext(), "User Name is Incorrect", Toast.LENGTH_LONG).show();
        }

        // case 3: teachers ..
        else
        if(db.isTeacher(user.getText().toString()))
        {
            if(db.isTeacherpassword(pass.getText().toString(),user.getText().toString()))
            {
                SaveSharedPreference.setUserName(LogIn_activity.this,user.getText().toString());
                Intent intent = new Intent(LogIn_activity.this, teacherHomePage.class);
                intent.putExtra("name",user.getText().toString());
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"password is Incorrect" ,Toast.LENGTH_LONG).show();
            }

        }
        else
        if(!db.isTeacher(user.getText().toString())&& !db.isStudent(user.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"User Name is Incorrect" ,Toast.LENGTH_LONG).show();
        }

    }

    }

