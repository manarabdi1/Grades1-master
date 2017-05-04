package com.example.manar.grades;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class marksActivity extends AppCompatActivity {
    DBHandler db;
    EditText sub, StuName, mark;
    Button save, update;


    String teacherName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        // objects element decalre
        db = new DBHandler(getApplicationContext());
        teacherName = SaveSharedPreference.getUserName(marksActivity.this);
        sub = (EditText) findViewById(R.id.subjectNamemarks);

        StuName = (EditText) findViewById(R.id.studentName);
        mark = (EditText) findViewById(R.id.grade);
        save = (Button) findViewById(R.id.Savemarks);
        update = (Button) findViewById(R.id.update);

        //call function to check empty fields

        //on click save events
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckField()&&checkTeacher())
                    if (db.isStudent(StuName.getText().toString())) {
                        InsertMark();
                        Toast.makeText(marksActivity.this,"Added" ,Toast.LENGTH_LONG).show();
                    }
                else
                {
                     Toast.makeText(marksActivity.this,"This Student dose Not Exisit",Toast.LENGTH_LONG).show();
                }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckField()&&checkTeacher())
                    if (db.isStudent(StuName.getText().toString())) {
                        Toast.makeText(marksActivity.this,"Added" ,Toast.LENGTH_LONG).show();
                        UpdateMark();
                        Toast.makeText(marksActivity.this,"Added" ,Toast.LENGTH_LONG).show();
                    }
                else
                {
                    Toast.makeText(marksActivity.this,"This Student dose Not Exisit",Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    public void InsertMark() {
        String name = StuName.getText().toString();
        String _sub = sub.getText().toString();
        String grade = mark.getText().toString();

        Marks marks = new Marks(name, _sub, Integer.parseInt(grade));
        db.addNewMarks(marks);
    }

    public void UpdateMark() {
        String name = StuName.getText().toString();
        String _sub = sub.getText().toString();
        String grade = mark.getText().toString();
        Marks marks = new Marks(name, _sub, Integer.parseInt(grade));
        db.UpdateMarks(marks);
    }

    public boolean CheckField() {
        if (sub.getText().toString().isEmpty() || StuName.getText().toString().isEmpty()
                || mark.getText().toString().isEmpty()) {
            Toast.makeText(marksActivity.this, "Empty Field ", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
        return  true;
        }
    }

    public boolean checkTeacher() {
        if(db.isTeachThisSub(teacherName , sub.getText().toString()))
        {
          return true;
        }
        else
        {
            Toast.makeText(marksActivity.this,"You cann't add mark to this subject",Toast.LENGTH_LONG).show();
            return false;
        }



    }


}
