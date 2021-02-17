package com.example.ges_auto;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ges_auto.modelo.Practica;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdaptadorReclicerPracticas extends RecyclerView.Adapter<AdaptadorReclicerPracticas.ViewHolderDatos>{


    List<Practica> listDatos;
    Button modificar, eliminar, ver;
    Context context;

    public AdaptadorReclicerPracticas(List<Practica> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistapractica,null,false);
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

        TextView nombreAlumno, apellidoAlumno, premisosPractica, fechaPractica , dnialumno;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombreAlumno = (TextView) itemView.findViewById(R.id.textnombrealumnoPractica);
            premisosPractica = (TextView) itemView.findViewById(R.id.textpracticapermisos);
            apellidoAlumno = (TextView) itemView.findViewById(R.id.textalumnoapellidoPractica);
            modificar = (Button) itemView.findViewById(R.id.buttonModificarParctica);
            eliminar = (Button) itemView.findViewById(R.id.buttoneliminartargetaParctica);
            ver = (Button) itemView.findViewById(R.id.buttonverpractica);
            fechaPractica = (TextView) itemView.findViewById(R.id.textPracticaFecha);
            dnialumno = (TextView) itemView.findViewById(R.id.textalumnoDniPractica);



        }

        public void asignarDatos(Practica practica) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

            nombreAlumno.setText(practica.getAlumno().getNombre());
            premisosPractica.setText(practica.getPermiso());
            apellidoAlumno.setText(practica.getAlumno().getPrimer_apellido()+" "+practica.getAlumno().getSegundo_apellido());
            dnialumno.setText(practica.getAlumno().getDni());

            fechaPractica.setText(formatter.format(practica.getFechaInicio()));

            modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(),UpdatePractica.class);
                    intent.putExtra("practica",practica);
                    context.startActivities(new Intent[]{intent});
                }
            });

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(),deletePractica.class);
                    intent.putExtra("practicadel",practica);
                    context.startActivities(new Intent[]{intent});
                }
            });

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(),deleteProfesor.class);
                    intent.putExtra("practicaver",practica);
                    context.startActivities(new Intent[]{intent});

                }
            });
        }
    }
}
