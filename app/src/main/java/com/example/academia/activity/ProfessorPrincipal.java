package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.academia.R;

public class ProfessorPrincipal extends AppCompatActivity {

    private Button alunos;
    private Button dadoProfessor;
    private Button criarTreino;
    private Button criarExercicio;
    private Button trocarSenha;
    private Button fichaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_principal);

        alunos = findViewById(R.id.alunoButton);
        dadoProfessor = findViewById(R.id.dadosProfessorButton);
        criarTreino = findViewById(R.id.criarTreinoButton);
        criarExercicio = findViewById(R.id.criarExercioButton);
        trocarSenha = findViewById(R.id.trocarSenhaButton);
        fichaAluno = findViewById(R.id.fichaAlunoButton);

        fichaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abriTeleCriarFichaAluno();
            }
        });

        criarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadExercicios();
            }
        });

        criarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarPlanoTreino();

            }
        });

        dadoProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abriTelaMeusDados();
            }
        });

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

    public void abriTelaMeusDados(){
        Intent intent = new Intent(ProfessorPrincipal.this, MeusDados.class);
        startActivity(intent);
    }
}
