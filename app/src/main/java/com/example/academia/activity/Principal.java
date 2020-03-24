package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.academia.R;

public class Principal extends AppCompatActivity {

    private Button meuTreino;
    private Button meusDados;
    private Button minhaFicha;
    private Button trocarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        meuTreino = findViewById(R.id.meuTreinoButton);
        meusDados = findViewById(R.id.meusDadosButton);
        trocarSenha = findViewById(R.id.trocarSenhaButton);
        minhaFicha = findViewById(R.id.minhaFichaButton);

        meuTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirMeusTreinos();
            }
        });

        meusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abriTelaMeusDados();
            }
        });

        minhaFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirMinhaFicha();
            }
        });

        trocarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void abrirMeusTreinos(){
        Intent intent = new Intent(Principal.this, MeuTreino.class);
        startActivity(intent);
        finish();
    }

    public void abriTelaMeusDados(){
        Intent intent = new Intent(Principal.this, MeusDados.class);
        startActivity(intent);
        finish();
    }

    public void abrirMinhaFicha(){
        Intent intent = new Intent(Principal.this, MinhaFicha.class);
        startActivity(intent);
        finish();
    }


}
