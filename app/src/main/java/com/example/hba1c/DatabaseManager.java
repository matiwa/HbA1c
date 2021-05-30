package com.example.hba1c;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    public DatabaseManager(Context context){
        super(context,"testes.db",null,1);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table tests(" +
                        "nr integer primary key autoincrement," +
                        "date text," +
                        "time text,"+
                        "mgdL text," +
                        "mmolL text);"
        );

        for(int i=0;i<540;i++) {
            if(i%6==0) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','1','0','0');");
            else if(i%6==1) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','2','0','0');");
            else if(i%6==2) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','3','0','0');");
            else if(i%6==3) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','4','0','0');");
            else if(i%6==4) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','5','0','0');");
            else if(i%6==5) db.execSQL("insert into tests (date,time,mgdL,mmolL) " +
                    "values('0000-00-00','6','0','0');");
        }
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS tests");
        onCreate(db);
    }

    //INCOMES
    public void addTest(String date,String time,String mgdL,String mmolL){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("date",date);
        values.put("time",time);
        values.put("mgdL",mgdL);
        values.put("mmolL",mmolL);
        db.insertOrThrow("tests",null,values);
    }

    public void deleteTest(int id){
        SQLiteDatabase db=getWritableDatabase();
        String[]arguments={""+id};
        db.delete("tests","nr=?",arguments);
    }

    public void updateTest(int nr,String date,/*String time,*/String mgdL,String mmolL){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("date",date);
        //values.put("time",time);
        values.put("mgdL",mgdL);
        values.put("mmolL",mmolL);
        String args[]={nr+""};
        db.update("tests",values,"nr=?",args);
    }

    public Cursor giveAllTheTests(){
        String[]columns={"nr","date","time","mgdL","mmolL"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query("tests",columns,null,null,null,null,null);
        return cursor;
    }

    public Test giveTest(int nr){
        Test test =new Test();
        SQLiteDatabase db=getReadableDatabase();
        String[]columns={"nr","date","time","mgdL","mmolL"};
        String args[]={nr+""};
        Cursor cursor=db.query("tests",columns,"nr=?",args,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            test.setNr(cursor.getLong(0));
            test.setDate2(cursor.getString(1));
            test.setTime2(cursor.getString(2));
            test.setMgdL(cursor.getString(3));
            test.setMmolL(cursor.getString(4));
        }
        return test;
    }
    //End Tests
}