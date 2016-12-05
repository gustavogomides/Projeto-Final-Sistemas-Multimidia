package com.gustavosg.tbcdrones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class q3Professor extends AppCompatActivity {

    private Button proxima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);

        proxima = (Button) findViewById(R.id.proximaq3);
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
        startActivity(new Intent(q3Professor.this, q4Professor.class));
    }

}
