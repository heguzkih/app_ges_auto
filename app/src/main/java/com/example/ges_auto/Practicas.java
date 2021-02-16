package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

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
    List<Practica> listaDatos;
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


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Practica>();
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

                    AdaptadorReclicerPracticas adapter= new AdaptadorReclicerPracticas(listaDatos);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });




    }
}