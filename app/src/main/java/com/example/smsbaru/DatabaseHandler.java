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

    // Database isi_sms
    private static final String DATABASE_NAME = "SmsGateway";

    // table isi_sms
    private static final String TABLE_NAME = "tbl_sms";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NOTUJUAN = "no_tujuan";
    private static final String KEY_ISISMS = "isi_sms";
    private static final String KEY_STATUS = "status";
    private static final String KEY_WAKTU = "waktu";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOTUJUAN + " TEXT,"
                + KEY_ISISMS + " TEXT,"+ KEY_STATUS + " TEXT," + KEY_WAKTU + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(SmsModels smsModels){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTUJUAN, smsModels.getIsi_sms());
        values.put(KEY_ISISMS, smsModels.getNo_tujuan());
        values.put(KEY_STATUS, smsModels.getStatus());
        values.put(KEY_WAKTU, smsModels.getWaktu());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public SmsModels getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_NOTUJUAN, KEY_ISISMS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SmsModels contact = new SmsModels(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        // return contact
        return contact;
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

                smsModels.setIsi_sms(cursor.getString(1));
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
        values.put(KEY_NOTUJUAN, contact.getIsi_sms());
        values.put(KEY_ISISMS, contact.getNo_tujuan());

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