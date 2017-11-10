package com.sanjet.tubesv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> telp = new ArrayList<>();

    int status=0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnLoginOnClick(View v){
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        String uname = etUsername.getText().toString();
        String pass = etPassword.getText().toString();

        DbCitizen db = new DbCitizen(getApplicationContext());
        db.open();

        i=0;
        ArrayList<DbCitizen.Akun> akun = db.getAllAkun();
        for(DbCitizen.Akun index : akun){
           if(uname.equals(index.username.toString()) && pass.equals(index.password.toString())){
                status=1;
           }
        }

        if(status == 1){
            Intent intent2 = new Intent(this, Main2Activity.class);
            startActivity(intent2);
        }else{
            Toast t = Toast.makeText(getApplicationContext(), "Username atau Password anda salah",      Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void btnTambahOnClick(View v){
        Intent intent3 = new Intent(this, Main3Activity.class);
        startActivity(intent3);
    }
}