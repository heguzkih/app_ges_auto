package com.example.ges_auto.servicio;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/profesor")
    Call <List<Profesor>> verTodosProfesores (@Header("profesor-login") String tock);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @PUT("/profesor/{dni}")
    Call <Void>actualizarProfesor(@Path("dni") String dni,
                                  @Body Profesor profesor,
                                  @Header("profesor-login") String tock);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @DELETE("/profesor/{dni}")

    Call <Void>borrarProfesor(@Path("dni") String id,
                                  @Header("profesor-login") String tock);




}







