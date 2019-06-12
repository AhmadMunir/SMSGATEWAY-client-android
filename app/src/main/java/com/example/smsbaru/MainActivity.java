package com.example.smsbaru;

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

public class MainActivity extends AppCompatActivity {

    public Button kirim;
    public EditText to;
    Spinner message;
    String nomor, pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to = findViewById(R.id.to);
        message = findViewById(R.id.message);

        kirim = findViewById(R.id.send);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimsms();
            }
        });

    }


    public void kirimsms(){

        nomor = to.getText().toString();
        pesan = message.getSelectedItem().toString();



        try{
            URI server = new URI("ws://192.168.43.1:6868");


        SmsGatewayClient client = new SmsGatewayClient(server);
        // menyalakan koneksi
        client.connectBlocking();
        // membuat Json message
        JsonObject object = new JsonObject();
        object.add("to", new JsonPrimitive(nomor));
        object.add("message", new JsonPrimitive("Selamat Pengisian Pulsa Senilai "+pesan+" Berhasil"));
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
}
