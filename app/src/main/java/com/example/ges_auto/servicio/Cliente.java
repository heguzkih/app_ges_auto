package com.example.ges_auto.servicio;

import com.example.ges_auto.modelo.Profesor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Cliente {

    @GET ("/profesor")
    Call<List<Profesor>> getReposUser();
}
