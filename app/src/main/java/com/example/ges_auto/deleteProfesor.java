package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Practica;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.util.ArrayList;
import java.util.List;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class deleteProfesor extends AppCompatActivity {
    TextView dniProfesor, nombreProfesor, apellidounoprofesor, apellidodosprofesor, permisosprofesor;
    Button  eliminar;
    Profesor profesor;
    MisPreferencias misPreferencias;
    List<Practica> practiasBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_profesor);


        Bundle extras = getIntent().getExtras();

       if (extras !=null) profesor = (Profesor) extras.getSerializable("profesordel");


       dniProfesor = findViewById(R.id.Createdni);
       nombreProfesor= findViewById(R.id.CreateNombre);
        apellidounoprofesor= findViewById(R.id.CreatePrimerApellido);
        apellidodosprofesor = findViewById(R.id.CreateSegundoApellido);
        permisosprofesor= findViewById(R.id.Createpermisos);

       misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

        dniProfesor.setText(getText(R.string.Dni) +" "+profesor.getDni());
        nombreProfesor.setText( getText(R.string.nombre)+" "+profesor.getNombre());
        apellidodosprofesor.setText(getText(R.string.SegundoApellido )+" "+profesor.getSegundo_apellido());
        apellidounoprofesor.setText(getText(R.string.PrimerApellido)+" "+profesor.getPrimer_apellido());
        permisosprofesor.setText(getText(R.string.permisos) +" "+profesor.getPermisos().toString());

        eliminar = findViewById(R.id.buttonCreatePractica);


        Toast.makeText(getApplicationContext(), getText(R.string.mensadgeborradoprofesor), Toast.LENGTH_LONG).show();

        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Practica>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodasPracticas(misPreferencias.getToken().getSucces());

        practiasBorrar = new ArrayList<Practica>();

        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if (response.code() == 200) {

                    for(Practica p : response.body()){

                        if(p.getProfesor().getId().equals(profesor.getId())) practiasBorrar.add(p);
                    }
                    Toast.makeText(getApplicationContext(), "Se borraran "+practiasBorrar.size()+"  practicas ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Problemas en carga datos practicas ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problemas en la Conexión ", Toast.LENGTH_LONG).show();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (Practica p: practiasBorrar){

                    RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                    Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                            .createService(Cliente.class).borrarPractica(p.getId(), misPreferencias.getToken().getSucces());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if (response.code() == 200) {

                            } else {
                                Toast.makeText(getApplicationContext(), "Problemas en el borrado ", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Problemas en la Conexión ", Toast.LENGTH_LONG).show();

                        }
                    });


                }




                RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                        .createService(Cliente.class).borrarProfesor(profesor.getDni(),misPreferencias.getToken().getSucces());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200){

                            Toast.makeText(getApplicationContext(),"Borrado correcto",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Problemas en el Borrado ",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Problemas en  la Conexión ", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });




    }
}