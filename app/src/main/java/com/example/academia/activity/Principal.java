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
    private Button criarTreino;
    private Button criarExercicio;
    private Button trocarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        meuTreino = findViewById(R.id.meuTreinoButton);
        meusDados = findViewById(R.id.meusDadosButton);
        criarTreino = findViewById(R.id.criarTreinoButton);
        criarExercicio = findViewById(R.id.criarExercioButton);
        trocarSenha = findViewById(R.id.trocarSenhaButton);

        meuTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        meusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        criarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarPlanoTreino();

            }
        });

        criarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadExercicios();
            }
        });

        trocarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void abrirCadExercicios(){
        Intent intent = new Intent(Principal.this, Exercicios.class);
        startActivity(intent);
    }

    public  void criarPlanoTreino(){
        Intent intent = new Intent(Principal.this, PlanoTreino.class);
        startActivity(intent);
    }
}
