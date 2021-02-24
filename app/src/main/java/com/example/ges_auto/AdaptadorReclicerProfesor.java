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

import com.example.ges_auto.modelo.Profesor;

import java.util.List;

import preferencias.MisPreferencias;

public class AdaptadorReclicerProfesor extends RecyclerView.Adapter<AdaptadorReclicerProfesor.ViewHolderDatos>{


    List<Profesor> listDatos;
    Button modificar , eliminar;
    Context context;
    private MisPreferencias misPreferencias;


    public AdaptadorReclicerProfesor(List<Profesor> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistaprofesor,null,false);
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

        TextView nombreProfesor, apellidoProfesor, premisosProfesor;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombreProfesor = (TextView) itemView.findViewById(R.id.textpronombre);
            premisosProfesor = (TextView) itemView.findViewById(R.id.textprofpermisos);
            apellidoProfesor = (TextView) itemView.findViewById(R.id.textprofapellido);
            modificar = (Button) itemView.findViewById(R.id.buttonModificarProfesores);
            eliminar = (Button) itemView.findViewById(R.id.buttoneliminartargetaprofesor);



        }

        public void asignarDatos(Profesor profesor) {
            nombreProfesor.setText(profesor.getNombre());
            premisosProfesor.setText(profesor.getPermisos().toString());
            apellidoProfesor.setText(profesor.getPrimer_apellido()+" "+profesor.getSegundo_apellido());

            modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(v.getContext(),"modificar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),UpdateProfesor.class);
                    intent.putExtra("profesor",profesor);
                    context.startActivities(new Intent[]{intent});
                }
            });

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Toast.makeText(v.getContext(),"eliminar",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),deleteProfesor.class);
                    intent.putExtra("profesordel",profesor);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
