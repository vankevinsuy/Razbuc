package com.example.razbuc.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RazbucLocalDb extends SQLiteOpenHelper {
//****DATABASE VARIABLES****//

    private static final String DATA_BASE_name = "Razbuc.db";

    private static final String TABLE_user = "user";
    private static final String COL_user_id = "userID";

    //****QUERY FOR CREATING THE TABLES****//
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_user + "(" +
            COL_user_id + " TEXT" + ")" ;


    public RazbucLocalDb(Context context) {
        super(context, DATA_BASE_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_user);

        //create new TABLES
        onCreate(db);
    }

    public boolean isFirstUse(){
        boolean res = true;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_user , null);

        if(cursor.getCount() > 0){
            res = false;
        }
        else {
            return true;
        }

        database.close();
        return res;
    }

    public void saveUserId(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_user_id, id);

        database.insert(TABLE_user, null, values);

        database.close();
    }

    public String getUserId(){
        String res = "";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_user , null);

        if (cursor.getCount() >0){
            while (cursor.moveToNext()){
                res = cursor.getString(0);
            }
        }

        return res;
    }

    public void clearDatabase(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_user);
    }
}
