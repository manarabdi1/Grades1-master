package com.example.manar.grades;

/**
 * Created by manar on 4/28/2017.
 */

public class AdminClass {
    private String name;
    private  String password;
    public  AdminClass()
    {

    }
    public  AdminClass(String _name ,String _password)
    {
        name = _name ;
        password = _password;
    }
     public String getName()
     {
         return  name;
     }
    public String getPassword()
    {
        return  password;
    }
}
