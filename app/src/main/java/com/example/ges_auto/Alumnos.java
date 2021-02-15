package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.util.ArrayList;
import java.util.List;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class Alumnos extends AppCompatActivity {

    List<Alumno> listaDatos;
    RecyclerView recyclerView;
    private MisPreferencias misPreferencias;
    Token tock;
    Button crearAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerAlumno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Alumno>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();

        crearAlumno=  findViewById(R.id.buttonCrearAlumno);

        crearAlumno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),CrearAlumno.class);

                startActivity(intent);
            }
        });


        // llamada a retrofit
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Alumno>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosAlumnos(tock.getSucces());

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if (response.code()==200){

                    listaDatos = response.body();

                    AdaptadorReclicerAlumno adapter= new AdaptadorReclicerAlumno(listaDatos);
                    recyclerView.setAdapter(adapter);

                }else {

                    Toast.makeText(getApplicationContext(),"problemas en la recuperacion de alumnos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"problemas con la conexion", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerAlumno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Alumno>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();

        crearAlumno=  findViewById(R.id.buttonCrearAlumno);

        crearAlumno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                

                Intent intent = new Intent(getApplicationContext(),CrearAlumno.class);

                startActivity(intent);
            }
        });


        // llamada a retrofit
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Alumno>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosAlumnos(tock.getSucces());

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if (response.code()==200){

                    listaDatos = response.body();

                    AdaptadorReclicerAlumno adapter= new AdaptadorReclicerAlumno(listaDatos);
                    recyclerView.setAdapter(adapter);

                }else {

                    Toast.makeText(getApplicationContext(),"problemas en la recuperacion de alumnos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"problemas con la conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
}