package com.tugas.akhirtugas.Berita;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.tugas.akhirtugas.Berita.detail.DetailBerita;
import com.tugas.akhirtugas.R;
import com.tugas.akhirtugas.menu.MainActivity;
import com.tugas.akhirtugas.model.berita.BeritaItem;
import com.tugas.akhirtugas.model.crud.ResponseCrud;
import com.tugas.akhirtugas.network.ApiService;
import com.tugas.akhirtugas.network.RetroClient;
import com.tugas.akhirtugas.utils.takeimage.TakeImage;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tugas.akhirtugas.network.RetroClient.BASE_URL_IMAGE;
import static com.tugas.akhirtugas.session.Session.SP_SUDAH_LOGIN2;
import static com.tugas.akhirtugas.utils.Contans.DATA;
import static com.tugas.akhirtugas.utils.date.ConvertDate.currentDate;

public class FormBerita extends AppCompatActivity {

    @BindView(R.id.ib_foto)
    ImageButton ibFoto;
    @BindView(R.id.judul)
    TextInputEditText judul;
    @BindView(R.id.deskripsi)
    TextInputEditText deskripsi;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    boolean flagUpdate = false;
    BeritaItem data;
    TakeImage takeImage;
    String partImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_berita);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Form Berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        takeImage = new TakeImage(this);

        //permission akses camera, open gallery
        takeImage.requestMultiplePermissions();
        UI();
    }

    private void UI() {
        data = getIntent().getParcelableExtra(DATA);
        if (data != null) {
            flagUpdate = true;
            btnSimpan.setText("Ubah");

            if (data.getFoto() != null) {
                Glide.with(FormBerita.this)
                        .load(BASE_URL_IMAGE + data.getFoto())
                        .into(ibFoto);
            }
            judul.setText(data.getJudulBerita());
            deskripsi.setText(data.getDeskripsi());
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

    @OnClick({R.id.ib_foto, R.id.btn_simpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_foto:
                takeImage.showPictureDialog();
                break;
            case R.id.btn_simpan:
                //flage update or view
                if (flagUpdate) {
                    if (partImage == null) {
                        //update field
                        updateField(Integer.parseInt(data.getIdBerita()), judul.getText().toString(),
                                deskripsi.getText().toString());
                    } else {
                        //update image
                        updateImage(Integer.parseInt(data.getIdBerita()), judul.getText().toString(),
                                deskripsi.getText().toString(), partImage);
                    }
                } else {
                    //created new news
                    created(judul.getText().toString(), deskripsi.getText().toString(),
                            currentDate(), partImage);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == takeImage.getGALLERY()) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    takeImage.setPartImage(takeImage.saveImage(bitmap));
                    partImage = takeImage.saveImage(bitmap);
                    ibFoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == takeImage.getCAMERA()) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ibFoto.setImageBitmap(thumbnail);
            partImage = takeImage.saveImage(thumbnail);
            takeImage.setPartImage(takeImage.saveImage(thumbnail));
            //Toast.makeText(Regist.this, "Image Saved! " + thumbnail, Toast.LENGTH_SHORT).show();
        }
    }

    private void created(String judulBerita, String deskripsi, String tanggal, String image) {
        //bpkb
        File file = new File(image);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        //parm 1 samakan dengan parameter dibackend
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.createBerita(judulBerita, deskripsi, tanggal, partImage);
        call.enqueue(new Callback<ResponseCrud>() {
            @Override
            public void onResponse(Call<ResponseCrud> call, Response<ResponseCrud> response) {
                if (response.body().isResponse()) {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(FormBerita.this, BeritaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCrud> call, Throwable t) {
                Toast.makeText(FormBerita.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void updateImage(int id, String judulBerita, String deskripsi, String image) {
        //bpkb
        File file = new File(image);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        //parm 1 samakan dengan parameter dibackend
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.updateBeritaImage(id, judulBerita, deskripsi, data.getFoto(), partImage);
        call.enqueue(new Callback<ResponseCrud>() {
            @Override
            public void onResponse(Call<ResponseCrud> call, Response<ResponseCrud> response) {
                if (response.body().isResponse()) {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(FormBerita.this, BeritaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCrud> call, Throwable t) {
                Toast.makeText(FormBerita.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void updateField(int id, String judulBerita, String deskripsi) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.updateBeritaField(id, judulBerita, deskripsi);
        call.enqueue(new Callback<ResponseCrud>() {
            @Override
            public void onResponse(Call<ResponseCrud> call, Response<ResponseCrud> response) {
                if (response.body().isResponse()) {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(FormBerita.this, BeritaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCrud> call, Throwable t) {
                Toast.makeText(FormBerita.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void deleted(String tableName, String tablePrimary, String valueID) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCrud> call = service.deleted(tableName, tablePrimary, valueID);
        call.enqueue(new Callback<ResponseCrud>() {
            @Override
            public void onResponse(Call<ResponseCrud> call, Response<ResponseCrud> response) {
                if (response.body().isResponse()) {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(FormBerita.this, BeritaActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(FormBerita.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCrud> call, Throwable t) {
                Toast.makeText(FormBerita.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void alertDeleted() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
        //settting judul dan pesan
        aleBuilder.setTitle("Hapus Data");
        aleBuilder
                .setMessage("Apakah anda yakin ingin menghapus data ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, which) -> {
                    deleted("berita", "id_berita", data.getIdBerita());
                })
                .setNegativeButton("Tidak", (dialog, id1) -> {
                    //cancel
                    dialog.cancel();
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }
}