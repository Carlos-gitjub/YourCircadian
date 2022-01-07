package com.example.yourcircadian.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourcircadian.R;
import com.example.yourcircadian.entidades.Registros;

import java.util.ArrayList;

public class listaRegistrosAdapter extends RecyclerView.Adapter<listaRegistrosAdapter.RegistroViewHolder> {

    ArrayList<Registros> listaRegistros;

    public listaRegistrosAdapter(ArrayList<Registros> listaRegistros){
       this.listaRegistros = listaRegistros;
    }


    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_registro, null, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.viewFecha.setText(listaRegistros.get(position).getFecha());
        holder.viewHora.setText(listaRegistros.get(position).getHora());
        holder.viewAccion.setText(listaRegistros.get(position).getAccion());
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder{

        TextView viewFecha, viewHora, viewAccion;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);

            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewHora = itemView.findViewById(R.id.viewHora);
            viewAccion = itemView.findViewById(R.id.viewAccion);
        }
    }
}
