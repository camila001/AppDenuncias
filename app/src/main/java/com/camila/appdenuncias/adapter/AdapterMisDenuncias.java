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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMisDenuncias extends RecyclerView.Adapter<AdapterMisDenuncias.DenunciaHolder> {
    private Activity activity;
    private int layout;
    private List<Denuncia> list;

    public AdapterMisDenuncias(Activity activity, int layout, List<Denuncia> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncia denuncia = list.get(position);
        holder.titulo.setText(denuncia.getTitulo());
        holder.direccion.setText(denuncia.getDireccion());
        if (denuncia.getEstado() == 1){
            holder.estado.setBackgroundResource(R.drawable.circle_green);
        }
        holder.id = denuncia.getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{
        TextView titulo, direccion, estado, delete;
        String id;

        public DenunciaHolder(@NonNull final View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_title);
            direccion = itemView.findViewById(R.id.item_dir);
            estado = itemView.findViewById(R.id.item_circle);
            delete = itemView.findViewById(R.id.item_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(activity,"Funcionaxd",Toast.LENGTH_LONG).show();
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String uid = auth.getCurrentUser().getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("denuncias").child(uid);
                    myRef.child(id).removeValue();
                }
            });
        }
    }

}
