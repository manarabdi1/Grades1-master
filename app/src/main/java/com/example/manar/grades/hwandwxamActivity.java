package com.example.manar.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import DBHandler.HWEXAM;
import java.util.ArrayList;
import java.util.List;

import DBHandler.*;

public class hwandwxamActivity extends AppCompatActivity  {

    Spinner spinner, spinnerClass,spinnerSub;
    String item_select, subjectSelect,ClassSelect;
    EditText desc;
    DBHandler db;
    String note="";
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwandwxam);
        String name = SaveSharedPreference.getUserName(hwandwxamActivity.this);
        db= new DBHandler(getApplicationContext());
        spinnerClass = (Spinner) findViewById(R.id.class_name_HW);
        spinnerSub = (Spinner) findViewById(R.id.SUBHW);
        desc = (EditText) findViewById(R.id.SESCHW);
        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> clasess = db.getTeacherClasess(name);

        List<String> iteams =new ArrayList<String>();
        for(int i=0;i<clasess.size();i++)
        {
            if(!iteams.contains(clasess.get(i)))
            iteams.add(clasess.get(i));
        }
        ArrayAdapter<String> Classadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iteams);
        Classadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(Classadapter);

        List<String > subject= db.getTeacherSubject(name);
        ArrayAdapter<String> subadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subject);
        subadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSub.setAdapter(subadapter);

        List<String> categories = new ArrayList<String>();
        categories.add("Home Work");
        categories.add("Exams");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_select = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectSelect = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ClassSelect = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ClassSelect = parent.getSelectedItem().toString();
            }
        });
        save = (Button) findViewById(R.id.SAVEHW);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHWORExam();
                Toast.makeText(hwandwxamActivity.this,"Added",Toast.LENGTH_LONG).show();
            }
        });


    }



    public  void  addHWORExam()
    {

        String _desc="";
        _desc+=subjectSelect+" "+item_select+" "+ desc.getText().toString();
        HWEXAM hw = new HWEXAM(subjectSelect,ClassSelect,_desc);
        db.addNewHWorExam(hw);
    }

}
