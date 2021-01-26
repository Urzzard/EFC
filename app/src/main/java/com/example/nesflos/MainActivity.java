package com.example.nesflos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nesflos.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    EditText loemail, lopass;
    FirebaseFirestore db;
    Integer estado=1;
    String em, pass, nomus;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loemail = findViewById(R.id.etloEmail);
        lopass = findViewById(R.id.etloPass);
        log = findViewById(R.id.btnLogin);

        db = FirebaseFirestore.getInstance();

    }

    public void loguear(View view) {
        String login_email = loemail.getText().toString();
        String login_pass = lopass.getText().toString();

        if (login_email.equals("") || login_pass.equals("")){
            validarlogin();
        }
        else {
            db.collection("Usuario").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){

                        //Toast.makeText(getApplicationContext(), "Conecto con la bD", Toast.LENGTH_LONG).show();

                        for (QueryDocumentSnapshot document: task.getResult()){
                            em = (String) document.getData().get("correo");
                            pass = (String) document.getData().get("contraseña");
                            nomus = (String) document.getData().get("usuario");

                            //Toast.makeText(getApplicationContext(), em+pass, Toast.LENGTH_LONG).show();

                            if (login_email.equals(em) && login_pass.equals(pass)){
                                estado = 0;
                                entrar();
                            }
                                //Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecta", Toast.LENGTH_LONG).show();

                        }

                    }else{
                        Log.e("Fireapp", "Error", task.getException());
                    }
                }
            });
        }
    }

    public void entrar(){
        estado=1;
        Bundle parametros = new Bundle();
        parametros.putString("nu",nomus);
        parametros.putString("ue",em);
        Intent entrar = new Intent(this, Inicio.class);
        entrar.putExtras(parametros);
        startActivity(entrar);
    }

    private void validarlogin() {
        String login_email = loemail.getText().toString();
        String login_pass = lopass.getText().toString();

        if (login_email.equals("")){
            loemail.setError("Required");
        }
        if (login_pass.equals("")){
            lopass.setError("Required");
        }
    }

    public void registrar(View view) {
        Intent registrarse = new Intent(this, Registro.class);
        startActivity(registrarse);
    }
}