package com.example.manar.grades;

/**
 * Created by manar on 4/25/2017.
 */

public class Subject  {
    String Sub_name;
    String note;
    String ClassName;


    public Subject()
    {

    }
    public Subject(String _subname,String _Class)
    {

        Sub_name = _subname;
        ClassName = _Class;
        note ="";
    }
    public Subject(String _subname,String _Class,String _note)
    {

         Sub_name = _subname;
        note = _note;
        ClassName = _Class;
    }
    public  String getSub_name()
    {
        return Sub_name;
    }
    public String getNote()
    {
        return  note;
    }
    public void setNote(String _note)
    {
        note = _note;
    }
    public  String getClass_Name()
    {
        return ClassName;
    }
    public void setClass_Name(String _Class)
    {
        ClassName = _Class;
    }

    public void setSub_name(String _subname)
    {
        Sub_name = _subname;
    }
}
