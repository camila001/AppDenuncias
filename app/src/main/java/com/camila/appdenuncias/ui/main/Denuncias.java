package com.camila.appdenuncias.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camila.appdenuncias.R;
import com.camila.appdenuncias.adapter.AdapterDenuncias;
import com.camila.appdenuncias.adapter.AdapterMisDenuncias;
import com.camila.appdenuncias.model.Denuncia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Denuncias extends Fragment {

    List<Denuncia> list;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denuncias, container, false);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rv_den);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        for (DataSnapshot data : ds.getChildren()){
                            Denuncia denuncia = data.getValue(Denuncia.class);
                            denuncia.setId(ds.getKey());
                            list.add(denuncia);
                        }
                        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                        lm.setOrientation(RecyclerView.VERTICAL);

                        AdapterDenuncias adapterDenuncia = new AdapterDenuncias(getActivity(),R.layout.item_all_denuncias,list);
                        recyclerView.setLayoutManager(lm);
                        recyclerView.setAdapter(adapterDenuncia);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}