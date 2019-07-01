package com.example.smsbaru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void lpulsa(View view) {
        Intent intent = new Intent(Home.this ,Lpulsa.class);
        startActivity(intent);
        finish();

    }

    public void actpulsa(View view) {
        Intent pulsa = new Intent(Home.this, MainActivity.class);
        startActivity(pulsa);
        finish();
    }

    public void lpaket(View view) {
        Intent lpaket = new Intent(Home.this, Lpaket.class);
        startActivity(lpaket);
        finish();
    }

    public void actpaket(View view) {
        Intent paket = new Intent(Home.this, Mainpaket.class);
        startActivity(paket);
        finish();
    }
}
