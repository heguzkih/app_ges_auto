package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.servicio.Cliente;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText textusuraio,textpass;
    TextInputLayout impusuario, imppass;
    Button buinicio;
    LinearLayout inicioSesion;
    ImageView logo;
    String usuario, pass , url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        inicioSesion = findViewById(R.id.linearLayout);

        textusuraio =findViewById(R.id.edtUsuario);
        textpass =findViewById(R.id.etpContraseña);

        impusuario =findViewById(R.id.impUsuario);
        imppass=findViewById(R.id.imppass);

        buinicio=findViewById(R.id.btAcceso);

        // retrofit para el json consumo api
        url= "https://auto-ges-api.herokuapp.com";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        Cliente cliente = retrofit.create(Cliente.class);
        Call<List<Profesor>> call =cliente.getReposUser(); // hilo secundario
        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                for( int i=0; i<response.body().size(); i++){
                    Profesor prof = response.body().get(i);
                    Log.d("TAG1","respueta "+i+" nombre "+prof.getNombre());
                    Toast.makeText(MainActivity.this,prof.getNombre(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                    Log.d("tag1", "error "+ t.getMessage().toString());
            }
        });



        buinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario= textusuraio.getText().toString();
                pass = textpass.getText().toString();

                if(usuario.length()== 0 || pass.length()== 0){
                    Toast.makeText(MainActivity.this,"usuario o contraseña vacios",Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(MainActivity.this,usuario+": "+pass,Toast.LENGTH_LONG).show();
                }


            }
        });

        Animation animacionLogo = AnimationUtils.loadAnimation(this,R.anim.animacion_logo);
        Animation animacionLayaut = AnimationUtils.loadAnimation(this,R.anim.animacion_layaut);
        logo.setAnimation(animacionLogo);
        inicioSesion.setAnimation(animacionLayaut);

    }


}