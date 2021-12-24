package com.example.modul_praktikum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HalamanUtama extends AppCompatActivity {

    AppCompatButton daftar, lihat;
    Intent daftar_akun, lihat_data;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_utama);

        daftar = findViewById(R.id.signup);
        lihat = findViewById(R.id.lihat);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar_akun = new Intent(HalamanUtama.this, MainActivity.class );
                startActivity(daftar_akun);
                finish();
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihat_data = new Intent(HalamanUtama.this, ListPenggunaActivity.class );
                startActivity(lihat_data);
                finish();
            }
        });

    }

}

