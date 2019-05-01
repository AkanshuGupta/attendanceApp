package com.example.mrjava.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.AccessController;

import static java.security.AccessController.getContext;

/**
 * Created by Mr. JAVA on 1/31/2018.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "attendance.db",null, 15);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table className (cname text)");
        sqLiteDatabase.execSQL("create table student (srno text,sname text,smob text,saddr text,att INTEGER DEFAULT 1,totatt INTEGER DEFAULT 1,PRIMARY KEY(sname,saddr))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists className");
        sqLiteDatabase.execSQL("drop table if exists student");
        onCreate(sqLiteDatabase);
    }
    public boolean classinsert(String clsname){
        SQLiteDatabase cdb=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("cname",clsname);
        long result=cdb.insert("className",null,values);
        if(result==-1){
            return false;
        } else{
            return true;
        }
    }
    public boolean insertStudentData(String sno,String stname,String stmob,String staddr){
        SQLiteDatabase sdb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("srno",sno);
        contentValues.put("sname",stname);
        contentValues.put("smob",stmob);
        contentValues.put("saddr",staddr);
        contentValues.put("smob",stmob);
        contentValues.put("saddr",staddr);
        long result=sdb.insert("student",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public void DeleteData(String clasName){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("className","cname ='"+clasName+"'",null);
    }
    public void StudentDelete(String srnum){
        SQLiteDatabase dbb=this.getWritableDatabase();
        dbb.delete("student","sname ='"+srnum+"'",null);
    }
    public void updat(String num,String rooln){
        SQLiteDatabase ddb=this.getWritableDatabase();
        String valueToIncrementBy = "1";
        String productId = num;
        String rnum=rooln;
        String[] bindingArgs = new String[]{ valueToIncrementBy, productId, rnum };
        ddb.execSQL("update student set att = att + ? where saddr = ? and sname = ?",bindingArgs);
        ddb.close();
    }
    public void updatDecrement(String num,String rooln){
        SQLiteDatabase db=this.getWritableDatabase();
        String valueToDecrementBy = "1";
        String productId = num;
        String rnum=rooln;
        String[] bindingArgs = new String[]{ valueToDecrementBy, productId, rnum };
        db.execSQL("update student set att = att - ? where saddr = ? and sname = ?",bindingArgs);
    }
    public void updattotal(String num){
        SQLiteDatabase ddb=this.getWritableDatabase();
        String valueToIncrementBy = "1";
        String productId = num;
        String[] bindingArgs = new String[]{ valueToIncrementBy, productId, };
        ddb.execSQL("update student set totatt =totatt + ? where saddr = ?",bindingArgs);
    }
}
