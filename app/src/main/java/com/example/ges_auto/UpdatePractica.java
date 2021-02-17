package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Practica;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class UpdatePractica extends AppCompatActivity {

    EditText fechaInicio, permiso, fechaFin,comentario;
    Spinner alumnospiner, profesorspiner;
    Button updatePracticas;
    Profesor profesor;
    private MisPreferencias misPreferencias;
    Practica practica;
    List<Alumno> listaralumnos;
    List<Profesor> listaProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_practica);



        alumnospiner = findViewById(R.id.spinnerAlmunoUpdate);
        updatePracticas = findViewById(R.id.buttonUpdatePractica);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        fechaInicio = findViewById(R.id.UpdateeditTextDateinicio);
        permiso = findViewById(R.id.UpdatepermisosPractica);
        fechaFin= findViewById(R.id.UpdateeditTextDateFin);
        comentario = findViewById(R.id.UpdateeditTextTextMultiLineComentario);

        profesorspiner = findViewById(R.id.spinnerProfesorUpdate);

        Bundle extras = getIntent().getExtras();

        if (extras !=null) practica = (Practica) extras.getSerializable("practica");

        fechaFin.setText(formatter.format(practica.getFechaFin()));
        fechaInicio.setText(formatter.format(practica.getFechaInicio()));
        permiso.setText(practica.getPermiso());
        comentario.setText(practica.getComentario());



        listaralumnos = new ArrayList<Alumno>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());
        Call<List<Alumno>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosAlumnos( misPreferencias.getToken().getSucces());

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if (response.code()==200){

                    alumnospiner.setAdapter(new ArrayAdapter<Alumno>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,response.body()));

                }else {

                    Toast.makeText(getApplicationContext(),"problemas en la recuperacion de alumnos", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"problemas en la Conexion", Toast.LENGTH_LONG).show();
            }
        });




        listaProfesores = new ArrayList<Profesor>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());
        Call<List<Profesor>> call1 = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosProfesores( misPreferencias.getToken().getSucces());

        call1.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if (response.code()==200){

                    profesorspiner.setAdapter(new ArrayAdapter<Profesor>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,response.body()));

                }else {

                    Toast.makeText(getApplicationContext(),"problemas en la recuperacion de profesores", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call1, Throwable t) {
                Toast.makeText(getApplicationContext(),"problemas en la Conexion", Toast.LENGTH_LONG).show();
            }
        });


        updatePracticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    practica.setAlumno((Alumno) alumnospiner.getSelectedItem());
                    practica.setProfesor((Profesor) profesorspiner.getSelectedItem());
                    practica.setComentario(comentario.getText().toString());
                    Date fechaf = formatter.parse(fechaFin.getText().toString());
                    Date  fechai = formatter.parse(fechaInicio.getText().toString());
                    practica.setFechaFin(fechaf);
                    practica.setFechaInicio(fechai);
                    practica.setPermiso(permiso.getText().toString().trim());

                    RetrofitCliente.setBaseUrl(misPreferencias.getServidor());
                    Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                            .createService(Cliente.class).modificapractica(practica.getId(),practica,misPreferencias.getToken().getSucces());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==200){
                                Toast.makeText(getApplicationContext(),"practica Guardada", Toast.LENGTH_LONG).show();
                                finish();
                            }else{

                                Toast.makeText(getApplicationContext(),"practica no guardad", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Toast.makeText(getApplicationContext(),"problemas en la Conexion", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Formato fecha incorrecto ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}