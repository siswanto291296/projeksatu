package com.tugas.akhirtugas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tugas.akhirtugas.Berita.detail.DetailBerita;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.berita.BeritaItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.network.RetroClient.BASE_URL_IMAGE;
import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.MyFucntion.view;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal;

public class AdapterGaleri extends RecyclerView.Adapter<AdapterGaleri.ViewHolder> {

    private List<BeritaItem> list;
    private AppCompatActivity context;

    public AdapterGaleri(List<BeritaItem> list, AppCompatActivity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.cardview_galeri));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //code is all item click and set value
        holder.bookTitleId.setText(list.get(position).getJudulBerita());
        Glide.with(context)
                .load(BASE_URL_IMAGE + list.get(position).getFoto())
                .into(holder.bookImgId);

        //onclick
        holder.cardviewId.setOnClickListener(v -> {
            notifyFoto(context, list.get(position).getFoto());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.book_img_id)
        ImageView bookImgId;
        @BindView(R.id.book_title_id)
        TextView bookTitleId;
        @BindView(R.id.cardview_id)
        CardView cardviewId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void notifyFoto(AppCompatActivity activity, String image) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.pop_image, viewGroup, false);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();

        //init ui
        Button btnOK = dialogView.findViewById(R.id.buttonOk);
        ImageView img = dialogView.findViewById(R.id.icon);

        Glide.with(context)
                .load(BASE_URL_IMAGE + image)
                .into(img);

        btnOK.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        alertDialog.show();
    }
}
