package com.tugas.akhirtugas.DataLongsor;

import android.content.Context;
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
import com.tugas.akhirtugas.Adapter.AdapterLongsor;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;
import com.tugas.akhirtugas.model.longsor.ResponseLongsor;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLongsor extends AppCompatActivity {
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.rv_berita)
    RecyclerView rv;
    @BindView(R.id.swlayout)
    SwipeRefreshLayout swipeRef;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_longsor);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Data Longsor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getDataLongsor();
        swipeRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataLongsor();
                // Berhenti berputar/refreshing
                swipeRef.setRefreshing(false);
            }
        });
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
        Call<ResponseLongsor> loginCall = apiService.getDataLongsor();
        loginCall.enqueue(new Callback<ResponseLongsor>() {
            @Override
            public void onResponse(Call<ResponseLongsor> call, Response<ResponseLongsor> response) {
                ResponseLongsor login = response.body();
                try {
                    if (login.isResponse()) {
                        loadItem(response.body().getDataLongsor());
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
            public void onFailure(Call<ResponseLongsor> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Toast.makeText(context, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadItem(List<DataLongsorItem> list){
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterLongsor adapterNotifikasi = new AdapterLongsor(list, this);
        loading.setVisibility(View.GONE);
        rv.setAdapter(adapterNotifikasi);
    }
}
