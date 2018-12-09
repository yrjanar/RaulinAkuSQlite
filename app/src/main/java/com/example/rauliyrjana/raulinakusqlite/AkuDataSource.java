package com.example.rauliyrjana.raulinakusqlite;

import android.content.ContentValues;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AkuDataSource extends ListActivity{

    private SQLiteDatabase database;
    private OmaSQLiteHelper dbHelper;
    private String[] allColumns = {
            OmaSQLiteHelper.COLUMN_ID,
            OmaSQLiteHelper.COLUMN_NIMI,
            OmaSQLiteHelper.COLUMN_NRO,
            OmaSQLiteHelper.COLUMN_HANKINTA,
            OmaSQLiteHelper.COLUMN_PAINOS};

    public AkuDataSource(Context context) {
        dbHelper = new OmaSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Aku createAku(String nro, String nimi, String hankinta, String painos) {
        ContentValues values = new ContentValues();
        values.put(OmaSQLiteHelper.COLUMN_NRO, nro);
        values.put(OmaSQLiteHelper.COLUMN_NIMI, nimi);
        values.put(OmaSQLiteHelper.COLUMN_HANKINTA, hankinta);
        values.put(OmaSQLiteHelper.COLUMN_PAINOS, painos);

        long insertId = database.insert(OmaSQLiteHelper.TABLE_AKUT, null, values);
        Cursor cursor = database.query(OmaSQLiteHelper.TABLE_AKUT,
                allColumns, OmaSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Aku newAku = cursorToAku(cursor);
        cursor.close();
        return newAku;
    }

    public void deleteAku(Aku aku) {
        long id = aku.getId();
        System.out.println("Aku deleted with id: " + id);
        database.delete(OmaSQLiteHelper.TABLE_AKUT, OmaSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void deleteRow(Aku aku) {
        String no = aku.getKirjanNumero();
        System.out.println("Aku deleted with no: " + no);
        database.delete(OmaSQLiteHelper.TABLE_AKUT, OmaSQLiteHelper.COLUMN_NRO
                + " = " + no, null);
    }

    public List<Aku> getAllAku() {
        List<Aku> akut = new ArrayList<Aku>();

        Cursor cursor = database.query(OmaSQLiteHelper.TABLE_AKUT,
                allColumns, null, null, null, null, OmaSQLiteHelper.COLUMN_NRO);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Aku aku = cursorToAku(cursor);
            akut.add(aku);
            cursor.moveToNext();
        }
        cursor.close();
        return akut;
    }

    private Aku cursorToAku(Cursor cursor) {
        Aku aku = new Aku();
        aku.setId(cursor.getLong(0));
        aku.setKirjanNimi(cursor.getString(1));
        aku.setKirjanNumero(cursor.getString(2));
        aku.setHankinta(cursor.getString(3));
        aku.setPainos(cursor.getString(4));
        return aku;
    }
}


