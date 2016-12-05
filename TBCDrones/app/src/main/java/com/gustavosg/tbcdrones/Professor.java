package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Professor extends AppCompatActivity {

    private Button alunoscadastrados;
    private Button notasalunos;
    private Button atualizar;
    private Button deletar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        alunoscadastrados = (Button) findViewById(R.id.btnAlunosCadastrados);
        notasalunos = (Button) findViewById(R.id.btnNotasAlunos);
        atualizar = (Button) findViewById(R.id.btnAtualizarAluno);
        deletar = (Button) findViewById(R.id.btnDeletarAluno);
    }

    @Override
    protected void onStart() {
        super.onStart();

        alunoscadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Professor.this, AlunosCadastrados.class));
            }
        });

        notasalunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Professor.this, NotasAlunos.class));
            }
        });

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Professor.this, AtualizarAluno.class));
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Professor.this, DeletarAluno.class));
            }
        });
    }


}
