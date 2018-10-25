package com.example.cassi.exercicio_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMenInserir;
    private Button btnMenListar;
    private Button btnMenRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMenInserir = (Button) findViewById(R.id.btnMenInserir);
        btnMenInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inserir.class);
                     startActivity(intent);
            }
        });
        btnMenListar = (Button) findViewById(R.id.btnMenListar);
        btnMenListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar.class);
                startActivity(intent);
            }
        });

        btnMenRemover  = (Button) findViewById(R.id.btnMenRemover);
        btnMenRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Remover.class);
                startActivity(intent);
            }
        });

    }
}

