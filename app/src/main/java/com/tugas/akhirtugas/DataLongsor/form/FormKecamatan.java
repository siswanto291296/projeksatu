package com.tugas.akhirtugas.DataLongsor.form;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tugas.akhirtugas.DataLongsor.DataLongsor;
import com.tugas.akhirtugas.DataLongsor.Kecamatan;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.crud.ResponseCrud;
import com.tugas.akhirtugas.model.kecamatan.KecamatanItem;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal2;

public class FormKecamatan extends AppCompatActivity implements Callback<ResponseCrud>{
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_tambah)
    Button btnTambah;
    @BindView(R.id.nama)
    TextInputEditText nama;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    boolean flagUpdate = false;
    KecamatanItem data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kecamatan);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Form Kecamatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UI();
    }

    private void UI() {
        data = getIntent().getParcelableExtra(DATA);
        if (data != null) {
            flagUpdate = true;
            btnTambah.setText("Ubah");

            nama.setText(data.getNamaKec());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (flagUpdate) getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_delete:
                alertDeleted();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
        created(nama.getText().toString());
    }

    private void created(String nama) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = null;

        if (data == null){
            call = service.createKecamatan(nama);
        }else {
            call = service.updateKecamatan(data.getIdKec(), nama);
        }
        call.enqueue(this);
    }

    private void deleted(String tableName, String tablePrimary, String valueID) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.deleted(tableName, tablePrimary, valueID);
        call.enqueue(this);
    }

    private void alertDeleted() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
        //settting judul dan pesan
        aleBuilder.setTitle("Hapus Data");
        aleBuilder
                .setMessage("Apakah anda yakin ingin menghapus data ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, which) -> {
                    deleted("kecamatan", "id_kec", data.getIdKec());
                })
                .setNegativeButton("Tidak", (dialog, id1) -> {
                    //cancel
                    dialog.cancel();
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onResponse(Call<ResponseCrud> call, Response<ResponseCrud> response) {
        if (response.body().isResponse()) {
            Toast.makeText(FormKecamatan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(FormKecamatan.this, Kecamatan.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            //hancurkan activity
            finish();
        } else {
            Toast.makeText(FormKecamatan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseCrud> call, Throwable t) {
        Toast.makeText(FormKecamatan.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("error ", t.getMessage());
    }
}