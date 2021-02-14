package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_profesor);


        Bundle extras = getIntent().getExtras();

       if (extras !=null) profesor = (Profesor) extras.getSerializable("profesordel");


       dniProfesor = findViewById(R.id.deldni);
       nombreProfesor= findViewById(R.id.delNombre);
        apellidounoprofesor= findViewById(R.id.delPrimerApellido);
        apellidodosprofesor = findViewById(R.id.delSegundoApellido);
        permisosprofesor= findViewById(R.id.delepermisos);

       misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

       dniProfesor.setText(profesor.getId());
        nombreProfesor.setText(profesor.getNombre());
        apellidodosprofesor.setText(profesor.getSegundo_apellido());
        apellidounoprofesor.setText(profesor.getPrimer_apellido());
        permisosprofesor.setText(profesor.getPermisos().toString());

        eliminar = findViewById(R.id.botondeleter);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    }
                });

            }
        });




    }
}