package com.example.manar.grades;

/**
 * Created by manar on 4/25/2017.
 */

public class Teacher {
     int id;
     String teacher_name;
    String teacher_password;
    String Class_name;
    String subject;
    public Teacher()
    {

    }
    public Teacher(int _id, String _name,String _pass,String _class,String _sub )
    {
        this.Class_name = _class;
        this.id = _id;
        this.teacher_name = _name;
        this.teacher_password = _pass;
        subject = _sub;
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
    public void setSubject(String _sub)
    {
        subject = _sub;
    }
    public  String getSubject()
    {
        return  subject;
    }
}
