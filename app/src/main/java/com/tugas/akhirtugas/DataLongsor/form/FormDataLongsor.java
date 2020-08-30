package com.tugas.akhirtugas.DataLongsor.form;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tugas.akhirtugas.DataLongsor.maps.MapsActivity;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.model.crud.ResponseCrud;
import com.tugas.akhirtugas.model.longsor.DataLongsorItem;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.date.ConvertDate.changeDateServer;
import static com.tugas.akhirtugas.utils.date.ConvertDate.ubahTanggal2;

public class FormDataLongsor extends AppCompatActivity implements Callback<ResponseCrud> {
    @BindView(R.id.jenis_bencana)
    TextInputEditText jenisBencana;
    @BindView(R.id.korban)
    TextInputEditText korban;
    @BindView(R.id.btn_tgl)
    Button btnTgl;
    @BindView(R.id.btn_waktu)
    Button btnWaktu;
    @BindView(R.id.latitude)
    TextInputEditText latitude;
    @BindView(R.id.longitude)
    TextInputEditText longitude;
    @BindView(R.id.Lokasi)
    TextInputEditText lokasi;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    boolean flagUpdate = false;
    DataLongsorItem data;
    Calendar myCalendar;
    TimePickerDialog timePickerDialog;
    String idKec;
    @BindView(R.id.penduduk)
    TextInputEditText penduduk;
    @BindView(R.id.kejadian)
    TextInputEditText kejadian;
    @BindView(R.id.penanggulangan)
    TextInputEditText penanggulangan;
    @BindView(R.id.radius)
    TextInputEditText radius;
    @BindView(R.id.bil)
    TextInputEditText bil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_longsor);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Form Data Longsor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCalendar = Calendar.getInstance();
        UI();
    }

    private void UI() {
        data = getIntent().getParcelableExtra(DATA);
        if (data != null) {
            flagUpdate = true;
            btnSimpan.setText("Ubah");

            jenisBencana.setText("" + data.getJenisBencana());
            korban.setText("" + data.getKorban());
            latitude.setText("" + data.getLatitude());
            longitude.setText("" + data.getLongitude());
            lokasi.setText("" + data.getLokasi());
            btnTgl.setText("" + ubahTanggal2(data.getTanggal()));
            btnWaktu.setText("" + data.getWaktu());
            penduduk.setText("" + data.getPenduduk());
            kejadian.setText("" + data.getKejadian());
            penanggulangan.setText("" + data.getPenanggulangan());
        }

        idKec = getIntent().getStringExtra("idKec");
    }


    @OnClick({R.id.btn_tgl, R.id.btn_waktu, R.id.btn_simpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tgl:
                showDate();
                break;
            case R.id.btn_waktu:
                showTime();
                break;
            case R.id.btn_simpan:
                if (flagUpdate) {
                    update(jenisBencana.getText().toString(), changeDateServer(btnTgl.getText().toString()),
                            btnWaktu.getText().toString(), lokasi.getText().toString(), korban.getText().toString(),
                            latitude.getText().toString(), longitude.getText().toString(),
                            penduduk.getText().toString(), kejadian.getText().toString(),
                            penanggulangan.getText().toString(), radius.getText().toString(),
                            bil.getText().toString());
                } else {
                    //created new news
                    created(jenisBencana.getText().toString(), changeDateServer(btnTgl.getText().toString()),
                            btnWaktu.getText().toString(), lokasi.getText().toString(), korban.getText().toString(),
                            latitude.getText().toString(), longitude.getText().toString(),
                            penduduk.getText().toString(), kejadian.getText().toString(), penanggulangan.getText().toString(),
                            radius.getText().toString(), bil.getText().toString());
                }
                break;
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

    private void showDate() {
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String formatTanggal = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
            Toast.makeText(FormDataLongsor.this, ubahTanggal2(sdf.format(myCalendar.getTime())), Toast.LENGTH_SHORT).show();
            btnTgl.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
        },
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTime() {
        timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            /**
             * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
             */
            Toast.makeText(FormDataLongsor.this, "Waktu dipilih = " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            btnWaktu.setText("" + hourOfDay + ":" + minute);
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private void created(String jenisBencana, String tanggal, String waktu, String lokasi,
                         String korban, String latitude, String longitude,
                         String penduduk, String kejadian, String penanggulangan, String radius, String bil) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.createDataLongsor(idKec, jenisBencana, tanggal, waktu,
                lokasi, korban, latitude, longitude, penduduk, kejadian, penanggulangan, radius, bil);
        call.enqueue(this);
    }

    private void update(String jenisBencana, String tanggal, String waktu, String lokasi,
                        String korban, String latitude, String longitude, String penduduk, String kejadian,
                        String penanggulangan, String radius, String bil) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.updateDataLongsor(Integer.parseInt(data.getIdBencana()),
                jenisBencana, tanggal, waktu, lokasi, korban, latitude, longitude, penduduk, kejadian,
                penanggulangan, radius, bil);
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
                    deleted("data_longsor", "id_bencana", data.getIdBencana());
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
            Toast.makeText(FormDataLongsor.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(FormDataLongsor.this, MapsActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra("idKec", idKec));
            //hancurkan activity
            finish();
        } else {
            Toast.makeText(FormDataLongsor.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseCrud> call, Throwable t) {
        Toast.makeText(FormDataLongsor.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("error ", t.getMessage());
    }
}