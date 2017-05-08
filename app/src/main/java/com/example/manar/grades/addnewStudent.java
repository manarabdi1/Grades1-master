package com.example.manar.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DBHandler.*;

public class addnewStudent extends AppCompatActivity {
     Button save;
    EditText name,id ,pass,Class;
    DBHandler db;
    final AdminClass admin =new AdminClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_student);
// delare elements
        db  = new DBHandler( getApplicationContext());
        name = (EditText) findViewById(R.id.stname);
        id = (EditText) findViewById(R.id.stuiD);
        pass = (EditText) findViewById(R.id.stpass);
        Class = (EditText) findViewById(R.id.stuClass);
        save = (Button) findViewById(R.id.savestubt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // call the fnction to check the information entred and add it
                ADDNEW();
            }
        });



    }


    public void ADDNEW()
    {
        if(name.getText().toString().isEmpty() || id.getText().toString().isEmpty() || pass.getText().toString().isEmpty()
                || Class.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_LONG).show();
        }
        else
        if(name.getText().toString().equals(admin.getName()))
        {
            Toast.makeText(getApplicationContext(), "User Name  already exist", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "NoT Added", Toast.LENGTH_LONG).show();
        }
        else
        if(!db.isStudent(name.getText().toString())&&!db.isTeacher(name.getText().toString()))
        {
            if(!db.isStudentId(Integer.parseInt(id.getText().toString())))
            {
                Student st = new Student(Integer.parseInt(id.getText().toString()),name.getText().toString()
                        ,pass.getText().toString(),Class.getText().toString());
                db.addNewStudent(st);

                Toast.makeText(getApplicationContext(), " Added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), " Not Added ", Toast.LENGTH_LONG).show();
            }
        }
        else
        if(db.isStudent(name.getText().toString())||db.isTeacher(name.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "User Name  already exist", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "NoT Added", Toast.LENGTH_LONG).show();

        }
    }


}
