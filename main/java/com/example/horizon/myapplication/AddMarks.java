package com.example.horizon.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class AddMarks extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String[] Candy = {"Suha Awartani","Manar Abadi","Ni3ma Mohammad","Haneen Salameh","SHAWERMA","Ali ahmad","Xayna Khaled","Rasha Rizq","Knafeh","Tabbuleh","سوسو الأمورة","Fatteh","Chocolate","Kit Kat","Saja Ahmad"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getListView().getContext() , android.R.layout.simple_list_item_1 , Candy);
        getListView().setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }

    int stu_id ;
    int sub_id ;
    int mark;
    public AddMarks()
    {

    }
    public AddMarks (int _stuid,int _sub_id , int _mark)
    {
        stu_id = _stuid;
        sub_id = _sub_id;
        mark = _mark;
    }
    public int getStu_id()
    {
        return  stu_id;
    }
    public int getSub_id()
    {
        return  sub_id;
    }
    public int getMark()
    {
        return  mark;
    }
    public void setStu_id(int _stuid)
    {

    }
    public void setSub_id(int _subid)
    {
        sub_id = _subid;
    }
    public void setMark( int _mark)
    {
        mark = _mark;
    }
}




