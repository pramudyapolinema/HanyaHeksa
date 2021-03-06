package org.aplas.ecommerce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, nama, harga, gambar, deskripsi;

    AdapterProduk(Context context, ArrayList id, ArrayList nama, ArrayList harga, ArrayList deskripsi, ArrayList gambar) {
        this.context = context;
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_produk, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Locale idn = new Locale("id", "ID");
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(idn);
        String harga2 = String.valueOf(harga.get(position));
        holder.nama_txt.setText(String.valueOf(nama.get(position)));
        holder.harga_txt.setText(rupiahFormat.format(Integer.parseInt(harga2)));
//        holder.deskripsi_txt.setText(String.valueOf(deskripsi.get(position)));
        holder.gambar_img.setImageURI(Uri.parse(String.valueOf(gambar.get(position))));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDetails = new Intent(context, DetailProduk.class);
                goDetails.putExtra("id", id.get(position).toString());
                goDetails.putExtra("nama", nama.get(position).toString());
                goDetails.putExtra("harga", rupiahFormat.format(Integer.parseInt(harga2)));
                goDetails.putExtra("gambar", String.valueOf(gambar.get(position)));
                goDetails.putExtra("deskripsi", String.valueOf(deskripsi.get(position)));
                context.startActivity(goDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nama.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama_txt, harga_txt, deskripsi_txt;
        ImageView gambar_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_txt = itemView.findViewById(R.id.namaProduk);
//            deskripsi_txt = itemView.findViewById(R.id.);
            harga_txt = itemView.findViewById(R.id.hargaProduk);
            gambar_img = itemView.findViewById(R.id.gambarProduk);
        }
    }
}
