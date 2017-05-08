package DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manar on 4/25/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "grades";
    // tables names
    private static final String TABLE_STUDENT = "student";
    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_TEACHER = "teacher";
    private static final String TABLE_MARKS = "marks";
    private  static  final String TABLE_HW= "HOMWWORK";
// homework table col
    private  static  final  String subName_HW="subname";
    private  static  final  String ClassName_HW = "classname";
    private static final  String HWEXAM = "hwExam";
    // student col names
    private static final String stu_name = "stu_name";
    private static final String stu_password = "stu_password";
    private static final String stu_id = "stu_id";
    private static final String absent = "absent";
    private static final String stu_class = "stu_class";

    // subject col names
   // private static final String sub_id = "sub_id";
    private  static  final  String teacherNAme="teacherName";
    private static final String sub_name = "Sub_name";
    private static final String Class_Name_subj = "Class_Name";

    // teacher col names
    private static final String teacher_id = "teacher_id";
    private static final String teacher_name = "teacher_name";
    private static final String teacher_pass = "teacher_pass";


    //col for marks table
    private static final String stu_name_marks = "stuId";
    private static final String sub_name_marks = "subId";
    private static final String Mark = "mark";
    // teacher subjects col

    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                + stu_id + " INTEGER PRIMARY KEY,"
                + stu_name + " TEXT UNIQUE,"
                + stu_password + " TEXT,"
                + stu_class + " TEXT,"
                + absent + " INTEGER " + ")";
        String CREATE_TEACHER_TABLE = "CREATE TABLE " + TABLE_TEACHER + "("
                + teacher_id + " INTEGER PRIMARY KEY,"
                + teacher_name + " TEXT UNIQUE,"
                + teacher_pass + " TEXT "+")";

        String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "("
                +teacherNAme +" TEXT,"
                + sub_name + " TEXT,"
                + Class_Name_subj + " TEXT,"
                +"FOREIGN KEY (" +teacherNAme+ ") REFERENCES " + TABLE_TEACHER + "(" + teacher_name + "),"
                + " PRIMARY KEY ("+ sub_name +","+ Class_Name_subj + ")" + ")";
        String CREATE_TABLE_MARKS = "CREATE TABLE " + TABLE_MARKS + "("
                + stu_name_marks + " INETGER,"
                + sub_name_marks + " TEXT,"
                + Mark + " INTEGER,"
                + "FOREIGN KEY (" + stu_name_marks + ") REFERENCES " + TABLE_STUDENT + "(" + stu_name + "),"
                + "FOREIGN KEY (" + sub_name_marks + ") REFERENCES " + TABLE_SUBJECT + "(" + sub_name + "),"
                + " PRIMARY KEY (" + stu_name_marks + "," + sub_name_marks + "))";

        String CTEATE_TABLE_HW = "CREATE TABLE "+TABLE_HW +"("
                +subName_HW +" TEXT,"
                +ClassName_HW+" TEXT,"
                +HWEXAM +" TEXT "+")";
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_TEACHER_TABLE);
        db.execSQL(CREATE_SUBJECTS_TABLE);
        db.execSQL(CREATE_TABLE_MARKS);
        db.execSQL(CTEATE_TABLE_HW);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        // Create tables again
        onCreate(db);
    }

    // adding new subject
 public    void addNewSubject(Subject newSub) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(sub_name, newSub.getSub_name());
        values.put(teacherNAme,newSub.getTeacherName());
        values.put(Class_Name_subj, newSub.getClass_Name());

        // Inserting Row

        db.insert(TABLE_SUBJECT, null, values);
        db.close();
    }


    // get allsubject as List
    public ArrayList<String> getAllSubjectNotes(String _className) {

String selectQuery = "SELECT * FROM "+TABLE_HW +" WHERE "+ ClassName_HW + " = '"+_className +"' ;";
        ArrayList<String> subjectList = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // Adding contact to list
                subjectList.add(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        db.close();

        // return contact list
        return subjectList;
    }

public List<String> getALLTeachres()
{
    String selectQuery = "SELECT * FROM "+TABLE_TEACHER +";";
    List<String> teach = new ArrayList<String>();
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
    if(cursor.moveToFirst())
        do {

            teach.add(cursor.getString(1).toString());
        }while (cursor.moveToNext());
    return  teach;

}
    // adding new teacher
   public void addNewTeacher(Teacher newTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(teacher_id,newTeacher.getId());
        values.put(teacher_name, newTeacher.getTeacher_name());
        values.put(teacher_pass, newTeacher.getTeacher_password());


        // Inserting Row
        db.insert(TABLE_TEACHER, null, values);
        db.close();
    }



    // Adding new Student Information
    public void addNewStudent(Student newStud) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(stu_id, newStud.getId());
        values.put(stu_name, newStud.getName());
        values.put(stu_password, newStud.getPassword());
        values.put(stu_class, newStud.getClass_name());
        values.put(absent, newStud.getAbsent());


        // Inserting Row
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }




    // get student by name
    public Student getStudentByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "
                + stu_name + " = '" + name +"' ; ";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Student td = new Student();
        td.setId(c.getInt(c.getColumnIndex(stu_id)));
        td.setName((c.getString(c.getColumnIndex(stu_name))));
        td.setPassword(c.getString(c.getColumnIndex(stu_password)));
        td.setClass_name(c.getString(c.getColumnIndex(stu_class)));
        td.setAbsent(c.getInt(c.getColumnIndex(absent)));
        db.close();
        return td;
    }




// get student in the same class and take same subject
    public List<String>  getStudentIntheSameClass(String _classname )
    {
        List<String> studentList =new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT +" WHERE "+stu_class+" = '"+_classname +"' ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                studentList.add(cursor.getString(1));


            } while (cursor.moveToNext());
        }
  db.close();
        // return contact list
        return studentList;

    }

// get all student names
public List<String> getAllStudentNames() {


    List<String> studentList = new ArrayList<String>();

    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_STUDENT +" ;";
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {

            String stdnt =cursor.getString(1).toString();
            studentList.add(stdnt);

        } while (cursor.moveToNext());
    }
    // return contact list
    return studentList;
}







// add marks
    public  void addNewMarks(Marks newMark)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(stu_name_marks, newMark.getStu_name());
        values.put(sub_name_marks, newMark.getSub_name());
        values.put(Mark,newMark.getMark());
        // Inserting Row
        db.insert(TABLE_MARKS, null, values);
        db.close();

    }

    // gets marks
    public List<Marks> getAllMarksList(String name) {


        List<Marks> MarksList = new ArrayList<Marks>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MARKS + " WHERE "+stu_name_marks + " = '"+name+"' ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Marks stdnt = new Marks();
                stdnt.setStu_name(cursor.getString(0));
                stdnt.setSub_name(cursor.getString(1));
                stdnt.setMark(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                MarksList.add(stdnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return MarksList;
    }
// sheck student name
  public  boolean isStudent(String name)
    {
        String selectQuery = "SELECT " +stu_name + " FROM " + TABLE_STUDENT + " WHERE "+stu_name + " = '"+name+"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount()<=0)
            return false;
            else
            return  true;
    }
    // check student password
    public boolean isStudentPassword(String pass, String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "+stu_name + " = '"+name+"'  AND "+stu_password+" = '"+pass+ "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() <=0 ) {
            return false;
        }
        else
            return  true;
    }
    //check teacher name
    public boolean isTeacher(String name)
    {
        String selectQuery = "SELECT  "+teacher_name+" FROM " + TABLE_TEACHER + " WHERE "+teacher_name + " = '"+name+"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() <=0)
            return false;
        else
            return  true;

    }
    // check teacher id
    public boolean isTeacherID(int id)
    {
        String selectQuery = "SELECT "+teacher_id +" FROM " + TABLE_TEACHER + " WHERE "+teacher_id + " = "+id+" ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() <= 0)
            return false;
        else
            return  true;
    }
    //check teacher password
    public  boolean isTeacherpassword(String pass,String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_TEACHER + " WHERE "+teacher_name + " = '"+name+"'  AND "+teacher_pass+" = '"+pass+ "' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() <=0)
            return false;
        else
            return  true;
    }

    // get Teacher password
    public String getTeacherPassword(String name)
    {
        String pass="";
        String selectQuery =" SELECT * FROM "+TABLE_TEACHER +" WHERE "+teacher_name +" = '" +name +"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            pass=cursor.getString(2);


        return  pass;
    }
    // get student password
    public String getStudentPassword(String name)
    {
        String pass="";
        String selectQuery =" SELECT * FROM "+TABLE_STUDENT +" WHERE "+stu_name +" = '" +name +"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            pass=cursor.getString(2);


        return  pass;
    }
    //check for subjet if exist
     public boolean isSubjectExist(String subname ,String subClass)
     {
         String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + " WHERE "
          +sub_name+" = '"+subname+ "'  AND " +Class_Name_subj + " = '"+subClass+"' ;";
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);

         if(cursor.getCount() <=0)
             return false;
         else
             return  true;
     }
    // check student id
    public boolean isStudentId(int id)
    {
        String selectQuery = "SELECT  "+stu_id+" FROM " + TABLE_STUDENT+ " WHERE "+stu_id + " = "+id+" ; ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() <=0)
            return false;
        else
            return  true;
    }
    //add attendance
    public void addAtendance(String name) {
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE " + stu_name + " = '" + name + "' ; ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ContentValues values = new ContentValues();

        if (cursor.moveToFirst()) {
            int absen = cursor.getInt(4) ;
            absen +=1;
            values.put(absent, absen);
            db.update(TABLE_STUDENT, values,stu_name+" = '"+name+"'", null);

        }
    }
    public void UpdateMarks(Marks marks)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_MARKS + " WHERE " + stu_name_marks + " = '" + marks.getStu_name() + "'" +
                " AND "+sub_name_marks+" = '"+marks.getSub_name()+"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ContentValues values = new ContentValues();

        if (cursor.moveToFirst()) {
            values.put(Mark, marks.getMark());
            db.update(TABLE_MARKS, values,stu_name_marks+" = '"+marks.getStu_name()+"' AND "+sub_name_marks+" = '" +marks.getSub_name()+"'" , null);

        }
    }


    //get Number of attenednces for a student

    public int getNumberOfAbsent(String name)
    {
        int num=0;
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE " + stu_name + " = '" + name + "' ; ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            num = cursor.getInt(4);
        }
        return  num;
    }


    //remove student absent

    public void removeAbsent(String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE " + stu_name + " = '" + name + "' ; ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ContentValues values = new ContentValues();

        if (cursor.moveToFirst()) {
            int absen = cursor.getInt(4) ;
            if(absen>0)
            absen -=1;
            values.put(absent, absen);
            db.update(TABLE_STUDENT, values,stu_name+" = '"+name+"'", null);

        }

    }
    //add home work or exam
    public  void addNewHWorExam(HWEXAM HW)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(subName_HW, HW.getSub());
        values.put(ClassName_HW, HW.getClassName());
        values.put(HWEXAM,HW.getHW());
        // Inserting Row
        db.insert(TABLE_HW, null, values);
        db.close();

    }
     public List<String> getTeacherClasess(String name)
     {
         List<String> classes = new ArrayList<String>();
         String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + " WHERE " + teacherNAme +
                 " = '" + name + "' ; ";

         SQLiteDatabase db = this.getWritableDatabase();

         Cursor cursor = db.rawQuery(selectQuery, null);
         if (cursor.moveToFirst())
             do{
                 classes.add(cursor.getString(2));

             }while(cursor.moveToNext());
         return  classes;
     }
    public List<String> getTeacherSubject(String name)
    {
        List<String> classes = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT + " WHERE " + teacherNAme +
                " = '" + name + "' ; ";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
            do{
                classes.add(cursor.getString(1));

            }while(cursor.moveToNext());
        return  classes;
    }
   /* boolean isTeachThisSub(String name, String sub)
    {
        String selectQuery = "SELECT * FROM " + TABLE_SUBJECT+ " WHERE "+teacher_name + " = '"+name+"' ; ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

      if ((cursor.moveToFirst()))
          if(cursor.getString(cursor.getColumnIndex(teacher_Subject)).equals(sub))
              return  true;
          else
              return  false;

     return  false;
    }*/

}
