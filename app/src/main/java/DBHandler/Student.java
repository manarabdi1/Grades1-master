package DBHandler;

/**
 * Created by manar on 4/25/2017.
 */

public class Student {
    int id;
    String name;
    String password;
    String Class_name;
    int absent;

    public Student()
    {
                  absent = 0;
                  id = 0;
    }
    public Student(int _id,String _name,String _Class_name,String _password,int _absent){

        id= _id;
        name=_name;
        password=_password;
        absent=_absent;
        Class_name=_Class_name;
    }
    public Student(int _id,String _name,String _password,String _class){

        id= _id;
        name=_name;
        password=_password;
        Class_name =_class;


    }
    public String getClass_name()
    {
        return  Class_name;
    }
    public int getId()
    {
        return id;
    }
    public int getAbsent()
    {
        return absent;
    }
    public String getName()
    {
        return name;
    }
    public String getPassword(){
        return password;
    }
public void setId(int _id)
{
    id = _id;
}
    public void setName(String _name){
        name = _name;
    }
    public void setPassword(String _pass)
    {
        password = _pass;
    }
    public void setAbsent(int _absent)
    {
        absent = _absent;
    }
    public void setClass_name(String _Class_name)
    {
        Class_name=_Class_name;
    }
}
