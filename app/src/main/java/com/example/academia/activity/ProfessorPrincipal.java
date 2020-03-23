package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.academia.R;

public class ProfessorPrincipal extends AppCompatActivity {

    private Button alunos;
    private Button meusDados;
    private Button criarTreino;
    private Button criarExercicio;
    private Button trocarSenha;
    private Button fichaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_principal);

        alunos = findViewById(R.id.alunoButton);
        meusDados = findViewById(R.id.meusDadosButton);
        criarTreino = findViewById(R.id.criarTreinoButton);
        criarExercicio = findViewById(R.id.criarExercioButton);
        trocarSenha = findViewById(R.id.trocarSenhaButton);
        fichaAluno = findViewById(R.id.fichaAlunoButton);



    }

    public void abrirCadExercicios(){
        Intent intent = new Intent(ProfessorPrincipal.this, Exercicios.class);
        startActivity(intent);
    }

    public  void criarPlanoTreino(){
        Intent intent = new Intent(ProfessorPrincipal.this, PlanoTreino.class);
        startActivity(intent);
    }

    public void abriTeleCriarFichaAluno(){
        Intent intent = new Intent(ProfessorPrincipal.this, FichaAluno.class);
        startActivity(intent);
    }

    
}
