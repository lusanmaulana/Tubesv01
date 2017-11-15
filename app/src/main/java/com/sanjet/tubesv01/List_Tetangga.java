package com.sanjet.tubesv01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List_Tetangga extends AppCompatActivity {

    private ArrayList<String> items = new ArrayList<>();
    ListView listTetangga;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__tetangga);

        DbCitizen db = new DbCitizen(getApplicationContext());
        db.open();

        ArrayList<DbCitizen.Penduduk> pdk = db.getAllPenduduk();
        for(DbCitizen.Penduduk index : pdk){
            items.add(index.nama+"\n"+index.alamat);
        }

        listTetangga = (ListView) findViewById(R.id.listTetangga);
        adapter = new ArrayAdapter (this, android.R.layout.simple_expandable_list_item_1, items);
        listTetangga.setAdapter(adapter);
    }
}
