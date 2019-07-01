package com.example.smsbaru;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Lpaket extends AppCompatActivity {

    ListView listView;
    Paketadapter adapter;
    DatabaseHandler dbhelper;
    ArrayList<String> records;
    private FloatingActionButton fab;
    int vr;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpaket);

        rl = (RelativeLayout) findViewById(R.id.tambah);

        vr = rl.getVisibility();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (vr){
                    case 0:
                        rl.setVisibility(View.INVISIBLE);
                        break;
                    case 8:
                        rl.setVisibility(View.VISIBLE);
                        break;
                    default:
                        Log.d("Visibility :", "Error");
                        break;
                }
            }
        });

        listView =(ListView) findViewById(R.id.laporan_paket);
        records = new ArrayList<String>();
        adapter = new Paketadapter(this, R.layout.list_paket, R.id.id, records);
        dbhelper =new DatabaseHandler(this);
        listView.setAdapter(adapter);

        readData();
    }
    public void readData(){
        SQLiteDatabase database = dbhelper.getReadableDatabase();

        String sql = "SELECT * FROM tbl_sms WHERE jenis ='Paket Data' ORDER BY id DESC";

        Cursor c = database.rawQuery(sql, null);
        String nomor, op, nominal,  waktu, status, keterangan;
        int id;
        if (c.getCount()>0)
            while (c.moveToNext()){
                id = c.getInt(c.getColumnIndex("id"));
                nomor = c.getString(c.getColumnIndex("no_tujuan"));
                op = c.getString(c.getColumnIndex("op"));
                nominal = c.getString(c.getColumnIndex("nominal"));
                status = c.getString(c.getColumnIndex("status"));
                waktu = c.getString(c.getColumnIndex("waktu"));
                keterangan = c.getString(c.getColumnIndex("keterangan"));

                String item = id+"__"+nomor+"__"+op+"__"+nominal+"__"+status+"__"+waktu+"__"+keterangan;
                records.add(item);
            }
        adapter.notifyDataSetChanged();
    }


    public void actpulsa(View view) {
        Intent intent = new Intent(Lpaket.this ,Home.class);
        startActivity(intent);
        finish();
    }
}
