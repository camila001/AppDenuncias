package com.camila.appdenuncias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.camila.appdenuncias.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText t_email, t_name, t_pass, t_phone;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        t_email = findViewById(R.id.register_email);
        t_name = findViewById(R.id.register_name);
        t_pass = findViewById(R.id.register_pass);
        t_phone = findViewById(R.id.register_phone);

        auth = FirebaseAuth.getInstance();

    }

    public void createAccount(View view) {
        final String name, email, pass, phone;
        email = t_email.getText().toString();
        name = t_name.getText().toString();
        pass = t_pass.getText().toString();
        phone = t_phone.getText().toString();

        if (email.isEmpty() || name.isEmpty() || pass.isEmpty() || phone.isEmpty()){
            Toast.makeText(this,"Completa los campos", Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("usuarios");
                                Usuario user = new Usuario();
                                user.setNombre(name);
                                user.setEmail(email);
                                user.setTelefono(phone);
                                user.setUid(task.getResult().getUser().getUid());
                                myRef.push().setValue(user);
                                Toast.makeText(RegisterActivity.this,"Cuenta creada!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void backtoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}