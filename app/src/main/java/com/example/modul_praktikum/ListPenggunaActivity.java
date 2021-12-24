package com.example.modul_praktikum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ListPenggunaActivity extends AppCompatActivity {
    TextView tvTotal;
    RecyclerView recyclerView;
    PenggunaAdapter penggunaAdapter;
    ArrayList<Pengguna> penggunas;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pengguna);

        recyclerView = findViewById(R.id.recyclerView);
        tvTotal = findViewById(R.id.tvTotal);
        dbHelper = new DBHelper(this);


    }

    @Override
    protected void onStart(){
        super.onStart();
        penggunas = dbHelper.getAllPengguna();
        tvTotal.setText("Total Pengguna : " + penggunas.size());

        penggunaAdapter = new PenggunaAdapter(penggunas, this);
        recyclerView.setAdapter(penggunaAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }
}