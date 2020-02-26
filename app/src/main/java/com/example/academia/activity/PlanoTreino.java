package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.academia.helper.Codification;
import com.example.academia.model.PlanoDeTreino;

import com.example.academia.R;

public class PlanoTreino extends AppCompatActivity {

    private TextView email;
    private TextView sequencia;
    private TextView repeticao;
    private TextView tipoTreino;
    private TextView numeroExercicio;
    private PlanoDeTreino pTreino;

    private Spinner exercicio;
    private Spinner grupoMusculares;

    private Button salvar;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_treino);

        email = findViewById(R.id.emailTreinoEditText);
        sequencia = findViewById(R.id.sequenciaTreinoEditText);
        repeticao = findViewById(R.id.repeticaoTreinoEditText);
        tipoTreino = findViewById(R.id.tipoTreinoEditText);

        exercicio = findViewById(R.id.exercicioTreinoSpinner);
        grupoMusculares = findViewById(R.id.grupoMusculosTreinoSpinner);

        salvar = findViewById(R.id.salvarButton);
        voltar = findViewById(R.id.voltarPrincipalButton);



        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pTreino = new PlanoDeTreino();
                pTreino.setEmail( email.getText().toString() );
                pTreino.setSequencia( sequencia.getText().toString() );
                pTreino.setExercicio( repeticao.getText().toString() );
                pTreino.setTipoTreino( tipoTreino.getText().toString() );
                cadastrarTreino();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    public void cadastrarTreino(){
        String idUserEmail = Codification.codificacaoData(pTreino.getEmail());
        pTreino.setEmail(idUserEmail);
        pTreino.salvar();
    }

    public void voltar(){
        Intent integer = new Intent(PlanoTreino.this, Principal.class);
        startActivity(integer);
    }
}
