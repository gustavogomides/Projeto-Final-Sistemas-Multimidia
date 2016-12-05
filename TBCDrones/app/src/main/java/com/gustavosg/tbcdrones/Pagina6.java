package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pagina6 extends AppCompatActivity {

    private Button proximapagina;
    private Button anterior;
    private MediaPlayer mp;
    private Button audio;
    private boolean a = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina6);

        proximapagina = (Button) findViewById(R.id.buttonProximaPagina6);
        anterior = (Button) findViewById(R.id.buttonAnterior6);
        audio = (Button) findViewById(R.id.audiop6);
    }

    @Override
    protected void onStart() {
        super.onStart();

        proximapagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pagina6.this, Pagina7.class));
                if (a) {
                    mp.stop();
                }
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = true;
                mp = MediaPlayer.create(Pagina6.this, R.raw.pre_voo_1);
                mp.start();
            }
        });
    }
}