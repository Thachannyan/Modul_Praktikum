package com.example.modul_praktikum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

public class HalamanHasil extends Activity {
    TextView view1, view2,view3, view4;
    AppCompatButton kembali;
    Intent kembali_utama;
    boolean first=true;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_daftar);

        view1 = findViewById(R.id.v_data1);
        view2 = findViewById(R.id.v_data2);
        view3 = findViewById(R.id.v_data3);
        view4 = findViewById(R.id.v_data4);
        kembali = findViewById(R.id.kembali);

        view1.setText(getIntent().getStringExtra("data1"));
        view2.setText(getIntent().getStringExtra("data4"));
        view3.setText(getIntent().getStringExtra("data2"));
        view4.setText(getIntent().getStringExtra("data3"));

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kembali_utama = new Intent(HalamanHasil.this, HalamanUtama.class);
                startActivity(kembali_utama);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Program masih berjalan", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(first){
            Toast.makeText(this, "Halo...", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Selamat datang kembali....", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        first=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(this, HalamanUtama.class);
        Toast.makeText(this, "Input lagi..", Toast.LENGTH_LONG).show();
        startActivity(i);
    }


}
