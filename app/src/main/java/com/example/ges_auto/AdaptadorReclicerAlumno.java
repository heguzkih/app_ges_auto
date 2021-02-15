package com.example.ges_auto;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ges_auto.modelo.Alumno;

import java.util.List;

import preferencias.MisPreferencias;

public class AdaptadorReclicerAlumno extends RecyclerView.Adapter<AdaptadorReclicerAlumno.ViewHolderDatos>{


    List<Alumno> listDatos;
    Button modificar , eliminar;
    Context context;
    TextView nombreAlmno, apellidoAlmno, dniAlmno;
    private MisPreferencias misPreferencias;


    public AdaptadorReclicerAlumno(List<Alumno> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistalumno,null,false);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {



        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            nombreAlmno = (TextView) itemView.findViewById(R.id.textalumnombre);
            dniAlmno = (TextView) itemView.findViewById(R.id.textalumdni);
            apellidoAlmno = (TextView) itemView.findViewById(R.id.textalumapellido);

           modificar = (Button) itemView.findViewById(R.id.buttonModificarAlumno);
           eliminar = (Button) itemView.findViewById(R.id.buttoneliminartargetAlumno);



        }

        public void asignarDatos(Alumno alumno) {

            nombreAlmno.setText(alumno.getNombre());
            dniAlmno.setText(alumno.getDni());
            apellidoAlmno.setText(alumno.getPrimer_apellido()+" "+alumno.getSegundo_apellido());






           modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"modificar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),updateAlumno.class);
                    intent.putExtra("alumno",alumno);
                    context.startActivities(new Intent[]{intent});
                }
            });

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(),"eliminar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),deleteAlumno.class);
                    intent.putExtra("alumnodel",alumno);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
