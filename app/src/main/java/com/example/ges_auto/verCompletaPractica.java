package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ges_auto.modelo.Practica;

import java.text.SimpleDateFormat;

public class verCompletaPractica extends AppCompatActivity {

    TextView dniProfesor,nombreProfesor,nombreAlumno,dniAlumno, fechaInicio,fechaFin,
            permisoPractica, comentarios;

    Button salir;


    Practica practica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_completa_practica);


        Bundle extras = getIntent().getExtras();
        if (extras !=null) practica = (Practica) extras.getSerializable("practicaver");

        nombreAlumno  = findViewById(R.id.DverPracticaNombreAlumno);
        dniAlumno = findViewById(R.id.verePracticadniAlumno);
        nombreProfesor = findViewById(R.id.veretePracticaNombreProfesor);
        dniProfesor = findViewById(R.id.veretePracticadniprofesor);
        fechaInicio= findViewById(R.id.verPracticafechaIicio);
        fechaFin= findViewById(R.id.verPracticafechafin);
        permisoPractica= findViewById(R.id.verPracticapermisosAlumno);
        comentarios = findViewById(R.id.rols);
        salir= findViewById(R.id.botoncalirver);




        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        nombreAlumno.setText(practica.getAlumno().getNombre() + " "+practica.getAlumno().getPrimer_apellido()+
                " "+practica.getAlumno().getSegundo_apellido());
        dniAlumno.setText(practica.getAlumno().getDni());
        nombreProfesor.setText(practica.getProfesor().getNombre()+ " "+practica.getProfesor().getPrimer_apellido()+
                " "+practica.getProfesor().getSegundo_apellido());
        fechaInicio.setText(formatter.format(practica.getFechaInicio()));
        fechaFin.setText(formatter.format(practica.getFechaFin()));
        permisoPractica.setText(practica.getPermiso());
        dniProfesor.setText(practica.getProfesor().getDni());
        comentarios.setText(practica.getComentario());

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}