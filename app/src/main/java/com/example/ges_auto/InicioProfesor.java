package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class InicioProfesor extends AppCompatActivity {

    TextView txtNombre,txtPApellido,txtSApellido,txtPermisos;
    Profesor profesor;
    String token, dni;
    Token tock;
    Button profesores, alumnos , practicas;

    private MisPreferencias misPreferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio_profesor);
        txtNombre= findViewById(R.id.textnombre);
        txtPApellido=findViewById(R.id.textprimerapellido);
        txtSApellido=findViewById(R.id.textsegundoapellido);
        txtPermisos=findViewById(R.id.textpermisoso);

        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

        tock = misPreferencias.getToken();
        Intent intent = getIntent();

         token =tock.getSucces();
         dni =intent.getStringExtra("dni");

        Call<Profesor>  call =  RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verProfesor(dni,token);

        call.enqueue(new Callback<Profesor>() {
            @Override
            public void onResponse(Call<Profesor> call, Response<Profesor> response) {

                profesor= response.body();
                txtNombre.setText( profesor.getNombre().toUpperCase());
                txtPApellido.setText(profesor.getPrimer_apellido().toUpperCase());
                txtSApellido.setText( profesor.getSegundo_apellido().toUpperCase());
                txtPermisos.setText( profesor.getPermisos().toString().toUpperCase());

                if(profesor.isRoot()){

                    profesores.setVisibility(View.VISIBLE);
                }else{

                    profesores.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<Profesor> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No se encuantran servidor ",Toast.LENGTH_LONG).show();
            }
        });


        profesores = findViewById(R.id.btnProfesores);


        profesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Profesores.class);
                startActivity(intent);
            }
        });


        alumnos = findViewById(R.id.btnAlumnos);
        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Alumnos.class);
                startActivity(intent1);
            }
        });

        practicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Profesores.class);
                intent.putExtra("profesor",profesor);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Call<Profesor>  call =  RetrofitCliente.getInstance(getApplicationContext())
                .createService(Cliente.class).verProfesor(dni,token);

        call.enqueue(new Callback<Profesor>() {
            @Override
            public void onResponse(Call<Profesor> call, Response<Profesor> response) {

                profesor= response.body();
                txtNombre.setText( profesor.getNombre().toUpperCase());
                txtPApellido.setText(profesor.getPrimer_apellido().toUpperCase());
                txtSApellido.setText( profesor.getSegundo_apellido().toUpperCase());
                txtPermisos.setText( profesor.getPermisos().toString().toUpperCase());


            }

            @Override
            public void onFailure(Call<Profesor> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No se encuantran servidor ",Toast.LENGTH_LONG).show();
            }
        });
    }
}