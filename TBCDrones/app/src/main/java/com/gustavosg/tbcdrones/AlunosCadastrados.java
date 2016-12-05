package com.gustavosg.tbcdrones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class AlunosCadastrados extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos_cadastrados);

        textView = (TextView) findViewById(R.id.tvNome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BD bd = new BD(this);
        ArrayList<Usuario> usuarios = bd.alunosCadastrados();

        String saida = "";
        int i = 1;
        if(usuarios==null){
            saida+="Não existem alunos cadastrados!";
        }else{
            for (Usuario u : usuarios) {
                saida += "Aluno " + i
                        + "\n    Nome: " + u.getNomecompleto()
                        + "\n    Usuário: " + u.getUsuario() + "\n\n\n";
                i++;
            }

        }

        textView.setText(saida);
    }


}


