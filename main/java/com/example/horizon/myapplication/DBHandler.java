package com.example.horizon.myapplication;
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

    // student col names
    private static final String stu_name = "stu_name";
    private static final String stu_password = "stu_password";
    private static final String stu_id = "stu_id";
    private static final String absent = "absent";
    private static final String stu_class = "stu_class";

    // subject col names
    private static final String sub_id = "sub_id";
    private static final String sub_name = "Sub_name";
    private static final String Notes = "Notes";
    private static final String Class_Name_subj = "Class_Name";

    // teacher col names
    private static final String teacher_id = "teacher_id";
    private static final String teacher_name = "teacher_name";
    private static final String teacher_pass = "teacher_pass";
    private static final String Class_name = "class_name";

    //col for marks table
    private static final String stu_id_marks = "stuId";
    private static final String sub_id_marks = "subId";
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
                + teacher_pass + " TEXT,"
                + Class_name + " TEXT " + ")";
        String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "("
                + sub_id + " INTEGER,"
                + sub_name + " TEXT,"
                + Class_Name_subj + " Text,"
                + Notes + " TEXT,"
                + " PRIMARY KEY (" + sub_id + "," + sub_name + "," + Class_Name_subj + ")" + ")";
        String CREATE_TABLE_MARKS = "CREATE TABLE " + TABLE_MARKS + "("
                + stu_id_marks + " INETGER,"
                + sub_id_marks + " INTEGER,"
                + Mark + " INTEGER,"
                + "FOREIGN KEY (" + stu_id_marks + ") REFERENCES " + TABLE_STUDENT + "(" + stu_id + "),"
                + "FOREIGN KEY (" + sub_id_marks + ") REFERENCES " + TABLE_SUBJECT + "(" + sub_id + "),"
                + " PRIMARY KEY (" + stu_id_marks + "," + sub_id_marks + "))";


        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_TEACHER_TABLE);
        db.execSQL(CREATE_SUBJECTS_TABLE);
        db.execSQL(CREATE_TABLE_MARKS);

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
    void addNewSubject(Subject newSub) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(sub_id, newSub.getId());
        values.put(sub_name, newSub.getSub_name());
        values.put(Notes, newSub.getNote());
        values.put(Class_Name_subj, newSub.getClass_Name());
        // Inserting Row
        db.insert(TABLE_SUBJECT, null, values);
        db.close();
    }


    // get allsubject as List
    public List<Subject> getAllSubjecttList() {


        List<Subject> subjectList = new ArrayList<Subject>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT +";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Subject stdnt = new Subject();
                stdnt.setId(Integer.parseInt(cursor.getString(0)));
                stdnt.setSub_name((cursor.getString(1)));
                stdnt.setNote(cursor.getString(2));
                // Adding contact to list
                subjectList.add(stdnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return subjectList;
    }


    // adding new teacher
    void addNewTeacher(Teacher newTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(teacher_name, newTeacher.getTeacher_name());
        values.put(teacher_pass, newTeacher.getTeacher_password());
        values.put(Class_name, newTeacher.getClass_name());


        // Inserting Row
        db.insert(TABLE_TEACHER, null, values);
        db.close();
    }


    //get teacher
    public Teacher getTeacher(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TEACHER + " WHERE "
                + teacher_name + " = '" + name + "' AND " + teacher_pass + " = '" + password +"' ;";


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Teacher td = new Teacher();
        td.setId(c.getInt(c.getColumnIndex(teacher_id)));
        td.setTeacher_name((c.getString(c.getColumnIndex(teacher_name))));
        td.setTeacher_password(c.getString(c.getColumnIndex(teacher_pass)));
        td.setClass_name(c.getString(c.getColumnIndex(Class_name)));

        return td;
    }

    // Adding new Student Information
    void addNewStudent(Student newStud) {

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

    public boolean updateStudentInfo(int updId, String updname, String updpass, String updabsent) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(stu_name, updname);
        args.put(stu_password, updpass);
        args.put(absent, updabsent);

        return db.update(TABLE_STUDENT, args, stu_id + "=" + updId, null) > 0;
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
        td.setAbsent(c.getInt(c.getColumnIndex(absent)));

        return td;
    }

    // get a one student  by id
    public Student getSutdent(int student_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "
                + this.stu_id + " = " + student_id +" ;";


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Student td = new Student();
        td.setId(c.getInt(c.getColumnIndex(stu_id)));
        td.setName((c.getString(c.getColumnIndex(stu_name))));
        td.setPassword(c.getString(c.getColumnIndex(stu_password)));
        td.setAbsent(c.getInt(c.getColumnIndex(absent)));

        return td;
    }


    // get all student
    public List<Student> getAllStudentList() {


        List<Student> studentList = new ArrayList<Student>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT+";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Student stdnt = new Student();
                stdnt.setId(Integer.parseInt(cursor.getString(0)));
                stdnt.setName((cursor.getString(1)));
                stdnt.setPassword(cursor.getString(2));
                stdnt.setClass_name(cursor.getString(3));
                stdnt.setAbsent(Integer.parseInt(cursor.getString(4)));

                // Adding contact to list
                studentList.add(stdnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return studentList;
    }

    // delete Student
    public void deleteStudent(int _id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, stu_id + " = "+_id,null);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


    // add marks
    public  void addNewMarks(AddMarks newMark)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(sub_id_marks, newMark.getStu_id());
        values.put(sub_id_marks, newMark.getSub_id());
        values.put(Mark,newMark.getMark());
        // Inserting Row
        db.insert(TABLE_MARKS, null, values);
        db.close();

    }

    // gets marks
    public List<AddMarks> getAllMarksList(int stuID) {


        List<AddMarks> MarksList = new ArrayList<AddMarks>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MARKS + " WHERE "+stu_id_marks + " = "+stuID+" ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                AddMarks stdnt = new AddMarks();
                stdnt.setStu_id(Integer.parseInt(cursor.getString(0)));
                stdnt.setSub_id(Integer.parseInt(cursor.getString(1)));
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
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "+stu_name + " = '"+name+"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor!=null)
            return true;
        else
            return  false;
    }
    // check student password
    public boolean isStudentPassword(String pass, String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT + " WHERE "+stu_name + " = '"+name+"'  AND "+stu_password+" = ' "+pass+ "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor!=null)
            return true;
        else
            return  false;
    }
    //check teacher name
    public boolean isTeacher(String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_TEACHER + " WHERE "+teacher_name + " = '"+name+"' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor!=null)
            return true;
        else
            return  false;

    }
    //check teacher password
    public  boolean isTeacherpassword(String pass,String name)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_TEACHER + " WHERE "+teacher_name + " = '"+name+"'  AND "+teacher_pass+" = '"+pass+ "' ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor!=null)
            return true;
        else
            return  false;
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

}