package com.example.horizon.myapplication;
public class Subject  {
    int sub_id;
    String Sub_name;
    String note;
    String ClassName;


    public Subject()
    {

    }
    public Subject(int _id,String _subname,String _Class)
    {
        sub_id = _id;
        Sub_name = _subname;
        ClassName = _Class;
    }
    public Subject(int _id,String _subname,String _Class,String _note)
    {
        sub_id = _id;
        Sub_name = _subname;
        note = _note;
        ClassName = _Class;
    }
    public int getId()
    {
        return  sub_id;

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
    public void SetClassName(String _Class)
    {
        ClassName = _Class;
    }
    public  void setId(int _id)
    {
        sub_id = _id;
    }
    public void setSub_name(String _subname)
    {
        Sub_name = _subname;
    }
}