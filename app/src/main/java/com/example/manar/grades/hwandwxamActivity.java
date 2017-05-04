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

import java.util.ArrayList;
import java.util.List;

public class hwandwxamActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String item_select;
    EditText class_name , sub ,desc;
    DBHandler db;
    String note="";
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwandwxam);

        db= new DBHandler(getApplicationContext());
        class_name = (EditText) findViewById(R.id.class_name_HW) ;
        sub = (EditText) findViewById(R.id.SUBHW);
        desc = (EditText) findViewById(R.id.SESCHW);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categories = new ArrayList<String>();
        categories.add("Home Work");
        categories.add("Exams");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
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
        String _class = class_name.getText().toString();
        String _sub = sub.getText().toString();
        String _desc =  desc.getText().toString();
        note +=_sub+" " +item_select+" "+_desc ;
        db.addNewHWorExam(_sub , _class ,note);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      item_select = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
