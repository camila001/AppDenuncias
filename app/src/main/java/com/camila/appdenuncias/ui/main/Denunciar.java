package com.camila.appdenuncias.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.camila.appdenuncias.R;
import com.camila.appdenuncias.model.Denuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Denunciar extends Fragment {

    EditText t_titulo, t_direccion;
    Button bt_denunciar;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        auth = FirebaseAuth.getInstance();

        t_titulo = view.findViewById(R.id.nuevo_titulo);
        t_direccion = view.findViewById(R.id.nuevo_direccion);
        bt_denunciar = view.findViewById(R.id.btn_denunciar);

        bt_denunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = t_titulo.getText().toString();
                String direccion = t_direccion.getText().toString();
                String uid = auth.getCurrentUser().getUid();
                if (titulo.isEmpty() || direccion.isEmpty()){
                    Toast.makeText(getActivity(),"Completa los campos",Toast.LENGTH_LONG).show();
                }else{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("denuncias").child(uid);

                    Denuncia denuncia = new Denuncia();
                    denuncia.setTitulo(titulo);
                    denuncia.setDireccion(direccion);
                    denuncia.setEstado(0);
                    myRef.push().setValue(denuncia);
                    Toast.makeText(getActivity(),"Denuncia enviada",Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }
            }
        });

        return view;
    }

    private void limpiarCampos() {
        t_titulo.setText("");
        t_direccion.setText("");
    }
}