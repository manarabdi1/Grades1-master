package DBHandler;

/**
 * Created by manar on 4/26/2017.
 */

public class Marks {
    String stu_name ;
    String sub_name;
    int mark;
     public Marks()
     {

     }
    public Marks (String _stuname,String _sub_name , int _mark)
    {
        stu_name = _stuname;
        sub_name = _sub_name;
        mark = _mark;
    }
    public String getStu_name()
    {
        return  stu_name;
    }
    public String getSub_name()
    {
        return  sub_name;
    }
    public int getMark()
    {
        return  mark;
    }
    public void setStu_name(String _stuname)
    {
             stu_name = _stuname;
    }
    public void setSub_name(String _subname)
    {
        sub_name = _subname;
    }
    public void setMark( int _mark)
    {
        mark = _mark;
    }
}
