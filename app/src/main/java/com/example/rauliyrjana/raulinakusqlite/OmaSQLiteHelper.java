package com.example.rauliyrjana.raulinakusqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by miksa on 2/6/18.
 */

public class OmaSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_AKUT = "taskukirjat";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NRO = "nro";
    public static final String COLUMN_NIMI = "nimi";
    public static final String COLUMN_HANKINTA = "hankinta";
    public static final String COLUMN_PAINOS = "painos";


    private static final String DATABASE_NAME = "akut.db";
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement

    private static final String DATABASE_CREATE = "create table "
            + TABLE_AKUT + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NRO + " text not null, "
            + COLUMN_NIMI +" text not null, "
            + COLUMN_HANKINTA + " text not null, "
            + COLUMN_PAINOS + " text not null);";


    public OmaSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(OmaSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AKUT);
        onCreate(db);
    }

}