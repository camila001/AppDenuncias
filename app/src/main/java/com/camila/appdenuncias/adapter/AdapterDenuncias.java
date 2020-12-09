package com.camila.appdenuncias.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.camila.appdenuncias.R;
import com.camila.appdenuncias.model.Denuncia;
import com.camila.appdenuncias.ui.main.Denuncias;

import java.util.List;

public class AdapterDenuncias extends RecyclerView.Adapter<AdapterDenuncias.DenunciaHolder>{
    private Activity activity;
    private int layout;
    private List<Denuncia> list;

    public AdapterDenuncias(Activity activity, int layout, List<Denuncia> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new AdapterDenuncias.DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncia denuncia = list.get(position);
        holder.titulo.setText(denuncia.getTitulo());
        holder.direccion.setText(denuncia.getDireccion());
        if (denuncia.getEstado() == 1){
            holder.estado.setBackgroundResource(R.drawable.circle_green);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{
        TextView titulo, direccion, estado;
        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo);
            direccion = itemView.findViewById(R.id.item_direc);
            estado = itemView.findViewById(R.id.item_circulo);
        }
    }
}
