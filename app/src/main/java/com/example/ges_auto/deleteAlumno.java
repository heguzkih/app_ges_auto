package com.example.ges_auto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

public class deleteAlumno extends AppCompatActivity {
    TextView dni,nombre,primer_apellido,segundo_apellido,
            sexo,permiso_que_solicita;

    Button borrarAlumno;
    private MisPreferencias misPreferencias;
    Alumno alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_alumno);
        Bundle extras = getIntent().getExtras();
        if (extras !=null) alumno = (Alumno) extras.getSerializable("alumnodel");

        Toast.makeText(getApplicationContext(), alumno.getId(), Toast.LENGTH_LONG).show();

        dni = findViewById(R.id.DeletedniAlumno);
        nombre = findViewById(R.id.DeleteNombreAlumno);
        primer_apellido = findViewById(R.id.DeletePrimerApellidoAlumno);
        segundo_apellido   = findViewById(R.id.DeleteSegundoApellidoAlumno);

        sexo  = findViewById(R.id.DeletesexoAlumno);
        permiso_que_solicita   = findViewById(R.id.DeletetepermisosAlumno);

        misPreferencias = MisPreferencias.getInstance(getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE));


        borrarAlumno = findViewById(R.id.botonDeleteAlumno);


        borrarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    RetrofitCliente.setBaseUrl(misPreferencias.getServidor());

                    Call<Void> call = RetrofitCliente.getInstance(getApplicationContext())
                            .createService(Cliente.class).borrarAlumno(alumno.getId(), misPreferencias.getToken().getSucces());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {

                                Toast.makeText(getApplicationContext(), "borrado  correcto", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Problemas en el borrado ", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Problemas en la conexion ", Toast.LENGTH_LONG).show();
                        }
                    });



            }
        });
    }
}