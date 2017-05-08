package DBHandler;

/**
 * Created by manar on 4/25/2017.
 */

public class Teacher {
    int id;
    String teacher_name;
    String teacher_password;

    public Teacher() {

    }

    public Teacher(int _id, String _name, String _pass) {

        this.id = _id;
        this.teacher_name = _name;
        this.teacher_password = _pass;

    }

    public int getId() {
        return id;
    }

    public String getTeacher_name() {
        return this.teacher_name;
    }

    public String getTeacher_password() {
        return this.teacher_password;
    }

    public void setId(int _id) {
        id = _id;
    }

    public void setTeacher_name(String _name) {
        this.teacher_name = _name;
    }

    public void setTeacher_password(String _pass) {
        this.teacher_password = _pass;
    }

}