package com.example.ges_auto.modelo;

import java.util.ArrayList;

public class Profesor {
    private String dni;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private ArrayList permisos;

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
}
