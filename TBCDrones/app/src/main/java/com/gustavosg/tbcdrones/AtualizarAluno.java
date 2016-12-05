package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AtualizarAluno extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView usuario, nome, senha, titulo;
    private Button buscar, btnusuario, btnnome, btnsenha;
    private String usuarioSelecionado;

    private String strnome;
    private String strsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_aluno);

        spinner = (Spinner) findViewById(R.id.spinnerAtualizaAluno);

        usuario = (TextView) findViewById(R.id.tvUsuarioAtualizar);
        nome = (TextView) findViewById(R.id.tvNomeAtualizar);
        senha = (TextView) findViewById(R.id.tvSenhaAtualizar);
        titulo = (TextView) findViewById(R.id.titulodados);

        buscar = (Button) findViewById(R.id.btnBuscar);
        btnusuario = (Button) findViewById(R.id.btnAtuUsuario);
        btnnome = (Button) findViewById(R.id.btnAtualizarNome);
        btnsenha = (Button) findViewById(R.id.btnAtualizarSenha);

        // Loading spinner data from database
        loadSpinnerData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setarGone();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setarTV();
                setarVisible();
            }
        });

        btnusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AtualizarAluno.this, AtualizarUsuario.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", usuarioSelecionado);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnnome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AtualizarAluno.this, AtualizarNome.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", usuarioSelecionado);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AtualizarAluno.this, AtualizarSenha.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", usuarioSelecionado);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


    }

    private void setarTV() {
        BD bd = new BD(this);
        ArrayList<Usuario> usuarios = bd.alunosCadastrados();
        Usuario us = new Usuario();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuarioSelecionado)) {
                us = u;
            }
        }

        strnome = us.getNomecompleto();
        strsenha = us.getSenha();
        String susuario = "Usuário: " + us.getUsuario();
        String snome = "Nome: " + us.getNomecompleto();
        String ssenha = "Senha: " + us.getSenha();
        usuario.setText(susuario);
        nome.setText(snome);
        senha.setText(ssenha);
    }

    private void setarGone() {
        usuario.setVisibility(View.GONE);
        nome.setVisibility(View.GONE);
        senha.setVisibility(View.GONE);
        btnusuario.setVisibility(View.GONE);
        btnnome.setVisibility(View.GONE);
        btnsenha.setVisibility(View.GONE);
        titulo.setVisibility(View.GONE);
    }

    private void setarVisible() {
        usuario.setVisibility(View.VISIBLE);
        nome.setVisibility(View.VISIBLE);
        senha.setVisibility(View.VISIBLE);
        btnusuario.setVisibility(View.VISIBLE);
        btnnome.setVisibility(View.VISIBLE);
        btnsenha.setVisibility(View.VISIBLE);
        titulo.setVisibility(View.VISIBLE);
    }

    private void loadSpinnerData() {
        BD bd = new BD(this);
        ArrayList<Usuario> usuarios = bd.alunosCadastrados();
        ArrayList<String> lables = new ArrayList<>();

        for (Usuario u : usuarios) {
            Log.i("USUARIO", u.getUsuario());
            lables.add(u.getUsuario());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lables);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        usuarioSelecionado = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
