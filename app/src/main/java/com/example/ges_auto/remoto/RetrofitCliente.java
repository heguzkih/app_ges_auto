
        package com.example.ges_auto.remoto;

import android.content.Context;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import preferencias.MisPreferencias;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class RetrofitCliente {

    private static String BASE_URL;
    private  static HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private static Retrofit retrofit ;
    private   static  RetrofitCliente instance;



    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    private  RetrofitCliente(Context context){


        httpClientBuilder = new  OkHttpClient.Builder();
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public  static  synchronized  RetrofitCliente getInstance(Context context){

            instance = new RetrofitCliente(context);

        return  instance;
    }

    public  <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }



}
