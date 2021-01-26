package com.example.nesflos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Video extends AppCompatActivity {

    FirebaseFirestore db;
    String ti,gen,dir,ano;
    TextView tvtitulo, tvgenero, tvdirector, tvano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        db = FirebaseFirestore.getInstance();

        db.collection("Peliculas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        ti = (String) document.getData().get("titulo");
                        gen = (String) document.getData().get("genero");
                        dir = (String) document.getData().get("director");
                        ano = (String) document.getData().get("año");

                        tvtitulo.setText(ti);
                        tvgenero.setText(gen);
                        tvdirector.setText(dir);
                        tvano.setText(ano);

                        //Toast.makeText(getApplicationContext(), ti+" "+gen+" "+dir+" "+ano, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}