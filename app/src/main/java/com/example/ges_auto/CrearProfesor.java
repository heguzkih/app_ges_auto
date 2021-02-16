package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.util.ArrayList;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class CrearProfesor extends AppCompatActivity {
    EditText dniProfesor, nombreProfesor, apellidounoprofesor, apellidodosprofesor,passprofresor, permisosprofesor;
    CheckBox root;
    Button modificar;
    Profesor profesor;
    private MisPreferencias misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profesor);
        dniProfesor = findViewById(R.id.Createdni);
        nombreProfesor= findViewById(R.id.CreateNombre);
        apellidodosprofesor=findViewById(R.id.CreateSegundoApellido);
        apellidounoprofesor = findViewById(R.id.CreatePrimerApellido);
        passprofresor = findViewById(R.id.Createdapass);
        permisosprofesor = findViewById(R.id.Createpermisos);
        root = findViewById(R.id.ch_selector_rootCreate);
        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));
        profesor = new Profesor();
        modificar= findViewById(R.id.buttonCreatePractica);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                profesor.setDni(dniProfesor.getText().toString().trim());
                profesor.setPass(passprofresor.getText().toString().trim());
                profesor.setNombre(nombreProfesor.getText().toString().trim());
                profesor.setPrimer_apellido(apellidounoprofesor.getText().toString().trim());
                profesor.setSegundo_apellido(apellidodosprofesor.getText().toString().trim());

                if (root.isChecked()) {
                    profesor.setRoot(true);

                } else {
                    profesor.setRoot(false);
                }

                ArrayList<String> permisos = new ArrayList<String>();
                String permisosModificado = permisosprofesor.getText().toString().trim();
                for (char per : permisosModificado.toCharArray()) {
                    if (Character.isAlphabetic(per)) {
                        permisos.add(String.valueOf(per));
                    }
                }
                profesor.setPermisos(permisos);

                RetrofitCliente.setBaseUrl(misPreferencias.getServidor());
                Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                        .createService(Cliente.class).crearProfesor(profesor, misPreferencias.getToken().getSucces());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {

                            Toast.makeText(getApplicationContext(), "Creacion correcta", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Problemas en la creacion ", Toast.LENGTH_LONG).show();
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