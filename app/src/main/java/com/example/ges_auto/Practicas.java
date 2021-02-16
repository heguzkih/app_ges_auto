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
import com.example.ges_auto.modelo.Practica;
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

public class Practicas extends AppCompatActivity {
    Profesor profesor;
    List<Practica> listaDatos , listaDatosProfesor;
    RecyclerView recyclerView;

    private MisPreferencias misPreferencias;
    Token tock;
    Button crearPractica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicas);
        crearPractica = findViewById(R.id.buttonCrearpractica);

        Bundle extras = getIntent().getExtras();

        if (extras !=null) profesor = (Profesor) extras.getSerializable("profesor");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerpractica);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Practica>();
        listaDatosProfesor =new ArrayList<Practica>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();

        Call<List<Practica>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodasPracticas(tock.getSucces());


        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if(response.code()==200) {
                    // Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();


                    listaDatos = response.body();

                    for(Practica p :listaDatos){

                        if(p.getProfesor().getId() == profesor.getId()){
                            listaDatosProfesor.add(p);
                        }

                    }

                    AdaptadorReclicerPracticas adapter= new AdaptadorReclicerPracticas(listaDatosProfesor);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });


        crearPractica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CrearPractica.class);
                intent.putExtra("profesor",profesor);
                startActivity(intent);
            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();


        listaDatosProfesor =new ArrayList<Practica>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();

        Call<List<Practica>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodasPracticas(tock.getSucces());


        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if(response.code()==200) {
                    // Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();


                    listaDatos = response.body();

                    for(Practica p :listaDatos){
                        Toast.makeText(getApplicationContext(), p.getProfesor().getNombre(), Toast.LENGTH_LONG).show();
                        if(p.getProfesor().getId().equals(profesor.getId())){
                            listaDatosProfesor.add(p);
                        }

                    }

                    AdaptadorReclicerPracticas adapter= new AdaptadorReclicerPracticas(listaDatosProfesor);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });

    }
}