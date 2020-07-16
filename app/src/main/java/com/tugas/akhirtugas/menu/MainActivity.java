package com.tugas.akhirtugas.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tugas.akhirtugas.BPBD.BpbdActivity;
import com.tugas.akhirtugas.Berita.BeritaActivity;
import com.tugas.akhirtugas.DataLongsor.DataLongsor;
import com.tugas.akhirtugas.Galeri.GaleriActivity;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.kontak.KontakChat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.logobpbd1)
    ImageView logobpbd1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar_id)
    AppBarLayout appBarId;
    @BindView(R.id.tokoh)
    ImageView tokoh;
    @BindView(R.id.berita)
    CardView berita;
    @BindView(R.id.cerita)
    ImageView cerita;
    @BindView(R.id.cuaca)
    CardView cuaca;
    @BindView(R.id.soal)
    ImageView soal;
    @BindView(R.id.data_longsor)
    CardView dataLongsor;
    @BindView(R.id.keluar)
    ImageView keluar;
    @BindView(R.id.info)
    CardView info;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, KontakChat.class);
            startActivity(intent);
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.data_info) {
            intent(BpbdActivity.class);
        } else if (id == R.id.nav_galeri) {
            intent(GaleriActivity.class);
        } else if (id == R.id.nav_aplikasi) {
            intent(TentangAplikasi.class);
        } else if (id == R.id.nav_panduan) {
            intent(Panduan.class);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dataLongsor() {
        intent(DataLongsor.class);
    }

    private void chat() {
        intent(KontakChat.class);
    }

    private void berita() {
        intent(BeritaActivity.class);
    }

    private void info() {
        intent(Panduan.class);
    }

    @OnClick({R.id.berita, R.id.cuaca, R.id.data_longsor, R.id.info, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.berita:
                berita();
                break;
            case R.id.cuaca:
                break;
            case R.id.data_longsor:
                dataLongsor();
                break;
            case R.id.info:
                info();
                break;
            case R.id.btn_login:
                break;
        }
    }

    private void intent(Class activity) {
        startActivity(new Intent(MainActivity.this, activity));
    }
}
