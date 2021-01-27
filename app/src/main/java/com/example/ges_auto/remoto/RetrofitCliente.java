package com.example.ges_auto.remoto;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import preferencias.GestorPreferencia;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente {

    Context context = getActivity();

    private Context getActivity() {
        return context;
    }

    private  String BASE_URL = GestorPreferencia.getInstance(context).getServidor();


    private  static HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private static Retrofit retrofit ;
    private   static  RetrofitCliente instance;

    private  RetrofitCliente(){
        httpClientBuilder = new  OkHttpClient.Builder();
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  static  synchronized  RetrofitCliente getInstance(){
        if(instance == null){
            instance = new RetrofitCliente();
        }
        return  instance;
    }

    public  <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }



}
