package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pagina3 extends AppCompatActivity {

    private Button proximapagina;
    private Button anterior;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina3);

        proximapagina = (Button) findViewById(R.id.buttonProximaPagina3);
        anterior = (Button) findViewById(R.id.buttonAnterior3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        proximapagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pagina3.this, Pagina4.class));
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}