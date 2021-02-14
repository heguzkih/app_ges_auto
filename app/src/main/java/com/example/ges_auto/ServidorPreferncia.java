package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import preferencias.MisPreferencias;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class ServidorPreferncia extends AppCompatActivity {

    EditText servidorUrl;
    Button guardar, salir;
    String urlServer;
    private MisPreferencias misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor_preferncia);

        servidorUrl = findViewById(R.id.CreateNombre);
        guardar = findViewById(R.id.botonGuardar);
        salir = findViewById(R.id.botonSalir);
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        servidorUrl.setText(misPreferencias.getServidor());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlServer = servidorUrl.getText().toString().trim();
                if(!urlServer.isEmpty()){
                    misPreferencias.saveServidor(urlServer);
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
                if(misPreferencias.getServidor() == null){
                    Toast.makeText(getApplicationContext(),"SE NECESITA TENER UN SERVIDOR PARA EMPEZAR",Toast.LENGTH_LONG).show();
                }else {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                }
            }
        });
    }
}