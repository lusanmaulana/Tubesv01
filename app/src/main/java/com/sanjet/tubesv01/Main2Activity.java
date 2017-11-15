package com.sanjet.tubesv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DbCitizen db = new DbCitizen(getApplicationContext());
        db.open();

        Intent intent2 = getIntent();
        int penduduk_id = intent2.getIntExtra(MainActivity.EXTRA_MESSAGE,0);

        String id = ""+penduduk_id;

        DbCitizen.Penduduk pdk = db.getPendudukById(id);
        String nama = pdk.nama;

        TextView tvNama = (TextView) findViewById(R.id.tvNama);
        tvNama.setText(nama);
    }

    public void btnRondaOnClick(View v){
        Intent ronda = new Intent(this, List_Ronda.class);
        startActivity(ronda);
    }

    public void btnTetanggaOnClick(View v){
        Intent tetangga = new Intent(this, List_Tetangga.class);
        startActivity(tetangga);
    }
}
