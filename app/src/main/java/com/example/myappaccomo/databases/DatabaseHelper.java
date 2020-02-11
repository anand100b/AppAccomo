package com.example.myappaccomo.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myappaccomo.entities.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";



    private static final String COL_USER_ID = "user_id";

    private static final String COL_USER_NAME = "user_name";

    private static final String COL_USER_EMAIL = "user_email";

    private static final String COL_USER_PASSWORD = "user_password";


    //----sql queries for sign up register----
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_NAME + " TEXT,"
            + COL_USER_EMAIL + " TEXT," + COL_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, user.getName());
        values.put(COL_USER_EMAIL, user.getEmail());
//        values.put(COL_USER_PHONE,user.getPhone());
        values.put(COL_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COL_USER_ID,
                COL_USER_EMAIL,
                COL_USER_NAME,
                COL_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COL_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,          //group the rows
                null,       //filter by row groups
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COL_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COL_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COL_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }


    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COL_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COL_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COL_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_USER_EMAIL + " = ?" + " AND " + COL_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}





