package com.tugas.akhirtugas.network;


import com.tugas.akhirtugas.model.berita.ResponseBerita;
import com.tugas.akhirtugas.model.longsor.ResponseLongsor;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("read/Berita.php")
    Call<ResponseBerita> getBerita();

    @GET("read/DataLongsor.php")
    Call<ResponseLongsor> getDataLongsor();
   /*
    @FormUrlEncoded
    @POST("read/LoginUser.php")
    Call<ResponseLogin> login(@Field("email") String email, @Field("pass") String password);

    @FormUrlEncoded
    @POST("read/Pengajuan.php")
    Call<ResponsePengajuan> listPengajuan(@Field("id") String idPengguna);

    @Multipart
    @POST("create/pengajuans.php")
    Call<ResponseInsert> insertPengajuan1(@Part("penggunaId") int penggunaId,
                                          @Part("jenisKendaraan") String jenisKendaraan,
                                          @Part("namaKendaraan") String namaKendaraan,
                                          @Part("tahun") String tahun,
                                          @Part("noKerangka") int noKerangka,
                                          @Part("noMesin") String noMesin,
                                          @Part MultipartBody.Part bpkb,
                                          @Part MultipartBody.Part stnk,
                                          @Part("besaranPinjaman") int besaranPinjaman,
                                          @Part("angsuran") int angsuran);

    @Multipart
    @POST("create/User.php")
    Call<ResponseCrud> register(@Part("nik") int nik,
                                @Part("nama") String nama,
                                @Part("alamat") String alamat,
                                @Part("email") String email,
                                @Part("pass") String pass,
                                @Part MultipartBody.Part image);*/
}
