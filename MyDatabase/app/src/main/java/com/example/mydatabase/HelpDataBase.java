package com.example.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelpDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "result_sheet";
    public static final String COL = "ID";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "SureName";
    public static final String COL_4 = "Marks";

    public HelpDataBase(Context context) {
        super(context, DATABASE_NAME ,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name,FirstName,SureName,Marks )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String name,String firstname,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(COL_1,name);
        contentvalue.put(COL_2,firstname);
        contentvalue.put(COL_3,surname);
        contentvalue.put(COL_4,marks);
        long insert = db.insert(TABLE_NAME, null, contentvalue);
        if (insert == -1)
            return false;
        else
            return true;
    }
    // read data from database
    public Cursor getalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME,null);
        return res;
    }
    public boolean update(String id,String name,String firstname,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(COL,id);
        contentvalue.put(COL_1,name);
        contentvalue.put(COL_2,firstname);
        contentvalue.put(COL_3,surname);
        contentvalue.put(COL_4,marks);
        db.update(TABLE_NAME,contentvalue,"ID",new String[] {id});
        return true;
    }
}
