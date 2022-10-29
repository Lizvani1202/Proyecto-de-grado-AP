package com.example.sistemadereportes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button btnreporte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnreporte = (findViewById(R.id.btnReporte));
    }

    public void Reporte(View view) {
       Intent intent = new Intent(Inicio.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }
}