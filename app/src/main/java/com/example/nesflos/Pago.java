package com.example.nesflos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nesflos.model.Infopago;
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

public class Pago extends AppCompatActivity {

    EditText renom, reape, rentar, refv, recs;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago2);

        renom = findViewById(R.id.etreNombre);
        reape = findViewById(R.id.etreApellido);
        rentar = findViewById(R.id.etreNtarjeta);
        refv = findViewById(R.id.etreFvencimiento);
        recs = findViewById(R.id.etreCseguridad);

        db = FirebaseFirestore.getInstance();
    }


    public void completarregistro(View view) {

        String regis_nom = renom.getText().toString();
        String regis_ape = reape.getText().toString();
        String regis_ntar = rentar.getText().toString();
        String regis_fven = refv.getText().toString();
        String regis_cvv = recs.getText().toString();

        if (regis_nom.equals("") || regis_ape.equals("") || regis_ntar.equals("") || regis_fven.equals("") || regis_cvv.equals(""))
        {
            valdacionpago();
        }
        else if (regis_ntar.length()<16)
        {
            Toast.makeText(this, "Ingrese el numero de una tarjeta valida", Toast.LENGTH_LONG).show();
        }
        else {

            Map<String, Object> infopago = new HashMap<>();
            infopago.put("nombre", regis_nom);
            infopago.put("apellido", regis_ape);
            infopago.put("ntarjeta", regis_ntar);
            infopago.put("fvtarjeta", regis_fven);
            infopago.put("cvv", regis_cvv);

            db.collection("Infopago").add(infopago).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

            Intent completar = new Intent(this, Inicio.class);
            startActivity(completar);
        }
    }

    private void valdacionpago() {
        String regis_nom = renom.getText().toString();
        String regis_ape = reape.getText().toString();
        String regis_ntar = rentar.getText().toString();
        String regis_fven = refv.getText().toString();
        String regis_cvv = recs.getText().toString();

        if (regis_nom.equals(""))
        {
            renom.setError("Required");
        }
        if (regis_ape.equals(""))
        {
            reape.setError("Required");
        }
        if (regis_ntar.equals(""))
        {
            rentar.setError("Required");
        }
        if (regis_fven.equals(""))
        {
            refv.setError("Required");
        }
        if (regis_cvv.equals(""))
        {
            recs.setError("Required");
        }
    }
}