package com.tugas.akhirtugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tugas.akhirtugas.BPBD.BpbdActivity;
import com.tugas.akhirtugas.Berita.BeritaActivity;
import com.tugas.akhirtugas.DataLongsor.DataLongsor;
import com.tugas.akhirtugas.Galeri.GaleriActivity;
import com.tugas.akhirtugas.kontak.KontakChat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KontakChat.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            Intent i = new Intent(MainActivity.this, BpbdActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_tentang_kab) {
            Intent i = new Intent(MainActivity.this, DataLongsor.class);
            startActivity(i);
        } else if (id == R.id.nav_galeri) {
            Intent i = new Intent(MainActivity.this, GaleriActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_aplikasi) {
            Intent i = new Intent(MainActivity.this, TentangAplikasi.class);
            startActivity(i);
        }else if (id == R.id.nav_panduan) {
            Intent i = new Intent(MainActivity.this, Panduan.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void data_longsor(View view) {
        Intent i = new Intent(MainActivity.this, DataLongsor.class);
        startActivity(i);
    }

    public void chat(View view) {
        Intent i = new Intent(MainActivity.this, KontakChat.class);
        startActivity(i);
    }

    public void berita(View view) {
        Intent i = new Intent(MainActivity.this, BeritaActivity.class);
        startActivity(i);
    }

    public void info(View view) {
        Intent i = new Intent(MainActivity.this, Panduan.class);
        startActivity(i);
    }
}
