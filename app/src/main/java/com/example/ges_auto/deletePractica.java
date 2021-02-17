package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Practica;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.text.SimpleDateFormat;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class deletePractica extends AppCompatActivity {

    TextView dniProfesor,nombreProfesor,nombreAlumno,dniAlumno, fechaInicio,fechaFin,
            permisoPractica;

    Button borrarpractica;
    private MisPreferencias misPreferencias;

    Practica practica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_practica);

        nombreAlumno  = findViewById(R.id.DeletePracticaNombreAlumno);
        dniAlumno = findViewById(R.id.DeletePracticadniAlumno);
        nombreProfesor = findViewById(R.id.DeletePracticaNombreProfesor);
        dniProfesor = findViewById(R.id.DeletePracticadniprofesor);
        fechaInicio= findViewById(R.id.DeletePracticafechaIicio);
        fechaFin= findViewById(R.id.DeletePracticafechafin);
        permisoPractica= findViewById(R.id.DeletePracticapermisosAlumno);
        borrarpractica= findViewById(R.id.botonDeletePractica);

        Bundle extras = getIntent().getExtras();
        if (extras !=null) practica = (Practica) extras.getSerializable("practicadel");

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

        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

        borrarpractica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                        .createService(Cliente.class).borrarPractica(practica.getId(), misPreferencias.getToken().getSucces());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {

                            Toast.makeText(getApplicationContext(), "borrado  correcto", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Problemas en el borrado ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Problemas en la conexion ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}