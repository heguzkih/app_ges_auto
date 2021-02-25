package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Practica;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.util.ArrayList;
import java.util.List;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class deleteAlumno extends AppCompatActivity {
    TextView dni,nombre,primer_apellido,segundo_apellido,
            sexo,permiso_que_solicita;

    Button borrarAlumno;
    private MisPreferencias misPreferencias;
    Alumno alumno;
    List<Practica> practiasBorrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_alumno);
        Bundle extras = getIntent().getExtras();
        if (extras !=null) alumno = (Alumno) extras.getSerializable("alumnodel");

        dni = findViewById(R.id.DeletedniAlumno);
        nombre = findViewById(R.id.DeleteNombreAlumno1);
        primer_apellido = findViewById(R.id.DeletePrimerApellidoAlumno);
        segundo_apellido   = findViewById(R.id.DeleteSegundoApellidoAlumno);
        permiso_que_solicita = findViewById(R.id.DeletetepermisosAlumnouno);
        sexo  = findViewById(R.id.DeletesexoAlumno);


        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        permiso_que_solicita.setText(getString(R.string.permisos)+" "+alumno.getPermiso_que_solicita());
        dni.setText(getString(R.string.dni)+" "+alumno.getDni());
        nombre.setText(getString(R.string.nombre)+" "+alumno.getNombre());
        primer_apellido.setText(getString(R.string.PrimerApellido)+" "+alumno.getPrimer_apellido());
        segundo_apellido.setText(getString(R.string.SegundoApellido)+" "+alumno.getSegundo_apellido());
        sexo.setText(getString(R.string.sexo)+" "+alumno.getSexo());



        borrarAlumno = findViewById(R.id.botonDeleteAlumno);

        Toast.makeText(getApplicationContext(), "Al borrar alumno se borraran todas sus parcticas", Toast.LENGTH_LONG).show();

        RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

        Call<List<Practica>> call = RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verTodasPracticas(misPreferencias.getToken().getSucces());

        practiasBorrar = new ArrayList<Practica>();

        call.enqueue(new Callback<List<Practica>>() {
            @Override
            public void onResponse(Call<List<Practica>> call, Response<List<Practica>> response) {
                if (response.code() == 200) {

                    for(Practica p : response.body()){

                        if(p.getAlumno().getId().equals(alumno.getId())) practiasBorrar.add(p);
                    }
                    Toast.makeText(getApplicationContext(), "Se borraran "+practiasBorrar.size()+"  practicas ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Problemas en  carga datos practicas ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Practica>> call, Throwable t) {

            }
        });


        borrarAlumno.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(getApplicationContext(), "Problemas en la conexión ", Toast.LENGTH_LONG).show();

                        }
                    });



                }


                    RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                    Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                            .createService(Cliente.class).borrarAlumno(alumno.getId(), misPreferencias.getToken().getSucces());

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
                            Toast.makeText(getApplicationContext(), "Problemas en la conexión ", Toast.LENGTH_LONG).show();
                        }
                    });



            }
        });
    }
}