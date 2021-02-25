package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ges_auto.modelo.Alumno;
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

public class CrearAlumno extends AppCompatActivity {

     EditText dni,nombre,primer_apellido,segundo_apellido,fecha_nacimiento,centro_reconocimiento,
             sexo,lugar_nacimiento,nacionalidad,permiso_que_solicita,direccion;
     CheckBox lentes, condiciones_restrictivas, validez_limitada;
    Button crearAlumno;
    private MisPreferencias misPreferencias;
    Alumno alumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumno);

        dni = findViewById(R.id.CreatedniAlumno);
        nombre = findViewById(R.id.CreateNombreAlumno);
        primer_apellido = findViewById(R.id.CreatePrimerApellidoAlumno);
        segundo_apellido   = findViewById(R.id.CreateSegundoApellidoAlumno);
        fecha_nacimiento  = findViewById(R.id.editTextDate);
        centro_reconocimiento   = findViewById(R.id.Createcentrorec);
        sexo  = findViewById(R.id.CreatesexoAlumno);
        lugar_nacimiento  = findViewById(R.id.CreatelugarnaciAlumno);
        nacionalidad   = findViewById(R.id.CreatenacionalidadAlumno);
        permiso_que_solicita   = findViewById(R.id.CreatepermisosAlumno);
        direccion   = findViewById(R.id.CreadireccionAlumno);
        lentes   = findViewById(R.id.checkBoxLentes);
        condiciones_restrictivas   = findViewById(R.id.checkBoxConRestric);
        validez_limitada   = findViewById(R.id.checkBoxVlimitada);

        crearAlumno = findViewById(R.id.botonCreateAlumno);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        crearAlumno.setOnClickListener(new View.OnClickListener() {
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
                alumno.setPermiso_que_solicita(permiso_que_solicita.getText().toString().trim());

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
                            .createService(Cliente.class).crearAlumno(alumno, misPreferencias.getToken().getSucces());


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
                            Toast.makeText(getApplicationContext(), "Problemas en la conexion ", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(),"formato fecha dd/MM/yyy ",Toast.LENGTH_LONG).show();

                }

            }
        });






    }
}