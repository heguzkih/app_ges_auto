package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;
import com.google.android.material.textfield.TextInputLayout;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class MainActivity extends AppCompatActivity {
    EditText textpass, textusuarioDni;
    TextInputLayout imppass;
    Button buinicio, botonurl;
    LinearLayout inicioSesion;
    ImageView logo;
    String usuario, pass , url , dni , token ;
    RetrofitCliente retrofitCliente;
    Profesor profesor;
    private MisPreferencias misPreferencias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        inicioSesion = findViewById(R.id.linearLayout);
        textusuarioDni = findViewById(R.id.CreateNombre);
        textpass =findViewById(R.id.UpdatePrimerApellido);
        imppass=findViewById(R.id.imppass);
        buinicio=findViewById(R.id.btUpdatesalir);
        botonurl=findViewById(R.id.buttonCreatePractica);
        profesor = new Profesor();

        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

        // retrofit para el json consumo api

        if (misPreferencias.getServidor() ==null){

            Intent intent = new Intent(getApplicationContext(),ServidorPreferncia.class);
            startActivity(intent);
        }


        buinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),misPreferencias.getServidor(),Toast.LENGTH_LONG).show();

                RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                dni =textusuarioDni.getText().toString().trim();

                Profesor prof = new Profesor();
                prof.setDni(dni);
                prof.setPass(textpass.getText().toString().trim());


                Call <Token> call = RetrofitCliente.getInstance(getApplicationContext())
                                    .createService(Cliente.class).loguin(prof);
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {

                        Token tock = response.body();

                        if(response.code()==200){

                            misPreferencias.saveToken(tock);
                            Intent intent = new Intent(getApplicationContext(),InicioProfesor.class);
                            intent.putExtra("dni", dni);
                            startActivity(intent);

                        }else{

                            Toast.makeText(getApplicationContext(),"Error en Usuario o contraseña",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_LONG).show();

                    }
                });



            }
        });

        botonurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServidorPreferncia.class);
                startActivity(intent);
            }
        });

        Animation animacionLogo = AnimationUtils.loadAnimation(this,R.anim.animacion_logo);
        Animation animacionLayaut = AnimationUtils.loadAnimation(this,R.anim.animacion_layaut);
        logo.setAnimation(animacionLogo);
        inicioSesion.setAnimation(animacionLayaut);

    }



}