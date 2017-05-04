package com.example.manar.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class showmarksActivity extends AppCompatActivity {
 DBHandler db;
    Button back;
    String name;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmarks);
        // declare elements
        db = new DBHandler( getApplicationContext());
        name = SaveSharedPreference.getUserName(showmarksActivity.this);

        //prepared LV
        ListView LV = (ListView) findViewById(R.id.showMarksLV);
         adapter= new ArrayAdapter<String>(this,R.layout.rowshowmarks,R.id.marksTextView);
        LV.setAdapter(adapter);


        //  call function to but marks in the LV
        DisplayMarks();

        //go back to stuent informations
        back = (Button ) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showmarksActivity.this, Studentinfoactivity.class);
                startActivity(intent);
            }
        });

    }
    public  void  DisplayMarks()
    {
        List<Marks> studMark = db.getAllMarksList(name.toString());
        for(int i=0;i<studMark.size();i++)
        {
            Marks m = studMark.get(i);
            adapter.add(m.getSub_name().toString()+"                     "+m.getMark());
        }
    }
}
