package com.example.modul_praktikum;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "database_data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table pengguna(nama TEXT primary key, jk TEXT, notelp INTEGER, email TEXT, pw TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists pengguna");
    }

    public Boolean insertuserdata(String nama, String jk, String notelp, String email,  String pw)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("jk", jk);
        contentValues.put("notelp", notelp);
        contentValues.put("email", email);
        contentValues.put("pw", pw);
        long result=DB.insert("pengguna", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<Pengguna> getAllPengguna(){
        ArrayList<Pengguna> penggunas = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from pengguna", null);

        if( cursor.moveToFirst())
        {
            do {
                String nama = cursor.getString(0);
                String jk = cursor.getString(2);
                String notelp = cursor.getString(3);
                String email = cursor.getString(1);
                String pw = cursor.getString(4);

                Pengguna s = new Pengguna(nama,jk,notelp,email,pw);
                penggunas.add(s);
            }
            while(cursor.moveToNext());
        }
        return penggunas;
    }

    public int updatePengguna(Pengguna s) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nama", s.getNama());
        cv.put("jk", s.getJk());
        cv.put("notelp", s.getNotelp());
        cv.put("email", s.getEmail());
        cv.put("pw", s.getPw());
        return db.update("pengguna", cv, "nama=?", new String[]{String.valueOf(s.getNama())});

    }

    public int deletePengguna(String nama) {

        SQLiteDatabase db = getWritableDatabase();
        return db.delete("pengguna", "nama=?", new String[]{ String.valueOf(nama)});

    }
}

