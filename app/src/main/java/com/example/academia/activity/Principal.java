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
    private Button fichaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        meuTreino = findViewById(R.id.meuTreinoButton);
        meusDados = findViewById(R.id.meusDadosButton);
        criarTreino = findViewById(R.id.criarTreinoButton);
        criarExercicio = findViewById(R.id.criarExercioButton);
        trocarSenha = findViewById(R.id.trocarSenhaButton);
        fichaAluno = findViewById(R.id.fichaAlunoButton);

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

        fichaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abriTeleCriarFichaAluno();
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

    public void abrirMeusTreinos(){
        Intent intent = new Intent(Principal.this, MeuTreino.class);
        startActivity(intent);
    }

    public void abriTelaMeusDados(){
        Intent intent = new Intent(Principal.this, MeusDados.class);
        startActivity(intent);
    }

    public void abriTeleCriarFichaAluno(){
        Intent intent = new Intent(Principal.this, FichaAluno.class);
        startActivity(intent);
    }
}
