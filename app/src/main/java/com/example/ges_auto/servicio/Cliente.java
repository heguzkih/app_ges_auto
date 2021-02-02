package com.example.ges_auto.servicio;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Cliente {

    @POST("/loguin")
    Call <Token>loguin(@Body Profesor profesor);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("/profesor/{dni}")
    Call<Profesor>verProfesor(@Path("dni") String dni,
                              @Header("profesor-login") String tock);

}
