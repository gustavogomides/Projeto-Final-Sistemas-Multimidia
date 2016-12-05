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

public class AtualizarNome extends AppCompatActivity {

    private TextView nomeanterior;
    private EditText novonome;
    private Button atualizar;
    private String usuario;
    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_nome);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        usuario = bundle.getString("user");

        nomeanterior = (TextView) findViewById(R.id.tvNomeAnterior);

        novonome = (EditText) findViewById(R.id.tvNovoNome);

        atualizar = (Button) findViewById(R.id.btnNovoNome);

        bd = new BD(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String strnomeanterior = "";

        ArrayList<Usuario> usuarios = bd.alunosCadastrados();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                strnomeanterior = u.getNomecompleto();
            }
        }
        String saida = "Nome anterior: " + strnomeanterior;
        nomeanterior.setText(saida);

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strnovonome = novonome.getText().toString();

                if (strnovonome.equals("")) {
                    Toast.makeText(AtualizarNome.this, "Digite um novo nome!", Toast.LENGTH_SHORT).show();
                } else {
                    long retorno = bd.atualizarNome(usuario, strnovonome);
                    if (retorno == -1) {
                        Toast.makeText(AtualizarNome.this, "Erro ao atualizar nome!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AtualizarNome.this, "Nome atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AtualizarNome.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
