package com.tugas.akhirtugas.Cuaca;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import com.google.android.material.snackbar.Snackbar;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.cuaca.ResponseWeather;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.InitWeather;
import com.tugas.akhirtugas.utils.Function;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Function.cuaca;

public class Cuaca extends AppCompatActivity {

    @BindView(R.id.img_cover)
    ImageView imgCover;
    @BindView(R.id.bg)
    TextView bg;
    @BindView(R.id.txt_cuaca)
    TextView txtCuaca;
    @BindView(R.id.txt_ket_cuaca)
    TextView txtKetCuaca;
    @BindView(R.id.guideline3)
    Guideline guideline3;
    @BindView(R.id.txt_derajat)
    TextView txtDerajat;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.txt_lokasi)
    TextView txtLokasi;
    @BindView(R.id.pd_cuaca)
    ProgressBar pdCuaca;
    //weather icon
    Typeface weatherFont;
    String latitude, longitude, API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Cuaca");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        latitude = "-6.888701";
        longitude = "109.668289";
        API = "e5d41e9cfc82c5999f231cf50a43749a";

        getWeather(latitude, longitude, "metric", API);
        //fonts
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");
        txtCuaca.setTypeface(weatherFont);
    }

    private void getWeather(String latitude, String longitude, String units, String api) {
        pdCuaca.setVisibility(View.VISIBLE);
        ApiService apiService = InitWeather.getApiService();
        Call<ResponseWeather> call = apiService.getWeatherKoordinat(latitude, longitude, units, api);

        call.enqueue(new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                //Log.e("Tag", "Hasil List :" + new Gson().toJson(response));
                try {
                    if (response.body().getCod() == 200) {
                        //keterangan
                        txtKetCuaca.setText(cuaca(response.body().getWeather().get(0).getId()));
                        //icon
                        txtCuaca.setText(Html.fromHtml(Function.setWeatherIcon(response.body().getWeather().get(0).getId(),
                                response.body().getSys().getSunrise() * 1000,
                                response.body().getSys().getSunset() * 1000)));
                        //derajat
                        int cuaca = (int) response.body().getMain().getTemp();
                        //txtDerajat.setText(String.format("%.2f", response.body().getMain().getTemp())+ " ℃");
                        txtDerajat.setText(String.valueOf(cuaca) + " ℃");
                        pdCuaca.setVisibility(View.GONE);
                        txtLokasi.setText(response.body().getName());
                    } else {
                        Log.e("Tag", "Gagal req data ");
                        pdCuaca.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Toast.makeText(Cuaca.this, "Cuaca tidak ditemukan", Toast.LENGTH_SHORT).show();
                    pdCuaca.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                pdCuaca.setVisibility(View.GONE);
                Snackbar.make(pdCuaca, "Tidak Ada Koneksi internet", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ulangi", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //cuaca
                                getWeather(latitude, longitude, "metric", API);
                            }
                        }).show();
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
}