package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AtualizarUsuario extends AppCompatActivity {

    private TextView usuarioanterior;
    private EditText novousuario;
    private Button atualizar;
    private String strusuarioanterior;
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_usuario);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strusuarioanterior = bundle.getString("user");

        usuarioanterior = (TextView) findViewById(R.id.tvUsuarioAnterior);
        String saida = "Usuário anterior: " + strusuarioanterior;
        usuarioanterior.setText(saida);

        novousuario = (EditText) findViewById(R.id.tvNovoUsuario);

        atualizar = (Button) findViewById(R.id.btnNovoUsuario);

        bd = new BD(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strnovousuario = novousuario.getText().toString();
                ArrayList<Usuario> usuarios = bd.alunosCadastrados();

                if (strnovousuario.equals("")) {
                    Toast.makeText(AtualizarUsuario.this, "Digite um novo usuário!", Toast.LENGTH_SHORT).show();
                } else {
                    int c = 0;
                    for (Usuario u : usuarios) {
                        if (u.getUsuario().equals(strnovousuario)) {
                            c++;
                        }
                    }

                    if (c != 0) {
                        Toast.makeText(AtualizarUsuario.this, "Usuário já cadastrado anteriormente!", Toast.LENGTH_SHORT).show();
                    } else {
                        long retorno = bd.atualizarUsuario(strusuarioanterior, strnovousuario);
                        if (retorno == -1) {
                            Toast.makeText(AtualizarUsuario.this, "Erro ao atualizar Usuário!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AtualizarUsuario.this, "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AtualizarUsuario.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
