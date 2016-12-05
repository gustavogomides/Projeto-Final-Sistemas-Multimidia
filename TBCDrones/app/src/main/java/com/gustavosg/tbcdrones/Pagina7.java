package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pagina7 extends AppCompatActivity {

    private Button proximapagina;
    private Button anterior;
    private MediaPlayer mp;
    private Button audio;
    private boolean a = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina7);

        proximapagina = (Button) findViewById(R.id.buttonProximaPagina7);
        anterior = (Button) findViewById(R.id.buttonAnterior7);
        audio = (Button) findViewById(R.id.audiop7);
    }

    @Override
    protected void onStart() {
        super.onStart();

        proximapagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a) {
                    mp.stop();
                }
                startActivity(new Intent(Pagina7.this, Pagina8.class));
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
                mp = MediaPlayer.create(Pagina7.this, R.raw.voo_2);
                mp.start();
            }
        });
    }
}