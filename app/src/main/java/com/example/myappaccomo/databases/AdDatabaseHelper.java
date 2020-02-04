package com.example.myappaccomo.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myappaccomo.entities.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="ad.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME ="ad";

    private static final String COL_ID ="ID";

    private static final String COL_CATEGORY ="CATEGORY";
    private static final String COL_TYPE ="TYPE";
    private static final String COL_DESCRIPTION="DESCRIPTION";


    private static final String CREATE_TABLE_ST ="CREATE TABLE " + TABLE_NAME + "(" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_CATEGORY + " TEXT, " +
            COL_TYPE + " TEXT, " +
            COL_DESCRIPTION + " TEXT)";


    private static final String DROP_TABLE_ST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String GET_ALL_ST = "SELECT * FROM " + TABLE_NAME;
    //adding more queries
    private static final String GET_LAST_INSERTED_ID = "SELECT SEQ FROM SQLITE_SEQUENCE WHERE NAME = ?";
    private static final String GET_AD_BY_ID = "SELECT " + COL_ID + ", " + COL_CATEGORY + ", " + COL_TYPE + ", " + COL_DESCRIPTION +   " FROM " + TABLE_NAME + " WHERE " + COL_ID + "= ?";







    public AdDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_TABLE_ST);}


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE_ST);
        onCreate(sqLiteDatabase);
    }

    public Long insert(String category, String type, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CATEGORY, category);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

       return result;

    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(GET_ALL_ST, null);

    }


    public List<Ad>getAds(){
        List<Ad> ads = new ArrayList<>();
        Cursor cursor = getAll();

        if(cursor.getCount()>0){
            Ad ad;
            while(cursor.moveToNext()){
                Long id = cursor.getLong(0);
                String category = cursor.getString(1);
                String type = cursor.getString(2);
                String description = cursor.getString(3);

                ad = new Ad(id, category, type, description);
                ads.add(ad);

            }
        }
        cursor.close();
        return ads;
    }

    private Cursor getSequenceCursor(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(GET_LAST_INSERTED_ID, new String[]{tableName});
    }


    public Long getLastInsertedIdInTable(String tableName){
        Long lastId = -999L;
        Cursor cursor = getSequenceCursor(tableName);

        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                lastId = cursor.getLong(0);
            }
        }
        cursor.close();
        return lastId;
    }

    public Ad getAd(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Ad ad = null;
         Cursor cursor = db.rawQuery(GET_AD_BY_ID, new String[]{id.toString()});

         if(cursor.getCount()>0)
             while (cursor.moveToNext()){

                 String category = cursor.getString(1);
                 String type = cursor.getString(2);
                 String description = cursor.getString(3);

                 ad = new Ad(id, category, type, description);
             }
         cursor.close();
             return ad;
    }
}
