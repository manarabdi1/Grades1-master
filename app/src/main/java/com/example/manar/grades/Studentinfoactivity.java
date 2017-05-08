package com.example.manar.grades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import  DBHandler.*;
import java.util.ArrayList;

import DBHandler.DBHandler;


public class Studentinfoactivity extends AppCompatActivity {
    DBHandler db;
    ImageButton showmarks, Logout;
    Student student;
    ListView listview;
    ArrayList<String> subject_list;
    ArrayAdapter<String> adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfoactivity);

        //DBhanler element
        db=new DBHandler(getApplicationContext());

        //get the name of the student from the SavedSharedprefernce
        final String   name = SaveSharedPreference.getUserName(Studentinfoactivity.this);
        student =db.getStudentByName(name);
        TextView nametv=(TextView) findViewById(R.id.studentnametv);
        nametv.setText(student.getName());

        // prepared a listview for notes
      listview =(ListView) findViewById(R.id.listNotes);

        //List Subject to getNotes;
        subject_list= db.getAllSubjectNotes(student.getClass_name());
      adapter =new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txtitem,subject_list);
        listview.setAdapter(adapter);





        // get Student number of attendences
        int absentnum = db.getNumberOfAbsent(name);
        TextView absent = (TextView) findViewById(R.id.textView20);
        absent.setText(absent.getText()+"  =  "+absentnum);

       //show marks
        showmarks = (ImageButton) findViewById(R.id.Showmarks);
        showmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveSharedPreference.setUserName(Studentinfoactivity.this, name);
                Intent intent = new Intent(Studentinfoactivity.this, showmarksActivity.class);

                startActivity(intent);

            }
        });


        Logout = (ImageButton) findViewById(R.id.LogoutBT);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear sharedPrefrence values and go to Login Page
                SaveSharedPreference.clearUserName(Studentinfoactivity.this);
                Intent intent = new Intent(Studentinfoactivity.this, LogIn_activity.class);
                startActivity(intent);
            }
        });

        //call check sharedprefernces to stay logged or loggout
        chechSavedSharedPreference();

}


    public  void chechSavedSharedPreference()
    {
        String sharedName=SaveSharedPreference.getUserName(Studentinfoactivity.this);
        if(SaveSharedPreference.getUserName(Studentinfoactivity.this).length() == 0)
        {
            Intent intent =new Intent(this , LogIn_activity.class);
            startActivity(intent);
        }

        else
        if(db.isStudent(sharedName))
        {
            // Stay at the current activity.
        }
    }

    public void onStart()
    {
        super.onStart();



    }





}
