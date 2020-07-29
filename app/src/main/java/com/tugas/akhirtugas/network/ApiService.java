package com.tugas.akhirtugas.network;


import com.tugas.akhirtugas.model.berita.ResponseBerita;
import com.tugas.akhirtugas.model.crud.ResponseCrud;
import com.tugas.akhirtugas.model.cuaca.ResponseWeather;
import com.tugas.akhirtugas.model.kecamatan.ResponseKecamatan;
import com.tugas.akhirtugas.model.login.ResponseLogin;
import com.tugas.akhirtugas.model.longsor.ResponseLongsor;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @GET("read/Kecamatan.php")
    Call<ResponseKecamatan> getKecamatan();

    @GET("read/Berita.php")
    Call<ResponseBerita> getBerita();

    @GET("read/DataLongsor.php")
    Call<ResponseLongsor> getDataLongsor();

    @FormUrlEncoded
    @POST("read/DataLongsor.php")
    Call<ResponseLongsor> getByIdDataLongsor(@Field("id") String id);

    //https://api.openweathermap.org/data/2.5/weather?lat=-7.026253&lon=109.581192&units=metric&appid=cbfdb21fa1793c10b14b6b6d00fbef03
    @GET("weather")
    Call<ResponseWeather> getWeatherKoordinat(@Query("lat") String lat, @Query("lon") String lon,
                                              @Query("units") String units, @Query("appid") String api);

    @FormUrlEncoded
    @POST("read/LoginAdmin.php")
    Call<ResponseLogin> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("create/Berita.php")
    Call<ResponseCrud> createBerita(@Part("judul_berita") String judulBerita,
                                    @Part("deskripsi") String deskripsi,
                                    @Part("tanggal") String tanggal,
                                    @Part MultipartBody.Part image);

    @Multipart
    @POST("update/Berita.php")
    Call<ResponseCrud> updateBeritaImage(@Part("id") int id,
                                         @Part("judul_berita") String judulBerita,
                                         @Part("deskripsi") String deskripsi,
                                         @Part("hapus") String hapus,
                                         @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("update/Berita.php")
    Call<ResponseCrud> updateBeritaField(@Field("id") int id,
                                         @Field("judul_berita") String judulBerita,
                                         @Field("deskripsi") String deskripsi);

    @FormUrlEncoded
    @POST("create/DataLongsor.php")
    Call<ResponseCrud> createDataLongsor(@Field("id_kec") String id_kec,
                                         @Field("jenis_bencana") String jenisBencana,
                                         @Field("tanggal") String tanggal,
                                         @Field("waktu") String waktu,
                                         @Field("lokasi") String lokasi,
                                         @Field("korban") String korban,
                                         @Field("latitude") String latitude,
                                         @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("update/DataLongsor.php")
    Call<ResponseCrud> updateDataLongsor(@Field("id") int id,
                                         @Field("jenis_bencana") String jenisBencana,
                                         @Field("tanggal") String tanggal,
                                         @Field("waktu") String waktu,
                                         @Field("lokasi") String lokasi,
                                         @Field("korban") String korban,
                                         @Field("latitude") String latitude,
                                         @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("delete/Delete.php")
    Call<ResponseCrud> deleted(@Field("tabel") String tabelName, @Field("cari") String tablePrimary,
                               @Field("idTabel") String valueTablePrimary);

    @FormUrlEncoded
    @POST("create/Kecamatan.php")
    Call<ResponseCrud> createKecamatan(@Field("nama") String nama);

    @FormUrlEncoded
    @POST("update/Kecamatan.php")
    Call<ResponseCrud> updateKecamatan(@Field("id") String id, @Field("nama") String nama);
}
