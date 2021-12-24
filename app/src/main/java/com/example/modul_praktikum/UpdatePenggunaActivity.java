package com.example.modul_praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePenggunaActivity extends AppCompatActivity {
    Button kirim,back;
    EditText notelp, email, pw;
    TextView nama;
    RadioGroup jenis_kelamin;
    Intent kmbl_hal_utama;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pengguna);

        Pengguna s = (Pengguna) getIntent().getExtras().getSerializable("PENGGUNA");
        kirim = findViewById(R.id.btn_submit);
        nama = findViewById(R.id.nama);
        notelp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        jenis_kelamin = findViewById(R.id.jk);
        back = findViewById(R.id.btn_back);
        pw = findViewById(R.id.pw);

        nama.setText(s.getNama());
        notelp.setText(s.getNotelp());
        email.setText(s.getEmail());
        pw.setText(s.getPw());
        DB = new DBHelper(this);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = nama.getText().toString();
                int gender = jenis_kelamin.getCheckedRadioButtonId();
                RadioButton jk = (RadioButton) findViewById(gender);
                String jkTXT = jk.getText().toString();
                String notelpTXT = notelp.getText().toString();
                String emailTXT = email.getText().toString();
                String pwTXT = pw.getText().toString();

                Pengguna s = new Pengguna(namaTXT, notelpTXT, emailTXT, jkTXT  , pwTXT);

                int result = DB.updatePengguna(s);

                if (result > 0) {
                    Toast.makeText(UpdatePenggunaActivity.this, "Update Berhasil" + result, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdatePenggunaActivity.this, "Gagal: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kmbl_hal_utama = new Intent(UpdatePenggunaActivity.this, ListPenggunaActivity.class);
                startActivity(kmbl_hal_utama);
                finish();
            }
        });
    }
}