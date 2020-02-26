package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.academia.model.PlanoDeTreino;

import com.example.academia.R;

public class PlanoTreino extends AppCompatActivity {

    private TextView email;
    private TextView sequencia;
    private TextView repeticao;
    private TextView tipoTreino;
    private PlanoDeTreino pTreino;

    private Spinner exercicio;
    private Spinner grupoMusculares;

    private Button salvar;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_treino);

        email = findViewById(R.id.emailEditText);
        sequencia = findViewById(R.id.sequenciaEditText);
        repeticao = findViewById(R.id.repeticaoEditText);
        tipoTreino = findViewById(R.id.tipoTreinoEditText);

        exercicio = findViewById(R.id.exercicioSpinner);
        grupoMusculares = findViewById(R.id.grupoMusculosSpinner);

        salvar = findViewById(R.id.salvarButton);
        voltar = findViewById(R.id.voltarButton);


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
    }

    public void cadastrarTreino(){

        pTreino.salvar();
    }
}
