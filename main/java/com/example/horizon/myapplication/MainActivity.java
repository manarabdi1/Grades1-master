package com.example.horizon.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static android.R.attr.value;


public class MainActivity extends Activity {
    Button login_btn, cancel_btn;
    EditText username, password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup ..
        DBHandler loginDatabaseAdapter;
        loginDatabaseAdapter = new DBHandler(this);

        login_btn = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        cancel_btn = (Button) findViewById(R.id.cancel);

        Student S = new Student();
        final String storedPassword = S.getPassword().toString();
        final String storedUsername = S.getName().toString();
        Teacher T = new Teacher();
        final String T_name = T.getTeacher_name().toString();
        final String T_pass = T.getTeacher_password().toString();
      /*  final String storedPassword = loginDatabaseAdapter.getPassword().toString();
        final String storedUsername = loginDatabaseAdapter.getUsername().toString();
        final String T_name = loginDatabaseAdapter.getTeacher_name().toString();
        final String T_pass = loginDatabaseAdapter.getTeacher_password().toString();
        */


        DBHandler db = new DBHandler(getApplicationContext());
        Student SS = new Student(2, "suha", "123", "english");
        db.addNewStudent(SS);

        Teacher TT = new Teacher(1, "manar", "1234", "english");
        db.addNewTeacher(TT);


        // onCLICK..
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // case 1: empty field problem..
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_LONG).show();
                    return;
                }

                // case 2: Authentications problem ..
                // 2.1 : if student ..
                if (password.getText().toString().equals(storedPassword) && username.getText().toString().equals(storedUsername)) {
                    Toast.makeText(getApplicationContext(), "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Student.class);
                    startActivity(intent);
                    finish();

                }
                // 2.2 : if Teacher ..
                if (password.getText().toString().equals(T_pass) && username.getText().toString().equals(T_name)) {
                    Toast.makeText(getApplicationContext(), "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(MainActivity.this, Teacher.class);
                    startActivity(intent2);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                     /* Intent in  = new Intent(MainActivity.this , Teacher.class);
                    startActivity(in);
                    finish();*/
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }}
