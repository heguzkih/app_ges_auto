package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.modelo.Token;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import preferencias.GestorPreferencia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioProfesor extends AppCompatActivity {

    TextView txtNombre,txtPApellido,txtSApellido,txtPermisos;
    Profesor profesor;
    String token, dni,url;
    Token tock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio_profesor);
        txtNombre= findViewById(R.id.textnombre);
        txtPApellido=findViewById(R.id.textprimerapellido);
        txtSApellido=findViewById(R.id.textsegundoapellido);
        txtPermisos=findViewById(R.id.textpermisoso);

        tock = GestorPreferencia.getInstance(getApplicationContext()).getToken();
        Intent intent = getIntent();

         token =tock.getSucces();
         dni =intent.getStringExtra("dni");
        //Toast.makeText(getApplicationContext(),dni + " " +token,Toast.LENGTH_LONG).show();

        Call<Profesor>  call =  RetrofitCliente.getInstance()
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