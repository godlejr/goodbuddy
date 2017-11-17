package com.demand.goodbuddy.receiver.pedometer.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ㅇㅇ on 2017-05-08.
 */

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public SQLiteOpenHelper(Context context) {
        super(context, "pedometer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "create table pedometer (id integer primary key autoincrement, count integer);";
        String countInitSql = "insert into pedometer (count) values (0)";

        Cursor cursor = db.rawQuery("SELECT pedometer FROM sqlite_master WHERE type='table", null);
        if(cursor.moveToFirst()){
            for(;;) {
                if(!cursor.moveToNext()){
                    db.execSQL(createTableSql);
                    db.execSQL(countInitSql);
                    break;
                }
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
