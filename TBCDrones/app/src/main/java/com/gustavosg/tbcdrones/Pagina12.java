package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pagina12 extends AppCompatActivity {

    private Button proximapagina;
    private Button anterior;
    private MediaPlayer mp;
    private Button audio;
    private boolean a = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina12);

        proximapagina = (Button) findViewById(R.id.buttonProximaPagina12);
        anterior = (Button) findViewById(R.id.buttonAnterior12);
        audio = (Button) findViewById(R.id.audiop12);
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
                startActivity(new Intent(Pagina12.this, Pagina13.class));
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
                mp = MediaPlayer.create(Pagina12.this, R.raw.log_voo_7);
                mp.start();
            }
        });
    }
}