package com.example.crud_operation_image_uplode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database_helper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "student.db";
    private static int DATABASE_VERSION = 1;
    public database_helper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ct = "CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , age INTEGER , country TEXT , gender TEXT , hobby TEXT , date TEXT)";
        db.execSQL(ct);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");

    }
    public void AddStudent(Student s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",s.getName());
        values.put("age",s.getAge());
        values.put("country",s.getCountry());
        values.put("gender",s.getGender());
        values.put("hobby",s.getHobby());
        values.put("date",s.getDate());

        db.insert("student",null,values);
    }
    public Cursor getStudent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id , name,age,country,gender,hobby,date FROM student",null);
        return cursor;
    }
    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int row = db.delete("student","id = ?",new String[]{String.valueOf(id)});
        return row;
    }
    public Cursor selectOneData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE id = ?",new String[]{String.valueOf(id)});
        return cursor;
    }
    public int updateStudent(Student s , int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",s.getName());
        values.put("age",s.getAge());
        values.put("country",s.getCountry());
        values.put("gender",s.getGender());
        values.put("hobby",s.getHobby());
        values.put("date",s.getDate());
        int row = db.update("student",values,"id = ?",new String[]{String.valueOf(id)});
        return row;
    }
}
