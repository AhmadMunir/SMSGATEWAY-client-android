package com.example.smsbaru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database op
    private static final String DATABASE_NAME = "SmsGateway";

    // table op
    private static final String TABLE_NAME = "tbl_sms";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NOTUJUAN = "no_tujuan";
    private static final String KEY_OP = "op";
    private static final String KEY_NOMINAL = "nominal";
    private static final String KEY_STATUS = "status";
    private static final String KEY_WAKTU = "waktu";
    private static final String KEY_KETERANGAN = "keterangan";
    private static final String KEY_JENIS = "jenis";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOTUJUAN + " TEXT,"
                + KEY_OP + " TEXT,"+ KEY_NOMINAL + " TEXT,"+ KEY_STATUS + " TEXT," + KEY_WAKTU + " TEXT,"+ KEY_KETERANGAN +" TEXT, "+ KEY_JENIS +" TEXT "+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(SmsModels smsmodels){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTUJUAN, smsmodels.getNo_tujuan());
        values.put(KEY_OP, smsmodels.getOp());
        values.put(KEY_NOMINAL, smsmodels.getNominal());
        values.put(KEY_STATUS, smsmodels.getStatus());
        values.put(KEY_WAKTU, smsmodels.getWaktu());
        values.put(KEY_KETERANGAN, smsmodels.getKeterangan());
        values.put(KEY_JENIS, smsmodels.getJenis());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    // get All Record
    public List<SmsModels> getAllRecord() {
        List<SmsModels> contactList = new ArrayList<SmsModels>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SmsModels smsModels = new SmsModels();

                smsModels.setOp(cursor.getString(1));
                smsModels.setNo_tujuan(cursor.getString(2));
                smsModels.setStatus(cursor.getString(3));
                smsModels.setWaktu(cursor.getString(4));

                contactList.add(smsModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int updateContact(SmsModels contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTUJUAN, contact.getOp());
        values.put(KEY_OP, contact.getNo_tujuan());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    public void deleteModel(SmsModels contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
}