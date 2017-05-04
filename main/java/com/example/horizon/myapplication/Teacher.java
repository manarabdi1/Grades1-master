package com.example.horizon.myapplication;

        import android.content.Intent;
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.view.View;
        import android.widget.GridView;
        import android.widget.ImageButton;


public class Teacher extends Activity {
    int id;
    String teacher_name;
    String teacher_password;
    String Class_name;
    public Teacher()
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup ..
        ImageButton att , marks, exams, homeworks;
        att       = (ImageButton)findViewById(R.id.attendance);
        marks     = (ImageButton)findViewById(R.id.marks);
        exams     = (ImageButton)findViewById(R.id.exams);
        homeworks = (ImageButton)findViewById(R.id.homeworks);

        att.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Teacher.this , TakeAtt.class);
                startActivity(intent1);
                finish();

            }

        }
        );


        marks.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Teacher.this , AddMarks.class);
                startActivity(intent1);
                finish();
            }
        }
        );

        homeworks.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Teacher.this , Homeworks.class);
                startActivity(intent1);
                finish();
            }
        }
        );

        exams.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent1 = new Intent(Teacher.this , Exams.class);
                startActivity(intent1);
                finish();
            }
        }
        );




    }

    public Teacher(int _id, String _name,String _pass,String _class)
    {
        this.Class_name = _class;
        this.id = _id;
        this.teacher_name = _name;
        this.teacher_password = _pass;
    }
    public int getId()
    {
        return id;
    }
    public String getTeacher_name()
    {
        return this.teacher_name;
    }
    public String getTeacher_password()
    {
        return  this.teacher_password;
    }
    public String getClass_name()
    {
        return this.Class_name;
    }
    public void setId(int _id)
    {
        id = _id;
    }
    public void setTeacher_name(String _name)
    {
        this.teacher_name = _name;
    }
    public void setTeacher_password(String _pass)
    {
        this.teacher_password = _pass;
    }
    public void setClass_name(String _class)
    {
        this.Class_name = _class;

    }
}
