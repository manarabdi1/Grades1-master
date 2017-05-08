package com.example.manar.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import  DBHandler.*;
public class showTeachers extends AppCompatActivity {
 DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teachers);
        db = new  DBHandler(getApplicationContext());
        ListView LV = (ListView) findViewById(R.id.teachresLV);

        List<String> teach = db.getALLTeachres();
        ArrayAdapter adapter =new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txtitem,teach);
        LV.setAdapter(adapter);


    }
}
