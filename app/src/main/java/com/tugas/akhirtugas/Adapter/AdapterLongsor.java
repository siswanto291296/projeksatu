package com.tugas.akhirtugas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tugas.akhirtugas.DataLongsor.detail.DetailLongsor;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.MyFucntion.view;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal2;

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
        holder.txtJudul.setText(list.get(position).getJenisBencana());
        holder.txtKorban.setText(list.get(position).getKorban()+ "/Jiwa");
        holder.txtTgl.setText(ubahTanggal2(list.get(position).getTanggal()));
        holder.txtWaktu.setText(list.get(position).getWaktu() + " WIB");
        holder.txtAlamat.setText(list.get(position).getLokasi());

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
        @BindView(R.id.txt_judul)
        TextView txtJudul;
        @BindView(R.id.txt_korban)
        TextView txtKorban;
        @BindView(R.id.txt_tgl)
        TextView txtTgl;
        @BindView(R.id.txt_waktu)
        TextView txtWaktu;
        @BindView(R.id.txt_alamat)
        TextView txtAlamat;
        @BindView(R.id.ln)
        LinearLayout ln;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
