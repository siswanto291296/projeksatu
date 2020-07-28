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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tugas.akhirtugas.DataLongsor.DataLongsor;
import com.tugas.akhirtugas.DataLongsor.Kecamatan;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.crud.ResponseCrud;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormKecamatan extends AppCompatActivity {
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_tambah)
    Button btnTambah;
    @BindView(R.id.nama)
    TextInputEditText nama;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kecamatan);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Form Kecamatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //if (flagUpdate) getMenuInflater().inflate(R.menu.menu, menu);
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
                //alertDeleted();
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
        Call<ResponseCrud> call = service.createKecamatan(nama);
        call.enqueue(new Callback<ResponseCrud>() {
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
        });
    }
}