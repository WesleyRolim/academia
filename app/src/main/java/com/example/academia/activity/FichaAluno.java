package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.academia.R;
import com.example.academia.helper.Codification;
import com.example.academia.model.FichaDoAluno;

public class FichaAluno extends AppCompatActivity {

    private Button voltar;
    private Button salvar;

    private TextView email;
    private TextView objetivo;
    private TextView frequencia;
    private TextView descanco;
    private TextView duracao;
    private TextView dataInicio;
    private TextView dataFim;
    private TextView peso;
    private TextView altura;

    private FichaDoAluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_aluno);

        voltar = findViewById(R.id.voltarButton);
        salvar = findViewById(R.id.cadastrarButton);

        email = findViewById(R.id.loginEditText);
        objetivo = findViewById(R.id.objetivoEditText);
        frequencia = findViewById(R.id.frequenciaEditText);
        descanco = findViewById(R.id.descancoEditText);
        duracao = findViewById(R.id.duracaoEditText);
        dataInicio = findViewById(R.id.dataInicioEditText);
        dataFim = findViewById(R.id.dataFimEditText);
        peso = findViewById(R.id.pesoEditText);
        altura = findViewById(R.id.alturaEditText);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aluno = new FichaDoAluno();
                String idUserEmail = Codification.codificacaoData(email.getText().toString());
                aluno.setAluno(idUserEmail);
                aluno.setObjetivo(objetivo.getText().toString());
                aluno.setFrequancia(frequencia.getText().toString());
                aluno.setDescanco(descanco.getText().toString());
                aluno.setDuracao(duracao.getText().toString());
                aluno.setDataInicio(dataInicio.getText().toString());
                aluno.setDataFim(dataFim.getText().toString());
                aluno.setPeso(peso.getText().toString());
                aluno.setAltura(altura.getText().toString());
                aluno.salvar();
            }
        });



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    private void voltar (){
        Intent intent = new Intent(FichaAluno.this, ProfessorPrincipal.class);
        startActivity(intent);
    }
}
