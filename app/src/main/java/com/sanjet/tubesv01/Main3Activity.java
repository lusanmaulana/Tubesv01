package com.sanjet.tubesv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        String uname = etUsername.getText().toString();
        String pass = etPassword.getText().toString();

        db.insertAkun(uname,pass);

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);

        Toast t = Toast.makeText(getApplicationContext(), "Akun berhasil dibuat",      Toast.LENGTH_LONG);
        t.show();
    }
}
