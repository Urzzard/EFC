package com.example.nesflos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    TextView bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Bundle traer = getIntent().getExtras();

        String usuario = traer.getString("nu");

        //bienvenido.setText(usuario);

        Toast.makeText(getApplicationContext(), "Bienvenido "+usuario, Toast.LENGTH_LONG).show();
    }

    public void abrirvideo(View view) {
        Intent entrar = new Intent(this, Video.class);
        startActivity(entrar);
    }
}