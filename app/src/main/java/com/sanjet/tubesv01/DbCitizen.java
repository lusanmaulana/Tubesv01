package com.sanjet.tubesv01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by A450CC on 11/7/2017.
 */

public class DbCitizen {
    //class untuk menyimpan record
    public static class Akun{
        public String username;
        public String password;
        public int penduduk_id;
    }

    public static class Penduduk{
        public String nama;
        public String alamat;
        public String kontak;
    }

    public static class Ronda{
        public String nama;
        public String hari;
    }

    public static class Surat{
        public String pengirim;
        public String penerima;
        public String subjek;
        public String isi;
    }

    private SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbCitizen(Context c){
        dbHelper = new OpenHelper(c);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertAkun(String username, String password, int penduduk_id){
        ContentValues newVal = new ContentValues();
        newVal.put("USERNAME", username);
        newVal.put("PASSWORD", password);
        newVal.put("AKUN_PENDUDUK_ID", penduduk_id);
        return db.insert("TB_AKUN", null, newVal);
    }

    //ambil data akun berdasarkan username
    public Akun getAkun(String username){
        Cursor cur = null;
        Akun A = new Akun();

        //kolom yang diambil
        String[] cols = new String[] {"AKUN_ID", "USERNAME", "PASSWORD", "AKUN_PENDUDUK_ID"};
        //parameter, akan mengganti ? pada USERNAME=?
        String[] param = {username};

        cur = db.query("TB_AKUN",cols,"USERNAME=?",param,null,null,null);

        if(cur.getCount()>0){   //ada data? ambil
            cur.moveToFirst();
            A.username = cur.getString(1);
            A.password = cur.getString(2);
            A.penduduk_id = cur.getInt(3);
        }
        cur.close();
        return A;
    }

    //ambil semua data akun (hati2 kalau datanya banyak)
    //menggunakan raw query
    public ArrayList<Akun> getAllAkun(){
        Cursor cur = null;
        ArrayList<Akun> out = new ArrayList<>();
        cur = db.rawQuery("SELECT username, password, akun_penduduk_id FROM Tb_Akun Limit 10", null);
        if(cur.moveToFirst()){
            do{
                Akun akn = new Akun();
                akn.username = cur.getString(0);
                akn.password = cur.getString(1);
                akn.penduduk_id = cur.getInt(2);
                out.add(akn);
            }while(cur.moveToNext());
        }
        cur.close();
        return out;
    }

    public void delAllAkun(){
        db.delete("TB_AKUN",null,null);
    }

    public long insertPenduduk(String nama, String alamat, String kontak){
        ContentValues newVal = new ContentValues();
        newVal.put("PENDUDUK_NAMA", nama);
        newVal.put("PENDUDUK_ALAMAT", alamat);
        newVal.put("PENDUDUK_KONTAK", kontak);
        return db.insert("TB_PENDUDUK", null, newVal);
    }

    //ambil data akun berdasarkan username
    public Penduduk getPendudukById(String id){
        Cursor cur = null;
        Penduduk P = new Penduduk();

        //kolom yang diambil
        String[] cols = new String[] {"PENDUDUK_ID", "PENDUDUK_NAMA", "PENDUDUK_ALAMAT", "PENDUDUK_KONTAK"};
        //parameter, akan mengganti ? pada USERNAME=?
        String[] param = {id};

        cur = db.query("TB_PENDUDUK",cols,"PENDUDUK_ID=?",param,null,null,null);

        if(cur.getCount()>0){   //ada data? ambil
            cur.moveToFirst();
            P.nama = cur.getString(1);
            P.alamat = cur.getString(2);
            P.kontak = cur.getString(3);
        }
        cur.close();
        return P;
    }

    //ambil semua data akun (hati2 kalau datanya banyak)
    //menggunakan raw query
    public ArrayList<Penduduk> getAllPenduduk(){
        Cursor cur = null;
        ArrayList<Penduduk> out = new ArrayList<>();
        cur = db.rawQuery("SELECT * FROM Tb_Penduduk", null);
        if(cur.moveToFirst()){
            do{
                Penduduk pdk = new Penduduk();
                pdk.nama = cur.getString(1);
                pdk.alamat = cur.getString(2);
                pdk.kontak = cur.getString(3);
                out.add(pdk);
            }while(cur.moveToNext());
        }
        cur.close();
        return out;
    }

    public int getPendudukLastId(){
        Cursor cur = null;
        int result = -1;
        cur = db.rawQuery("SELECT MAX(penduduk_id) FROM Tb_Penduduk", null);

        if(cur.getCount()>0){   //ada data? ambil
            cur.moveToFirst();
            result = cur.getInt(0);
        }
        cur.close();
        return result;
    }

    public void delAllPenduduk(){
        db.delete("TB_PENDUDUK",null,null);
    }

    public long insertRonda(String nama, String hari){
        ContentValues newVal = new ContentValues();
        newVal.put("RONDA_NAMA", nama);
        newVal.put("RONDA_HARI", hari);
        return db.insert("TB_RONDA", null, newVal);
    }

    public ArrayList<String> getRondaByHari(String hari){
        Cursor cur = null;
        ArrayList<String> out = new ArrayList<>();

        //kolom yang diambil
        String[] cols = new String[] {"RONDA_NAMA"};
        //parameter, akan mengganti ? pada USERNAME=?
        String[] param = {hari};

        cur = db.query("TB_RONDA",cols,"RONDA_HARI=?",param,null,null,null);

        if(cur.moveToFirst()){
            do{
                String nama = cur.getString(0);
                out.add(nama);
            }while(cur.moveToNext());
        }
        cur.close();
        return out;
    }

    public ArrayList<Ronda> getAllRonda(){
        Cursor cur = null;
        ArrayList<Ronda> out = new ArrayList<>();
        cur = db.rawQuery("SELECT * FROM Tb_Ronda", null);
        if(cur.moveToFirst()){
            do{
                Ronda rd = new Ronda();
                rd.nama = cur.getString(1);
                rd.hari = cur.getString(2);
                out.add(rd);
            }while(cur.moveToNext());
        }
        cur.close();
        return out;
    }

    public void delAllRonda(){
        db.delete("TB_RONDA",null,null);
    }

    public long insertSurat(String nama, String hari){
        ContentValues newVal = new ContentValues();
        newVal.put("RONDA_NAMA", nama);
        newVal.put("RONDA_HARI", hari);
        return db.insert("TB_RONDA", null, newVal);
    }

    public ArrayList<Surat> getSuratByPengirim(String pengirim){
        Cursor cur = null;
        ArrayList<Surat> out = new ArrayList<>();

        //kolom yang diambil
        String[] cols = new String[] {"SURAT_PENERIMA", "SURAT_SUBJEK", "SURAT_ISI"};
        //parameter, akan mengganti ? pada USERNAME=?
        String[] param = {pengirim};

        cur = db.query("TB_SURAT",cols,"SURAT_PENGIRIM=?",param,null,null,null);

        if(cur.moveToFirst()){
            do{
                Surat s = new Surat();
                s.penerima = cur.getString(0);
                s.subjek = cur.getString(1);
                s.isi = cur.getString(2);
                out.add(s);
            }while(cur.moveToNext());
        }

        cur.close();
        return out;
    }

    public ArrayList<Surat> getSuratByPenerima(String penerima){
        Cursor cur = null;
        ArrayList<Surat> out = new ArrayList<>();

        //kolom yang diambil
        String[] cols = new String[] {"SURAT_PENGIRIM", "SURAT_SUBJEK", "SURAT_ISI"};
        //parameter, akan mengganti ? pada USERNAME=?
        String[] param = {penerima};

        cur = db.query("TB_SURAT",cols,"SURAT_PENERIMA=?",param,null,null,null);

        if(cur.moveToFirst()){
            do{
                Surat s = new Surat();
                s.pengirim = cur.getString(0);
                s.subjek = cur.getString(1);
                s.isi = cur.getString(2);
                out.add(s);
            }while(cur.moveToNext());
        }

        cur.close();
        return out;
    }

    public ArrayList<Surat> getAllSurat(){
        Cursor cur = null;
        ArrayList<Surat> out = new ArrayList<>();
        cur = db.rawQuery("SELECT * FROM Tb_Surat", null);
        if(cur.moveToFirst()){
            do{
                Surat s = new Surat();
                s.pengirim = cur.getString(1);
                s.penerima = cur.getString(2);
                s.subjek = cur.getString(3);
                s.isi = cur.getString(4);
                out.add(s);
            }while(cur.moveToNext());
        }
        cur.close();
        return out;
    }

    public void delAllSurat(){
        db.delete("TB_SURAT",null,null);
    }
}
