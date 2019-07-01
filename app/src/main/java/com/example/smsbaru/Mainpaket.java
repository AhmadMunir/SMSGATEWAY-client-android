package com.example.smsbaru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Mainpaket extends AppCompatActivity {

    public Button kirim;
    public EditText to;
    Spinner message, opt;
    String nomor, pesan, op, msg, produk;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpaket);

        to = findViewById(R.id.to);
        message = findViewById(R.id.message);
        opt = findViewById(R.id.op);

        kirim = findViewById(R.id.send);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimsms();
            }
        });
    }

    public void kirimsms(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal =  dateFormat.format(calendar.getTime());

        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        String jam = date.format(d.getTime());

        String waktu = tanggal + " " +jam;

        nomor = to.getText().toString();
        pesan = message.getSelectedItem().toString();
        op = opt.getSelectedItem().toString();
        msg = "Selamat Pengisian Paket Data "+op+" Sebesar "+pesan+" Berhasil";


        try{
            URI server = new URI("ws://192.168.43.1:6868");

            SmsGatewayClient client = new SmsGatewayClient(server);
            // menyalakan koneksi
            client.connectBlocking();
            // membuat Json message
            JsonObject object = new JsonObject();
            object.add("to", new JsonPrimitive(nomor));
            object.add("message", new JsonPrimitive(msg));


            SmsModels sms = new SmsModels(nomor, op, pesan,"status", waktu, "keterangan", "Paket Data");
            db.addRecord(sms);

            // mengkonversi ke JSON String
            Gson gson = new Gson();
            String json = gson.toJson(object);

            // mengirim pesan ke server
            client.send(json);

            // memutuskan koneksi
            client.closeBlocking();
        } catch (URISyntaxException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Mainpaket.this, Home.class);
        startActivity(intent);
        finish();
    }
}
