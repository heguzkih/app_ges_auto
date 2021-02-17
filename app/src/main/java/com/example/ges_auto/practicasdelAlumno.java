package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Practica;
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

public class practicasdelAlumno extends AppCompatActivity {

    Alumno alumno;
    List<Practica> listaDatos , listaDatosPracAlumn;
    RecyclerView recyclerView;
    private MisPreferencias misPreferencias;
    Token tock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicasdel_alumno);


        Bundle extras = getIntent().getExtras();

        if (extras !=null) alumno = (Alumno) extras.getSerializable("alumnopra");


        recyclerView = (RecyclerView) findViewById(R.id.recilcerviwerpracticaalumno);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listaDatos =new ArrayList<Practica>();
        listaDatosPracAlumn =new ArrayList<Practica>();
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

                        if(p.getAlumno().getId().equals( alumno.getId())){
                            listaDatosPracAlumn.add(p);
                        }

                    }

                    AdaptadorReclicerPracticas adapter= new AdaptadorReclicerPracticas(listaDatosPracAlumn);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        listaDatos =new ArrayList<Practica>();
        listaDatosPracAlumn =new ArrayList<Practica>();

        Call<List<Practica>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodasPracticas(tock.getSucces());


        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if(response.code()==200) {
                    // Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();


                    listaDatos = response.body();

                    for(Practica p :listaDatos){

                        if(p.getAlumno().getId().equals( alumno.getId())){
                            listaDatosPracAlumn.add(p);
                        }

                    }

                    AdaptadorReclicerPracticas adapter= new AdaptadorReclicerPracticas(listaDatosPracAlumn);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });
    }
}