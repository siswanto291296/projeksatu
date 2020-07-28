package com.tugas.akhirtugas.DataLongsor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tugas.akhirtugas.Adapter.AdapterKecamatan;
import com.tugas.akhirtugas.DataLongsor.form.FormDataLongsor;
import com.tugas.akhirtugas.DataLongsor.form.FormKecamatan;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.kecamatan.KecamatanItem;
import com.tugas.akhirtugas.model.kecamatan.ResponseKecamatan;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;
import com.tugas.akhirtugas.session.Session;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kecamatan extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.rv_berita)
    RecyclerView rv;
    @BindView(R.id.swlayout)
    SwipeRefreshLayout swipeRef;
    Context context = this;
    Session sharedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kecamatan);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Data Kecamatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkLogin();

        getDataLongsor();

        swipeRef.setOnRefreshListener(() -> {
            getDataLongsor();
            // Berhenti berputar/refreshing
            swipeRef.setRefreshing(false);
        });
    }

    @SuppressLint("RestrictedApi")
    private void checkLogin() {
        sharedLogin = new Session(context);
        if (sharedLogin.getSPSudahLogin2()) fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataLongsor() {
        loading.setVisibility(View.VISIBLE);
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseKecamatan> loginCall = apiService.getKecamatan();
        loginCall.enqueue(new Callback<ResponseKecamatan>() {
            @Override
            public void onResponse(Call<ResponseKecamatan> call, Response<ResponseKecamatan> response) {
                ResponseKecamatan login = response.body();
                try {
                    if (login.isResponse()) {
                        loadItem(response.body().getKecamatan());
                        loading.setVisibility(View.GONE);
                    } else {
                        loading.setVisibility(View.GONE);
                        Snackbar.make(loading, "Data Kosong!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    Toast.makeText(context, "error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatan> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Toast.makeText(context, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadItem(List<KecamatanItem> list) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterKecamatan adapterNotifikasi = new AdapterKecamatan(list, this);
        loading.setVisibility(View.GONE);
        rv.setAdapter(adapterNotifikasi);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, FormKecamatan.class));
    }
}