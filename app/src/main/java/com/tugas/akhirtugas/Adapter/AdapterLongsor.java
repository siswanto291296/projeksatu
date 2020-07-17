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

import com.tugas.akhirtugas.Berita.detail.DetailBerita;
import com.tugas.akhirtugas.DataLongsor.detail.DetailLongsor;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.MyFucntion.view;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal;

public class AdapterLongsor extends RecyclerView.Adapter<AdapterLongsor.ViewHolder> {
    private List<DataLongsorItem> list;
    private Context context;

    public AdapterLongsor(List<DataLongsorItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.longsor_item));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //code is all item click and set value
        holder.JudulBerita.setText(list.get(position).getJenisBencana());
        holder.TglTerbit.setText(ubahTanggal(list.get(position).getTanggal()));
        holder.Penulis.setText("Oleh : Badan Pengawan Bencana");

        //onclick
        holder.ln.setOnClickListener(v -> {
            context.startActivity(new Intent(context, DetailLongsor.class)
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
