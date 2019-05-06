package com.example.pro226design.data_fetch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Likedata extends SQLiteOpenHelper {
    public static final String Database_name="like.db";
    public static final String Table_name="likeData";


    public Likedata( Context context ) {
        super(context,Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,ARTITLE TEXT ,ARTURL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Table_name);
        onCreate(sqLiteDatabase);

    }

    //inserting data

    public boolean insertData(String artitle,String arturl){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ARTITLE",artitle);
        cv.put("ARTURL",artitle);
       long i= db.insert(Table_name,null,cv);
       if(i==-1){return false;
       }else{
           return true;
       }


    }

    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_name,null);
        return res;
    }

    public Integer deletData(String arturl11){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_name,"ARTITLE=?",new String[]{arturl11});
    }
}
