package com.sanjet.tubesv01;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class List_Ronda extends AppCompatActivity {

    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<String> items2 = new ArrayList<>();
    ListView listRonda;
    Spinner listHari;
    ArrayAdapter adapter2;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__ronda);

        DbCitizen db = new DbCitizen(getApplicationContext());
        db.open();

        listRonda = (ListView) findViewById(R.id.listRonda);
        adapter = new ArrayAdapter (this, android.R.layout.simple_expandable_list_item_1, items);

        listHari = (Spinner) findViewById(R.id.spHari);
        items2.add("Senin");
        items2.add("Selasa");
        items2.add("Rabu");
        items2.add("Kamis");
        items2.add("Jumat");
        items2.add("Sabtu");
        items2.add("Minggu");
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2);
        listHari.setAdapter(adapter2);

        listHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DbCitizen db = new DbCitizen(getApplicationContext());
                db.open();

                switch(i){
                    case 0:
                        items.clear();
                        ArrayList<String> rondaSenin = db.getRondaByHari("Senin");
                        for(String index : rondaSenin){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 1:
                        items.clear();
                        ArrayList<String> rondaSelasa = db.getRondaByHari("Selasa");
                        for(String index : rondaSelasa){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 2:
                        items.clear();
                        ArrayList<String> rondaRabu = db.getRondaByHari("Rabu");
                        for(String index : rondaRabu){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 3:
                        items.clear();
                        ArrayList<String> rondaKamis = db.getRondaByHari("Kamis");
                        for(String index : rondaKamis){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 4:
                        items.clear();
                        ArrayList<String> rondaJumat = db.getRondaByHari("Jumat");
                        for(String index : rondaJumat){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 5:
                        items.clear();
                        ArrayList<String> rondaSabtu = db.getRondaByHari("Sabtu");
                        for(String index : rondaSabtu){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                    case 6:
                        items.clear();
                        ArrayList<String> rondaMinggu = db.getRondaByHari("Minggu");
                        for(String index : rondaMinggu){
                            items.add(index);
                        }
                        listRonda.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
