package com.example.saveyourplants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_USER="user";
    public static final String COL_ID="ID";
    public static final String COL_USERNAME="username";
    public static final String COL_PASSWORD="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String username, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_USERNAME,username);
        contentValues.put(COL_PASSWORD,password);
        long res=db.insert("user",null,contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password){
        String[] columns={ COL_ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection=COL_USERNAME +"=?"+" and "+COL_PASSWORD+"=?";
        String[] selectionArgs = {username,password};
        Cursor cursor=db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        db.close();

        return count>0;
    }
}
