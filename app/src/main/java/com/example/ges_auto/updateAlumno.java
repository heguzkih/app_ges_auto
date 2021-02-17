package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
import com.example.ges_auto.modelo.Profesor;
import com.example.ges_auto.remoto.RetrofitCliente;
import com.example.ges_auto.servicio.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import preferencias.MisPreferencias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static preferencias.MisPreferencias.SHARED_PREFERENCES;

public class updateAlumno extends AppCompatActivity {
    EditText dni,nombre,primer_apellido,segundo_apellido,fecha_nacimiento,centro_reconocimiento,
            sexo,lugar_nacimiento,nacionalidad,permiso_que_solicita,direccion;
    CheckBox lentes, condiciones_restrictivas, validez_limitada;
    Button modificarAlumno;
    private MisPreferencias misPreferencias;
    Alumno alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alumno);

        Bundle extras = getIntent().getExtras();
        if (extras !=null) alumno = (Alumno) extras.getSerializable("alumno");



        dni = findViewById(R.id.UpdatedniAlumno);
        nombre = findViewById(R.id.UpdateNombreAlumno);
        primer_apellido = findViewById(R.id.UpdatePrimerApellidoAlumno);
        segundo_apellido   = findViewById(R.id.UpdateSegundoApellidoAlumno);
        fecha_nacimiento  = findViewById(R.id.UpdateeditTextDate);
        centro_reconocimiento   = findViewById(R.id.Updatecentrorec);
        sexo  = findViewById(R.id.UpdatesexoAlumno);
        lugar_nacimiento  = findViewById(R.id.UpdatelugarnaciAlumno);
        nacionalidad   = findViewById(R.id.UpdatenacionalidadAlumno);
        permiso_que_solicita   = findViewById(R.id.UpdatepermisosAlumno);
        direccion   = findViewById(R.id.UpdatedireccionAlumno);
        lentes   = findViewById(R.id.UpdatecheckBoxLentes);
        condiciones_restrictivas   = findViewById(R.id.UpdatecheckBoxConRestric);
        validez_limitada   = findViewById(R.id.UpdatecheckBoxVlimitada);


        dni.setText(alumno.getDni());
        nombre.setText(alumno.getNombre());
        primer_apellido.setText(alumno.getPrimer_apellido());
        segundo_apellido.setText(alumno.getSegundo_apellido());
        fecha_nacimiento.setText(alumno.getFecha_nacimiento().toString());
        centro_reconocimiento.setText(alumno.getCentro_reconocimiento());
        sexo.setText(alumno.getSexo());
        lugar_nacimiento.setText(alumno.getLugar_nacimiento());
        nacionalidad.setText(alumno.getNacionalidad());
        permiso_que_solicita.setText(alumno.getPermiso_que_solicita());
        direccion.setText(alumno.getDireccion());
        if(alumno.isLentes()) lentes.isChecked();
        if (alumno.isCondiciones_restrictivas())condiciones_restrictivas.isChecked();
        if(alumno.isValidez_limitada()) validez_limitada.isChecked();

        modificarAlumno = findViewById(R.id.botonUpdateAlumno12);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        modificarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    alumno = new Alumno();
                    alumno.setDni(dni.getText().toString().trim());
                    alumno.setNombre(nombre.getText().toString().trim());
                    alumno.setPrimer_apellido(primer_apellido.getText().toString().trim());
                    alumno.setSegundo_apellido(segundo_apellido.getText().toString().trim());
                    alumno.setSexo(sexo.getText().toString().trim());
                    alumno.setLugar_nacimiento(lugar_nacimiento.getText().toString().trim());
                    alumno.setNacionalidad(nacionalidad.getText().toString().trim());
                    alumno.setDireccion(direccion.getText().toString().trim());
                    alumno.setCentro_reconocimiento(centro_reconocimiento.getText().toString().trim());

                    misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));

                    Date fechaNacidate =  formatter.parse(fecha_nacimiento.getText().toString());
                    alumno.setFecha_nacimiento(fechaNacidate);

                    if(lentes.isChecked()){
                        alumno.setLentes(true);
                    }else{
                        alumno.setLentes(false);
                    }
                    if(condiciones_restrictivas.isChecked()){
                        alumno.setCondiciones_restrictivas(true);
                    }else{
                        alumno.setCondiciones_restrictivas(false);
                    }
                    if(validez_limitada.isChecked()){
                        alumno.setValidez_limitada(true);
                    }else{
                        alumno.setValidez_limitada(false);}

                    RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                    Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                            .createService(Cliente.class).modificaAlumno(alumno.getDni(),alumno, misPreferencias.getToken().getSucces());


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
                            Toast.makeText(getApplicationContext(),"Problemas en la Conexion ",Toast.LENGTH_LONG).show();

                        }
                    });



                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(),"formato fecha dd/MM/yyy ",Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}