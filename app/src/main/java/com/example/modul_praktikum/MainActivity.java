package com.example.modul_praktikum;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {


    Button kirim,back;
    EditText nama, notelp, email, pw;
    RadioGroup jenis_kelamin;
    CheckBox persetujuan;
    Intent kmbl_hal_utama;
    SeekBar seekbar_v;
    TextView persentase;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kirim = findViewById(R.id.btn_submit);
        nama = findViewById(R.id.nama);
        notelp = findViewById(R.id.notelp);
        email = findViewById(R.id.email);
        jenis_kelamin = findViewById(R.id.jk);
        persetujuan = findViewById(R.id.checkbox_setuju);
        back = findViewById(R.id.btn_back);
        pw = findViewById(R.id.pw);
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



                Boolean checkinsertdata = DB.insertuserdata(namaTXT,  jkTXT, notelpTXT, emailTXT, pwTXT );
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Data Baru Berhasil Diinput", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Baru gagal diinput", Toast.LENGTH_SHORT).show();

                if (persetujuan.isChecked()){
                    Intent kirim_hasil = new Intent(MainActivity.this, HalamanHasil.class);
                    kirim_hasil.putExtra("data1", namaTXT);
                    kirim_hasil.putExtra("data2", notelpTXT);
                    kirim_hasil.putExtra("data3", emailTXT);
                    kirim_hasil.putExtra("data4", jkTXT);

                    startActivity(kirim_hasil);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Mohon klik persetujuan untuk melanjutkan", Toast.LENGTH_SHORT).show();
                }

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kmbl_hal_utama = new Intent(MainActivity.this, HalamanUtama.class);
                startActivity(kmbl_hal_utama);
                finish();
            }
        });

        seekbar_v = findViewById(R.id.v_seekbar);
        persentase = findViewById(R.id.v_angka);

        seekbar_v.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
        // Pemberitahuan Bahwa Nilai Pada Progress Telah Berubah
        persentase.setText(String.valueOf(value));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Pemberitahuan Bahwa User Telah Menyentuh/Mengendalikan Progress Pada SeekBar
        Toast.makeText(this, "Progress Dimulai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Pemberitahuan Bahwa User Telah Selesai Mengendalikan Progress Pada SeekBar
        Toast.makeText(this, "Progress Berhenti", Toast.LENGTH_SHORT).show();
    }

}