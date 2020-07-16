package com.tugas.akhirtugas.DataLongsor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tugas.akhirtugas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataLongsor extends AppCompatActivity {
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.rv_berita)
    RecyclerView rvBerita;
    @BindView(R.id.swlayout)
    SwipeRefreshLayout swlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_longsor);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Data Longsor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
