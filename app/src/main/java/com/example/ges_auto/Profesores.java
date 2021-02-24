package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class Profesores extends AppCompatActivity {

    List <Profesor>  listaDatos;
    RecyclerView recyclerView;
    private MisPreferencias misPreferencias;
    Token tock;
    Button crearProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Profesor>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();

        crearProfesor= (Button) findViewById(R.id.buttonCrearProfesor);

        crearProfesor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               // Toast.makeText(getApplicationContext(),"creart profe",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),CrearProfesor.class);

                startActivity(intent);
            }
        });


        // llamada a retrofit
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Profesor>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosProfesores(tock.getSucces());

       call.enqueue(new Callback<List<Profesor>>() {
           @Override
           public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.code()==200) {
                   // Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();

                        
                        listaDatos = response.body();

                        AdaptadorReclicerProfesor adapter= new AdaptadorReclicerProfesor(listaDatos);
                        recyclerView.setAdapter(adapter);

                }

           }

           @Override
           public void onFailure(Call<List<Profesor>> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_LONG).show();
           }
       });


    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_profesores);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaDatos =new ArrayList<Profesor>();
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        tock = misPreferencias.getToken();


        crearProfesor= (Button) findViewById(R.id.buttonCrearProfesor);

        crearProfesor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"creart profe",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),CrearProfesor.class);

                startActivity(intent);
            }
        });

        // llamada a retrofit
        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Profesor>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodosProfesores(tock.getSucces());

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.code()==200) {
                    // Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();


                    listaDatos = response.body();

                    AdaptadorReclicerProfesor adapter= new AdaptadorReclicerProfesor(listaDatos);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_LONG).show();
            }
        });

    }
}