package com.tugas.akhirtugas.DataLongsor.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tugas.akhirtugas.DataLongsor.form.FormDataLongsor;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.kecamatan.KecamatanItem;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;
import com.tugas.akhirtugas.model.longsor.ResponseLongsor;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;
import com.tugas.akhirtugas.session.Session;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Contans.DATA;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @BindView(R.id.btn_tambah)
    Button btnTambah;
    private GoogleMap mMap;
    KecamatanItem data;
    String idKec;
    Session sharedLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        UI();
    }

    private void UI() {
        sharedLogin = new Session(this);
        data = getIntent().getParcelableExtra(DATA);
        if (sharedLogin.getSPSudahLogin2()) btnTambah.setVisibility(View.VISIBLE);

        if (data != null) {
            idKec = data.getIdKec();
        }

        if (getIntent().getStringExtra("idKec") != null){
            idKec = getIntent().getStringExtra("idKec");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getDataLongsor(idKec);
    }

    private void getDataLongsor(String id) {
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseLongsor> loginCall = apiService.getByIdDataLongsor(id);
        loginCall.enqueue(new Callback<ResponseLongsor>() {
            @Override
            public void onResponse(Call<ResponseLongsor> call, Response<ResponseLongsor> response) {
                ResponseLongsor ress = response.body();
                try {
                    if (ress.isResponse()) {
                        //listMarker = ress.getDataLongsor();
                        initMarker(ress.getDataLongsor());
                    } else {
                        Toast.makeText(MapsActivity.this, "Data Kosong!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MapsActivity.this, "error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLongsor> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMarker(List<DataLongsorItem> listData) {
        HashMap<Marker, DataLongsorItem> stopsMarkersInfo = new HashMap<>();

        for (int i = 0; i < listData.size(); i++) {
            //set latlng nya
            LatLng location = new
                    LatLng(Double.parseDouble(listData.get(i).getLatitude()),
                    Double.parseDouble(listData.get(i).getLongitude()));
            //tambahkan markernya
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(listData.get(i).getJenisBencana())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.avalanche))
            );
            //set latlng index ke 0
            LatLng latLng = new
                    LatLng(Double.parseDouble(listData.get(0).getLatitude()),
                    Double.parseDouble(listData.get(0).getLongitude()));
            //lalu arahkan zooming ke marker index ke 0
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new
                    LatLng(latLng.latitude, latLng.longitude), 17.0f));
            stopsMarkersInfo.put(marker, listData.get(i));
        }

        mMap.setInfoWindowAdapter(new StopsInfoWindow(stopsMarkersInfo, MapsActivity.this)); // passed the HashMap
    }

    @OnClick(R.id.btn_tambah)
    public void onViewClicked() {
        startActivity(new Intent(MapsActivity.this, FormDataLongsor.class).putExtra("idKec", idKec));
    }
}