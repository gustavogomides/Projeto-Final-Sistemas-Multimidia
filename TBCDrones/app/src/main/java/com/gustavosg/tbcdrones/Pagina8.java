package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pagina8 extends AppCompatActivity {

    private Button proximapagina;
    private Button anterior;
    private MediaPlayer mp;
    private Button audio;
    private boolean a = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina8);

        proximapagina = (Button) findViewById(R.id.buttonProximaPagina8);
        anterior = (Button) findViewById(R.id.buttonAnterior8);
        audio = (Button) findViewById(R.id.audiop8);
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
                Toast.makeText(Pagina8.this, "Você concluiu o módulo 2!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Pagina8.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);

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
                mp = MediaPlayer.create(Pagina8.this, R.raw.pos_voo_3);
                mp.start();
            }
        });
    }
}