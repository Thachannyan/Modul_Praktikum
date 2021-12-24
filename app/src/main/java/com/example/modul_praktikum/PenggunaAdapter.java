package com.example.modul_praktikum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PenggunaAdapter extends RecyclerView.Adapter<PenggunaAdapter.PenggunaVH> {

    ArrayList<Pengguna> penggunas;
    Context context;

    public PenggunaAdapter(ArrayList<Pengguna> penggunas, Context context) {
        this.penggunas = penggunas;
        this.context = context;
    }

    @NonNull
    @Override
    public PenggunaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_pengguna, parent, false);
        PenggunaVH svh = new PenggunaVH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull PenggunaVH holder, int position) {

        Pengguna s = penggunas.get(position);
        holder.tvNama.setText(s.getNama());
        holder.tvJk.setText(s.getJk());
        holder.tvNotelp.setText(s.getNotelp());
        holder.tvEmail.setText(s.getEmail());
        holder.tvPw.setText(s.getPw());

        holder.cardUpdate.setOnClickListener((view) -> {
                Intent intent = new Intent(context, UpdatePenggunaActivity.class);
                intent.putExtra("PENGGUNA", s);
                context.startActivity(intent);
        });
        holder.cardDelete.setOnClickListener((view) ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Konfirmasi!!!");
            builder.setMessage("Apa Kamu Yakin untuk menghapus: " + s.getNama() + "?");
            builder.setIcon(android.R.drawable.ic_menu_delete);
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DBHelper dbHelper = new DBHelper(context);

                    int result = dbHelper.deletePengguna(s.getNama());

                    if( result > 0)
                    {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        penggunas.remove(s);
                        notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return penggunas.size();
    }

    class PenggunaVH extends RecyclerView.ViewHolder{
        TextView tvNama, tvJk, tvNotelp, tvEmail, tvPw;
        CardView cardUpdate, cardDelete;
        public PenggunaVH(@NonNull View v) {
            super(v);

            tvNama = v.findViewById(R.id.tvNama);
            tvJk = v.findViewById(R.id.tvJk);
            tvNotelp = v.findViewById(R.id.tvNoTelp);
            tvEmail = v.findViewById(R.id.tvEmail);
            tvPw = v.findViewById(R.id.tvPw);

            cardDelete = v.findViewById(R.id.cardDelete);
            cardUpdate = v.findViewById(R.id.cardUpdate);
        }
    }
}
