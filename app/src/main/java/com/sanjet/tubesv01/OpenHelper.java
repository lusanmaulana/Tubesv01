package com.sanjet.tubesv01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A450CC on 11/7/2017.
 */

public class OpenHelper extends SQLiteOpenHelper {
    //kalau ada upgrade, increment versi database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbCitizen.db";

    public static final String TABLE_AKUN_CREATE =
            "CREATE TABLE TB_AKUN (AKUN_ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, AKUN_PENDUDUK_ID INTEGER)";
    public static final String TABLE_RONDA_CREATE =
            "CREATE TABLE TB_RONDA (RONDA_ID INTEGER PRIMARY KEY AUTOINCREMENT, RONDA_NAMA TEXT, RONDA_HARI TEXT)";
    public static final String TABLE_PENDUDUK_CREATE =
            "CREATE TABLE TB_PENDUDUK (PENDUDUK_ID INTEGER PRIMARY KEY AUTOINCREMENT, PENDUDUK_NAMA TEXT, PENDUDUK_ALAMAT TEXT, PENDUDUK_KONTAK TEXT)";
    public static final String TABLE_SURAT_CREATE =
            "CREATE TABLE TB_SURAT (SURAT_ID INTEGER PRIMARY KEY AUTOINCREMENT, SURAT_ID_PENGIRIM TEXT, SURAT_ID_PENERIMA TEXT, SURAT_SUBJEK TEXT, SURAT_ISI TEXT)";

    public OpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create database
        db.execSQL(TABLE_AKUN_CREATE);
        db.execSQL(TABLE_RONDA_CREATE);
        db.execSQL(TABLE_PENDUDUK_CREATE);
        db.execSQL(TABLE_SURAT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        //jika app diupgrade (diinstall yang baru) maka database akan dicreate ulang (data hilang)
        //jika tidak ingin hilang, bisa diproses disini
        db.execSQL("DROP TABLE IF EXISTS TB_AKUN");
    }
}
