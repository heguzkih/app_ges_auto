package com.example.ges_auto.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Alumno implements Serializable {


    @SerializedName("_id")
    @Expose
    private String id;
    private String dni ;
    private String nombre ;
    private String primer_apellido;
    private String segundo_apellido;
    private Date fecha_nacimiento;
    private String centro_reconocimiento;
    private String sexo;
    private String lugar_nacimiento;
    private String nacionalidad;
    private boolean lentes;
    private boolean  condiciones_restrictivas;
    private boolean validez_limitada;
    private String  permiso_que_solicita;
    private String direccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCentro_reconocimiento() {
        return centro_reconocimiento;
    }

    public void setCentro_reconocimiento(String centro_reconocimiento) {
        this.centro_reconocimiento = centro_reconocimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugar_nacimiento() {
        return lugar_nacimiento;
    }

    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isLentes() {
        return lentes;
    }

    public void setLentes(boolean lentes) {
        this.lentes = lentes;
    }

    public boolean isCondiciones_restrictivas() {
        return condiciones_restrictivas;
    }

    public void setCondiciones_restrictivas(boolean condiciones_restrictivas) {
        this.condiciones_restrictivas = condiciones_restrictivas;
    }

    public boolean isValidez_limitada() {
        return validez_limitada;
    }

    public void setValidez_limitada(boolean validez_limitada) {
        this.validez_limitada = validez_limitada;
    }

    public String getPermiso_que_solicita() {
        return permiso_que_solicita;
    }

    public void setPermiso_que_solicita(String permiso_que_solicita) {
        this.permiso_que_solicita = permiso_que_solicita;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
