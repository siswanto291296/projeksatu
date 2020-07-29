package com.tugas.akhirtugas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.Berita.detail.DetailBerita;
import com.tugas.akhirtugas.model.berita.BeritaItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.network.RetroClient.BASE_URL_IMAGE;
import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.MyFucntion.view;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {
    private List<BeritaItem> list;
    private Context context;

    public AdapterBerita(List<BeritaItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.berita_item));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //code is all item click and set value
        holder.JudulBerita.setText(list.get(position).getJudulBerita());
        holder.TglTerbit.setText(ubahTanggal(list.get(position).getTanggal()));
        holder.Penulis.setText("Oleh : BPBD Kabupaten Pekalongan");

        if (list.get(position).getFoto() != null){
            Glide.with(context)
                    .load(BASE_URL_IMAGE + list.get(position).getFoto())
                    .into(holder.PosterBerita);
        }

        //onclick
        holder.ln.setOnClickListener(v -> {
            context.startActivity(new Intent(context, DetailBerita.class)
                    .putExtra(DATA, list.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.PosterBerita)
        ImageView PosterBerita;
        @BindView(R.id.JudulBerita)
        TextView JudulBerita;
        @BindView(R.id.TglTerbit)
        TextView TglTerbit;
        @BindView(R.id.Penulis)
        TextView Penulis;
        @BindView(R.id.ln)
        LinearLayout ln;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
