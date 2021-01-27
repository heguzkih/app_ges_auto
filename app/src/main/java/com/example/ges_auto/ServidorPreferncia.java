package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import preferencias.GestorPreferencia;

public class ServidorPreferncia extends AppCompatActivity {

    EditText servidorUrl;
    Button guardar, salir;
    String urlServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor_preferncia);

        servidorUrl = findViewById(R.id.edtUrl);
        guardar = findViewById(R.id.botonGuardar);
        salir = findViewById(R.id.botonSalir);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlServer = servidorUrl.getText().toString().trim();
                if(!urlServer.isEmpty()){
                GestorPreferencia.getInstance(getApplicationContext()).saveServidor(urlServer);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                Toast.makeText(getApplicationContext(),"Campo Url vacio",Toast.LENGTH_LONG).show();
                }
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}