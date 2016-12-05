package com.gustavosg.tbcdrones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    private EditText nomec;
    private EditText user;
    private EditText password;
    private Button registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nomec = (EditText) findViewById(R.id.nomecompleto);
        user = (EditText) findViewById(R.id.usuarioregistro);
        password = (EditText) findViewById(R.id.senharegistro);
        registrar = (Button) findViewById(R.id.btnRegistrar);

    }

    @Override
    protected void onStart() {
        super.onStart();


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomecompleto = nomec.getText().toString();
                String usuario = user.getText().toString();
                String senha = password.getText().toString();
                if (nomecompleto.equals("")) {
                    Toast.makeText(Registrar.this, "Nome completo não pode ser vazio!", Toast.LENGTH_SHORT).show();
                } else if (usuario.equals("")) {
                    Toast.makeText(Registrar.this, "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
                } else if (senha.equals("")) {
                    Toast.makeText(Registrar.this, "Senha não pode ser vazia!", Toast.LENGTH_SHORT).show();
                } else if (senha.length() < 7) {
                    Toast.makeText(Registrar.this, "Senha deve ter no mínimo 7 caracteres!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = getSharedPreferences("funcaoprofessor", Context.MODE_PRIVATE);
                    int vezesQueAcessou = prefs.getInt("vezes", 0);
                    if (vezesQueAcessou == 0) {
                        registrar("Professor", "professor", "professor123", "professor", false);
                        registrar(nomecompleto, usuario, senha, "aluno", true);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("vezes", vezesQueAcessou + 1);
                        editor.apply();
                    } else if (vezesQueAcessou > 0) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("vezes", vezesQueAcessou + 1);
                        editor.apply();
                        registrar(nomecompleto, usuario, senha, "aluno", true);
                    }

                }
            }
        });

    }


    private void registrar(String nomecompleto, String usuario, String senha, String funcao, boolean msg) {
        Usuario obj_usuario = new Usuario();
        obj_usuario.setNomecompleto(nomecompleto);
        obj_usuario.setUsuario(usuario);
        obj_usuario.setSenha(senha);
        obj_usuario.setFuncao(funcao);
        BD bd = new BD(this);
        long insert = bd.inserir(obj_usuario);
        if (msg) {
            if (insert == -1) {
                Toast.makeText(this, "Erro no cadastro!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuário " + usuario + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registrar.this, Login.class));
            }
        }
    }
}
