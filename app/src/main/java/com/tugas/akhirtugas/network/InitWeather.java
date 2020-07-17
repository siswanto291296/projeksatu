package com.tugas.akhirtugas.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitWeather {
    private static Retrofit retrofit = null;

    private static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //cek jika retrofit null
        if (retrofit == null){
            //maka buat object dari retrofit
            retrofit = new Retrofit.Builder()
            //https://api.openweathermap.org/data/2.5/weather?q=kesesirejo&units=metric&appid=cbfdb21fa1793c10b14b6b6d00fbef03
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService(){
        return getClient().create(ApiService.class);
    }
}
