package DBHandler;

/**
 * Created by manar on 5/8/2017.
 */

public class HWEXAM {
    String sub , ClassName, HW;
    public  HWEXAM()
    {

    }
    public  HWEXAM(String _sub ,String _class , String _HW)
    {
        sub = _sub;
        ClassName = _class;
        HW = _HW;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }


    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setHW(String HW) {
        this.HW = HW;
    }

    public String getHW() {
        return HW;
    }
}
