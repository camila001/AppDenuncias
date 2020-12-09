package com.camila.appdenuncias.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.camila.appdenuncias.R;
import com.camila.appdenuncias.adapter.AdapterMisDenuncias;
import com.camila.appdenuncias.model.Denuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MisDenuncias extends Fragment {

    FirebaseAuth auth;
    List<Denuncia> list;
    RecyclerView recyclerView;
    TextView delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_denuncias, container, false);
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.rv_md);
        delete = view.findViewById(R.id.item_delete);
        list = new ArrayList<>();
        misDenuncias();


        return view;
    }

    public void misDenuncias(){
        String uid = auth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Denuncia denuncia = ds.getValue(Denuncia.class);
                        denuncia.setId(ds.getKey());
                        list.add(denuncia);
                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(RecyclerView.VERTICAL);

                    AdapterMisDenuncias adapterDenuncia = new AdapterMisDenuncias(getActivity(),R.layout.item_denuncia,list);
                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapterDenuncia);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}