package com.tugas.akhirtugas.DataLongsor.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal;

public class DetailLongsor extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txt_jenis_bencana)
    TextView txtJenisBencana;
    @BindView(R.id.tgl_waktu)
    TextView tglWaktu;
    @BindView(R.id.txt_lokasi)
    TextView txtLokasi;
    @BindView(R.id.txt_korban)
    TextView txtKorban;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    DataLongsorItem data;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_longsor);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        UI();

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

    private void UI() {
        data = getIntent().getParcelableExtra(DATA);

        if (data != null) {
            String title = data.getJenisBencana();
            getSupportActionBar().setTitle(title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase());

            txtJenisBencana.setText("Jenis Bencanca : " + data.getJenisBencana());
            tglWaktu.setText("Tanggal : " + ubahTanggal(data.getTanggal())
                    + "\n Waktu : " + data.getWaktu() + " WIB"
            );
            txtLokasi.setText("Lokasi : " + data.getLokasi());
            txtKorban.setText("Korban : " + data.getKorban() + " Jiwa");

            fab.setOnClickListener(view -> {
                try {
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + data.getLatitude() + "," + data.getLongitude() + "");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } catch (Exception e) {
                    Snackbar.make(fab, "Tidak ada aplikasi google maps, silahkan download dulu", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(data.getLatitude()), Double.parseDouble(data.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(sydney).title(data.getLokasi()));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(sydney);
        LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.animateCamera(cu);
    }
}