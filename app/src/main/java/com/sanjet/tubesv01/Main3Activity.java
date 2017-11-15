package com.sanjet.tubesv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void btnCreateOnClick(View v){
        DbCitizen db = new DbCitizen(getApplicationContext());
        db.open();

        EditText etUsername = (EditText) findViewById(R.id.etNewUsername);
        EditText etPassword = (EditText) findViewById(R.id.etNewPassword);
        EditText etNama = (EditText) findViewById(R.id.etNewNama);
        EditText etAlamat = (EditText) findViewById(R.id.etNewAlamat);
        EditText etKontak = (EditText) findViewById(R.id.etNewKontak);

        String uname = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        pass = md5(pass);
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        String kontak = etKontak.getText().toString();

        db.insertPenduduk(nama,alamat,kontak);

        int penduduk_id = db.getPendudukLastId();

        if(penduduk_id % 7 == 1){
            db.insertRonda(nama,"Senin");
        }else if(penduduk_id % 7 == 2){
            db.insertRonda(nama,"Selasa");
        }else if(penduduk_id % 7 == 3){
            db.insertRonda(nama,"Rabu");
        }else if(penduduk_id % 7 == 4){
            db.insertRonda(nama,"Kamis");
        }else if(penduduk_id % 7 == 5){
            db.insertRonda(nama,"Jumat");
        }else if(penduduk_id % 7 == 6){
            db.insertRonda(nama,"Sabtu");
        }else {
            db.insertRonda(nama,"Minggu");
        }

        db.insertAkun(uname,pass,penduduk_id);

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);

        Toast t = Toast.makeText(getApplicationContext(), "Anda berhasil mendaftar",      Toast.LENGTH_LONG);
        t.show();
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
