package com.example.manar.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DBHandler.*;

public class addnewTeachers extends AppCompatActivity {
    DBHandler db;
    EditText teachname, techpass,teachClass,teachsub,teachID;
    AdminClass admin =new AdminClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_teachers);
        //declare elements
        db = new DBHandler(getApplicationContext());
        teachname = (EditText) findViewById(R.id.teacherNameAdmin);
        techpass = (EditText) findViewById(R.id.teacherpassadmin);
        teachClass = (EditText) findViewById(R.id.classadmin);
        teachsub = (EditText) findViewById(R.id.teachersubjectadmin);
        teachID =(EditText) findViewById(R.id.TeacherIDadmin);
        Button bt=(Button)findViewById(R.id.save);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // call the fnction to check the information entred and add it
                ADDNEW();
            }
        });

        Button show = (Button) findViewById(R.id.button);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addnewTeachers.this,showTeachers.class);
                startActivity(intent);
            }
        });





    }


    public void ADDNEW()
    {
        if(teachClass.getText().toString().isEmpty()|| teachID.getText().toString().isEmpty()
                || teachname.getText().toString().isEmpty() ||teachsub.getText().toString().isEmpty()
                ||techpass.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_LONG).show();
        }
        else
        if(teachname.getText().toString().equals(admin.getName()))
        {
            Toast.makeText(getApplicationContext(), "User Name  already exist", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "NoT Added", Toast.LENGTH_LONG).show();
        }
        else
        if(db.isTeacher(teachname.getText().toString())&&!db.isStudent(teachname.getText().toString())) {

            if(!db.isSubjectExist(teachsub.getText().toString(),teachClass.getText().toString()))
            {
                Subject sub = new Subject(teachname.getText().toString(),teachsub.getText().toString(),teachClass.getText().toString());
                db.addNewSubject(sub);

                Toast.makeText(getApplicationContext(), "You added A new Subject to this Teacher", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"subject already added  this Class", Toast.LENGTH_LONG).show();

            }


        }
        else
        if(!db.isTeacher(teachname.getText().toString()) && db.isTeacherID(Integer.parseInt(teachID.getText()+"")))
        {
            Toast.makeText(getApplicationContext(), "User ID already exist", Toast.LENGTH_LONG).show();
        }
        else
        if(!db.isSubjectExist(teachsub.getText().toString(),teachClass.getText().toString())&&!db.isTeacher(teachname.getText().toString())
        &&!db.isStudent(teachname.getText().toString()))
        {

            Teacher T = new Teacher(Integer.parseInt(teachID.getText().toString()), teachname.getText().toString(),
                    techpass.getText().toString());
            db.addNewTeacher(T);
            Subject sub = new Subject(teachname.getText().toString(),teachsub.getText().toString(),teachClass.getText().toString());
            db.addNewSubject(sub);
            Toast.makeText( getApplicationContext()," added ", Toast.LENGTH_LONG).show();
        }
      else
        {
            Toast.makeText(getApplicationContext(), "Not Added", Toast.LENGTH_LONG).show();

        }


    }


}
