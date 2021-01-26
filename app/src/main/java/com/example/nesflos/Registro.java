package com.example.nesflos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nesflos.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Registro extends AppCompatActivity {

    EditText reus, reemail, repass, recopass;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        reus = findViewById(R.id.etreUsuario2);
        reemail = findViewById(R.id.etreEmail2);
        repass = findViewById(R.id.etrePass2);
        recopass = findViewById(R.id.etreConfirmar2);

        db = FirebaseFirestore.getInstance();
    }



    public void continuar(View view) {

        String regis_usuario = reus.getText().toString();
        String regis_email = reemail.getText().toString();
        String regis_pass = repass.getText().toString();
        String regis_copass = recopass.getText().toString();

        if (regis_usuario.equals("") || regis_email.equals("") || regis_pass.equals("") || regis_copass.equals(""))
        {
            valdacion();
        }
        else if (regis_pass.equals(regis_copass)){

            Map<String, Object> usuario = new HashMap<>();
            usuario.put("usuario", regis_usuario);
            usuario.put("correo", regis_email);
            usuario.put("contraseña", regis_pass);

            db.collection("Usuario").add(usuario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("FireApp", "Error", e);
                }
            });

            Intent continuar = new Intent(this, Pago.class);
            startActivity(continuar);
        }
        else {
            Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show();
        }

    }

    private void valdacion() {
        String regis_usuario = reus.getText().toString();
        String regis_email = reemail.getText().toString();
        String regis_pass = repass.getText().toString();

        if (regis_usuario.equals(""))
        {
            reus.setError("Required");
        }
        else if (regis_email.equals(""))
        {
            reemail.setError("Required");
        }
        else if (regis_pass.equals(""))
        {
            repass.setError("Required");
        }
    }


}