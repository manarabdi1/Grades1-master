package com.example.manar.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DBHandler.DBHandler;

public class attendaceActivity extends AppCompatActivity {
    ArrayList<String> selectedItems;
    DBHandler db;
    Button removeabsent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendace);
        selectedItems=new ArrayList<String>();
        db = new DBHandler( getApplicationContext());
        removeabsent = (Button) findViewById(R.id.removabsentbt);
        removeabsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAbsent();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStart(){
        super.onStart();
        ListView LV=(ListView) findViewById(R.id.checkable_list);
        List<String> studentlist= db.getAllStudentNames();
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title);
        if(studentlist!=null)
            for (int i=0 ;i <studentlist.size();i++)
                aa.add(studentlist.get(i));
        LV.setAdapter(aa);
        LV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items

            }

        });



    }
    public void onStop(){
        super.onStop();

    }

    public void showSelectedItems(View view){
        for(String item:selectedItems){
          db.addAtendance(item);
        }
        Toast.makeText(this, " Done ", Toast.LENGTH_LONG).show();
    }

    public void  removeAbsent()
    {

        for(String item:selectedItems){
            db.removeAbsent(item);
        }
        Toast.makeText(this, " Done ", Toast.LENGTH_LONG).show();
    }

}
