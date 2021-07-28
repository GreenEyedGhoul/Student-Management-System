package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;


    private static final String Database_name="student.db";
    private static final String Table_name="tblstudent";
    private static final String Col_id="Id";
    private static final String Col_1="Name";
    private static final String Col_2="Roll_number";
    private static final String Col_3="Phone";
    private static final String Col_4="Address";
    DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="create table "+Table_name+" ("+Col_id+"  INTEGER PRIMARY KEY AUTOINCREMENT,"+Col_1+" TEXT, "+Col_2+" TEXT,"+Col_3+" TEXT,"+Col_4+" TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +Table_name);
        onCreate(db);

    }
    void addstudent(String name, String Roll_number, String Phone, String Address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Col_1, name);
        cv.put(Col_2, Roll_number);
        cv.put(Col_3, Phone);
        cv.put(Col_4, Address);
        db.insert(Table_name,null,cv);

    }
    Cursor readalldata()
    {
        String query ="SELECT * FROM "+Table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db!=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updatedata(String row_id, String name , String Roll_number, String Phone, String Address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_1,name);
        cv.put(Col_2,Roll_number);
        cv.put(Col_3,Phone);
        cv.put(Col_4,Address);
        db.update(Table_name, cv, "Id=?", new String[]{row_id});

    }
    void delete(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_name,"Id=?", new String[]{row_id});
    }
    void deleteAll()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM "+Table_name);
    }
}
