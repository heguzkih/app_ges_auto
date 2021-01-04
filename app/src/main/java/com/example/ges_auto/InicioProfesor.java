package com.example.ges_auto;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ges_auto.modelo.Profesor;

public class InicioProfesor extends AppCompatActivity {

    TextView txtNombre,txtPApellido,txtSApellido,txtPermisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesor);
        Profesor profesor =(Profesor)getIntent().getExtras().getSerializable("profesor");
        txtNombre= findViewById(R.id.textnombre);
        txtPApellido=findViewById(R.id.textprimerapellido);
        txtSApellido=findViewById(R.id.textsegundoapellido);
        txtPermisos=findViewById(R.id.textpermisoso);
        //seteamos los  datos
        txtNombre.setText( profesor.getNombre().toUpperCase());
        txtPApellido.setText( profesor.getPrimer_apellido().toUpperCase());
        txtSApellido.setText( profesor.getSegundo_apellido().toUpperCase());
        txtPermisos.setText( profesor.getPermisos().toString().toUpperCase());

    }
}