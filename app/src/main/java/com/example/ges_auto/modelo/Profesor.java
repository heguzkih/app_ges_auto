package com.example.ges_auto.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * modelo de datos para tconversion en POJO
 */
public class Profesor implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    private String dni;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private ArrayList permisos;
    private  String pass;
    private  boolean root;

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public ArrayList getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return
                "Profesor '" + dni + ' '
                        + nombre + " " +primer_apellido;
    }
}
