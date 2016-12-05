package com.gustavosg.tbcdrones;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeletarAluno extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private TextView usuario, nome, senha, titulo;
    private Button buscar, btndeletar;
    private String usuarioSelecionado;
    private BD bd;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_aluno);

        spinner = (Spinner) findViewById(R.id.spinnerDeletarAluno);

        usuario = (TextView) findViewById(R.id.tvUsuarioDeletar);
        nome = (TextView) findViewById(R.id.tvNomeDeletar);
        senha = (TextView) findViewById(R.id.tvSenhaDeletar);
        titulo = (TextView) findViewById(R.id.titulodadosDeletar);

        buscar = (Button) findViewById(R.id.btnBuscarDeletar);
        btndeletar = (Button) findViewById(R.id.btnAlunoDeletar);

        // Loading spinner data from database
        loadSpinnerData();
        bd = new BD(this);
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

        btndeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
            }
        });

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

    }

    private void dialogo() {
        //Cria o gerador do AlertDialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Deletar Aluno");
        //define a mensagem
        builder.setMessage("Deseja deletar o aluno: " + usuarioSelecionado + " ?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                int retorno = bd.deletarAluno(usuarioSelecionado);
                if (retorno <= 0) {
                    Toast.makeText(DeletarAluno.this, "Erro ao deletar aluno!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeletarAluno.this, "Aluno deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeletarAluno.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                }
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
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
        btndeletar.setVisibility(View.GONE);
        titulo.setVisibility(View.GONE);
    }

    private void setarVisible() {
        usuario.setVisibility(View.VISIBLE);
        nome.setVisibility(View.VISIBLE);
        senha.setVisibility(View.VISIBLE);
        btndeletar.setVisibility(View.VISIBLE);
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
