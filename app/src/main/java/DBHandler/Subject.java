package DBHandler;

/**
 * Created by manar on 4/25/2017.
 */

public class Subject  {
    String Sub_name;

    String ClassName;
    String TeacherName;


    public Subject()
    {

    }
    public Subject(String teacher,String _subname,String _Class)
    {

        Sub_name = _subname;
        ClassName = _Class;

        TeacherName =teacher;
    }

    public  String getSub_name()
    {
        return Sub_name;
    }

    public String getTeacherName()
    {
        return  TeacherName;
    }
    public  void setTeacherName(String teacher)
    {
        TeacherName = teacher;
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
