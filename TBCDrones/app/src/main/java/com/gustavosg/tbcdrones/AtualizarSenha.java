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

public class AtualizarSenha extends AppCompatActivity {

    private TextView senhaanterior;
    private EditText novasenha;
    private Button atualizar;
    private String usuario;
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_senha);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        usuario = bundle.getString("user");

        senhaanterior = (TextView) findViewById(R.id.tvSenhaAnterior);

        novasenha = (EditText) findViewById(R.id.tvNovaSenha);

        atualizar = (Button) findViewById(R.id.btnNovaSenha);

        bd = new BD(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String strsenhaanterior = "";

        ArrayList<Usuario> usuarios = bd.alunosCadastrados();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                strsenhaanterior = u.getSenha();
            }
        }
        String saida = "Senha anterior: " + strsenhaanterior;
        senhaanterior.setText(saida);

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strnovasenha = novasenha.getText().toString();

                if (strnovasenha.equals("")) {
                    Toast.makeText(AtualizarSenha.this, "Digite uma nova senha!", Toast.LENGTH_SHORT).show();
                } else {
                    long retorno = bd.atualizarSenha(usuario, strnovasenha);
                    if (retorno == -1) {
                        Toast.makeText(AtualizarSenha.this, "Erro ao atualizar senha!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AtualizarSenha.this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AtualizarSenha.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

