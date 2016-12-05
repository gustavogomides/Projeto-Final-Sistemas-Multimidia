package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class q1 extends AppCompatActivity {

    private RadioButton radioButton;
    private RadioGroup group;
    private Button proxima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);

        group = (RadioGroup) findViewById(R.id.rgq1);

        proxima = (Button) findViewById(R.id.proximaq1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        proxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedId = group.getCheckedRadioButtonId();

                if (selectedId != -1) {

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);

                    resolq1();

                } else {
                    Toast.makeText(q1.this, "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void resolq1() {

        if (radioButton.getText().toString().equals("Machucar os circunstantes, danos a propriedade e perda da certificação 107.")) {
            Toast.makeText(q1.this,
                    "Resposta Correta!", Toast.LENGTH_SHORT).show();
            long retorno = inserirResposta(1, "q1");
            Log.i("Q1 C", retorno + "");
        } else {
            Toast.makeText(q1.this,
                    "Resposta errada!", Toast.LENGTH_SHORT).show();
            long retorno = inserirResposta(0, "q1");
            Log.i("Q1 E", retorno + "");
        }
        startActivity(new Intent(q1.this, q2.class));
    }

    private long inserirResposta(int estado, String questao) {
        BD bd = new BD(this);
        return bd.inserirresposta(String.valueOf(estado), questao, null);
    }
}
