package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;

import java.util.List;

import preferencias.MisPreferencias;

public class Practicas extends AppCompatActivity {
    Profesor profesor;
    List<Practicas> listaDatos;
    RecyclerView recyclerView;

    private MisPreferencias misPreferencias;
    Token tock;
    Button crearAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicas);


        Bundle extras = getIntent().getExtras();

        if (extras !=null) profesor = (Profesor) extras.getSerializable("profesor");




    }
}