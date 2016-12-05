package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Desempenho extends AppCompatActivity {

    private TextView dq1;
    private TextView dq2;
    private TextView dq3;
    private TextView dq4;
    private TextView dq5;
    private TextView dmediafinal;
    private Button concluir;
    private Button relatorio;
    private String s1, s2, s3, s4, s5, mediafinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho);

        dq1 = (TextView) findViewById(R.id.dq1);
        dq2 = (TextView) findViewById(R.id.dq2);
        dq3 = (TextView) findViewById(R.id.dq3);
        dq4 = (TextView) findViewById(R.id.dq4);
        dq5 = (TextView) findViewById(R.id.dq5);
        dmediafinal = (TextView) findViewById(R.id.dmediafinal);
        concluir = (Button) findViewById(R.id.concluir);
        relatorio = (Button) findViewById(R.id.relatorio);
        setarTV();
    }

    @Override
    protected void onStart() {
        super.onStart();

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Desempenho.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPdf();
            }
        });
    }

    private void setarTV() {
        BD bd = new BD(this);

        Resposta resposta = bd.retornarrespostas();
        int r1 = resposta.getQ1();
        int r2 = resposta.getQ2();
        int r3 = resposta.getQ3();
        int r4 = resposta.getQ4();
        int r5 = resposta.getQ5();

        int somatorio = 0;
        if (r1 == 1) {
            s1 = "Questão 1: Acertou";
            somatorio += 20;
        } else {
            s1 = "Questão 1: Errou";
        }

        if (r2 == 1) {
            s2 = "Questão 2: Acertou";
            somatorio += 20;
        } else {
            s2 = "Questão 2: Errou";
        }

        if (r3 == 1) {
            s3 = "Questão 3: Acertou";
            somatorio += 20;
        } else {
            s3 = "Questão 3: Errou";
        }

        if (r4 == 1) {
            s4 = "Questão 4: Acertou";
            somatorio += 20;
        } else {
            s4 = "Questão 4: Errou";
        }

        if (r5 == 1) {
            s5 = "Questão 5: Acertou";
            somatorio += 20;
        } else {
            s5 = "Questão 5: Errou";
        }

        dq1.setText(s1);
        dq2.setText(s2);
        dq3.setText(s3);
        dq4.setText(s4);
        dq5.setText(s5);

        mediafinal = "Média Final: " + somatorio + " pontos";
        String media = String.valueOf(somatorio);
        bd.inserirresposta(null, "desempenho", media);
        dmediafinal.setText(mediafinal);
    }


    private void createPdf() {
        Document document = new Document();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tbcdrones";

            File pdfFolder = new File(path);

            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
                Log.i("LOG", "Pdf Directory created");
            }


            String nome = usuario();
            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

            File myFile = new File(pdfFolder + "_comprovante Questões " + nome + "_" + timeStamp + ".pdf");
            Log.i("NOME", myFile.toString());

            OutputStream output = new FileOutputStream(myFile);


            //Step 2
            PdfWriter.getInstance(document, output);

            //Step 3
            document.open();

            //Step 4 Add content
            document.add(new Paragraph("Aluno " + nome));
            document.add(new Paragraph(s1));
            document.add(new Paragraph(s2));
            document.add(new Paragraph(s3));
            document.add(new Paragraph(s4));
            document.add(new Paragraph(s5));
            document.add(new Paragraph(mediafinal));

            //Step 5: Close the document
            document.close();
            Toast.makeText(this, myFile.getName() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (DocumentException de) {
            Toast.makeText(this, "DocumentException:" + de, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "ioException:" + e, Toast.LENGTH_SHORT).show();

        } finally {
            document.close();
        }
    }

    public String usuario() {
        BD bd = new BD(this);
        Usuario u = bd.usuarioatual();
        String usuario = u.getNomecompleto();

        return usuario;
    }
}
