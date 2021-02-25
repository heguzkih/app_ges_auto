package com.example.ges_auto.servicio;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Practica;
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

/**
 * genera las lllamadas en si
 */
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


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @POST("/profesor")
    Call <Void>crearProfesor( @Body Profesor profesor,
                                  @Header("profesor-login") String tock);


    /*
    peticiones alumnos
     */

    @GET("/alumno")
    Call <List<Alumno>> verTodosAlumnos(@Header("profesor-login") String tock);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @POST("/alumno")
    Call <Void>crearAlumno( @Body Alumno alumno,
                              @Header("profesor-login") String tock);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @PUT("/alumno/{dni}")
    Call <Void>modificaAlumno(@Path("dni") String id, @Body Alumno alumno,
                            @Header("profesor-login") String tock);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @DELETE("/alumno/{id}")
    Call <Void>borrarAlumno(@Path("id") String id,
                              @Header("profesor-login") String tock);


    /*
    practicas
     */

    @GET("/practica")
    Call <List<Practica>> verTodasPracticas (@Header("profesor-login") String tock);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @POST("/practica")
    Call <Void>crearPractica( @Body Practica practica,
                            @Header("profesor-login") String tock);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @DELETE("/practica/{id}")
    Call <Void>borrarPractica(@Path("id") String id,
                            @Header("profesor-login") String tock);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("/practica/{id}")
    Call<Practica>verPractica(@Path("id") String dni,
                              @Header("profesor-login") String tock);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @PUT("/practica/{id}")
    Call <Void>modificapractica(@Path("id") String id, @Body Practica practica,
                              @Header("profesor-login") String tock);





}







