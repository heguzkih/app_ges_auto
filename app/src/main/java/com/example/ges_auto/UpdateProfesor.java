package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
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

public class UpdateProfesor extends AppCompatActivity {
    EditText dniProfesor, nombreProfesor, apellidounoprofesor, apellidodosprofesor,passprofresor, permisosprofesor;
    CheckBox root;
    Button modificar;
    Profesor profesor;
    private MisPreferencias misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profesor);

        Bundle extras = getIntent().getExtras();
        if (extras !=null) profesor = (Profesor) extras.getSerializable("profesor");

        dniProfesor = findViewById(R.id.Createdni);
        nombreProfesor= findViewById(R.id.CreateNombre);
        apellidounoprofesor= findViewById(R.id.CreatePrimerApellido);
        apellidodosprofesor = findViewById(R.id.CreateSegundoApellido);
        passprofresor = findViewById(R.id.Createdapass);
        permisosprofesor= findViewById(R.id.Createpermisos);
        root = findViewById(R.id.update_root);

        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));


        dniProfesor.setText(profesor.getDni());
        nombreProfesor.setText(profesor.getNombre());
        apellidodosprofesor.setText(profesor.getSegundo_apellido());
        apellidounoprofesor.setText(profesor.getPrimer_apellido());
        passprofresor.setText(profesor.getPass());
        permisosprofesor.setText(profesor.getPermisos().toString());
        root.setChecked(profesor.isRoot());

        modificar= findViewById(R.id.botonCreate);


        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               profesor.setNombre(nombreProfesor.getText().toString().trim());
               profesor.setPrimer_apellido(apellidounoprofesor.getText().toString().trim());
               profesor.setSegundo_apellido(apellidodosprofesor.getText().toString().trim());
               profesor.setDni(dniProfesor.getText().toString().trim());
               profesor.setPass(passprofresor.getText().toString().trim());

               if(root.isChecked()){
                   profesor.setRoot(true);
               }else{
                   profesor.setRoot(false);
               }

                ArrayList<String> permisos = new ArrayList<String>();
                String permisosModificado =   permisosprofesor.getText().toString().trim();
                for (char per: permisosModificado.toCharArray()){
                    if (Character.isAlphabetic(per) ){
                        permisos.add(String.valueOf(per));
                    }
                }

                profesor.setPermisos(permisos);

                RetrofitCliente.setBaseUrl(misPreferencias.getServidor());
                Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                        .createService(Cliente.class).actualizarProfesor(profesor.getDni().trim(),profesor,misPreferencias.getToken().getSucces());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200){

                            Toast.makeText(getApplicationContext(),"Actualizacion correcta",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Problemas en la Actualizacion ",Toast.LENGTH_LONG).show();
                        }
                    }



                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error en la conexion",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }





}