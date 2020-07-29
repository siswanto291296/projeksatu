package com.tugas.akhirtugas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tugas.akhirtugas.DataLongsor.form.FormKecamatan;
import com.tugas.akhirtugas.DataLongsor.maps.MapsActivity;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.kecamatan.KecamatanItem;
import com.tugas.akhirtugas.session.Session;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.MyFucntion.view;

public class AdapterKecamatan extends RecyclerView.Adapter<AdapterKecamatan.ViewHolder> {
    private List<KecamatanItem> list;
    private Context context;
    private Session session;

    public AdapterKecamatan(List<KecamatanItem> list, Context context) {
        this.list = list;
        this.context = context;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.item_kecamatan));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //code is all item click and set value
        holder.txtName.setText(list.get(position).getNamaKec());

        if (session.getSPSudahLogin2()) {
            //onlong
            holder.ln.setOnClickListener(v -> {
                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
                pictureDialog.setTitle("Pilih Aksi");
                String[] pictureDialogItems = {
                        "Lihat",
                        "Ubah"};
                pictureDialog.setItems(pictureDialogItems,
                        (dialog, which) -> {
                            switch (which) {
                                case 0:
                                    context.startActivity(new Intent(context, MapsActivity.class)
                                            .putExtra(DATA, list.get(position)));
                                    break;
                                case 1:
                                    context.startActivity(new Intent(context, FormKecamatan.class)
                                            .putExtra(DATA, list.get(position)));
                                    break;
                            }
                        });
                pictureDialog.show();
            });
        } else {
            //onclick
            holder.ln.setOnClickListener(v -> {
                context.startActivity(new Intent(context, MapsActivity.class)
                        .putExtra(DATA, list.get(position)));
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.ln)
        LinearLayout ln;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
