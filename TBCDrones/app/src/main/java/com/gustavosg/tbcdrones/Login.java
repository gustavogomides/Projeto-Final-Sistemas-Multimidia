package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    private TextView txtRegistrar;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (EditText) findViewById(R.id.usuario);
        txtSenha = (EditText) findViewById(R.id.senha);
        txtRegistrar = (TextView) findViewById(R.id.registrar);
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registrar.class));
            }
        });
        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strusuario = txtUsuario.getText().toString();
                String strsenha = txtSenha.getText().toString();
                if (strusuario.equals("")) {
                    Toast.makeText(Login.this, "Campo usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
                } else if (strsenha.equals("")) {
                    Toast.makeText(Login.this, "Campo senha não pode ser vazio!", Toast.LENGTH_SHORT).show();
                } else {
                    login(strusuario, strsenha);
                }
            }
        });
    }

    private void login(String usuario, String senha) {
        BD bd = new BD(this);

        if (bd.verificarUsuario(usuario) == null) {
            Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show();
        } else if (!bd.verificaSenha(usuario, senha)) {
            Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bem-Vindo ao TBC Drones!", Toast.LENGTH_SHORT).show();
            bd.inserirUsuarioAtual(usuario);
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }
}