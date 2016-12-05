package com.gustavosg.tbcdrones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class NotasAlunos extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_alunos);

        textView = (TextView) findViewById(R.id.tvNotasAlunos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BD bd = new BD(this);
        ArrayList<Resposta> respostas = bd.notasAlunos();

        String saida = "";
        if (respostas == null) {
            saida += "Nenhum aluno completou a avaliação!";
        } else {
            for (Resposta r : respostas) {
                saida += "Aluno: " + r.getUsuario().getNomecompleto() + "\n   Nota: " + r.getMediafinal() + " pontos\n\n";
            }
        }

        textView.setText(saida);
    }
}
