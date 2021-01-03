package com.example.ges_auto.autentificacion;


import com.example.ges_auto.modelo.Profesor;

import java.util.ArrayList;

public class autentificar {

    private String nombre;
    private  String dni;
    private ArrayList profesores;
    private  String pass;

    public autentificar(String dni, ArrayList profesores, String pass) {
        this.dni = dni;
        this.profesores = profesores;
        this.pass = pass;
    }

    public  boolean atentico (){
        boolean flag =false;

        for ( int i=0 ;i < profesores.size(); i++) {
            Profesor prof= (Profesor) profesores.get(i);
            if (prof.getDni()==dni & prof.getPass()==pass){
                flag=true;
            }

        }
         return flag;
    }
}
