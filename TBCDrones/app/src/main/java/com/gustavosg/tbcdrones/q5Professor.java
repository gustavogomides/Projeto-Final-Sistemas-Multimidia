package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class q5Professor extends AppCompatActivity {

    private Button proxima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q5);
        proxima = (Button) findViewById(R.id.proximaq5);
        proxima.setText(getResources().getString(R.string.concluir));
    }

    @Override
    protected void onStart() {
        super.onStart();
        proxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolq1();
            }
        });
    }

    private void resolq1() {

        Intent intent = new Intent(q5Professor.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}
