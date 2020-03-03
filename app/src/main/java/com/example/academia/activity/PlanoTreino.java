package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Codification;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.PlanoDeTreino;

import com.example.academia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PlanoTreino extends AppCompatActivity {

    private TextView email;
    private TextView sequencia;
    private TextView repeticao;
    private TextView tipoTreino;
    private TextView numeroExercicio;
    private PlanoDeTreino pTreino;
    private String identificadorUsuario;
    private DatabaseReference refenreciaDados;

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
        numeroExercicio = findViewById(R.id.numeroExercicioEditText);

        exercicio = findViewById(R.id.exercicioTreinoSpinner);
        grupoMusculares = findViewById(R.id.grupoMusculosTreinoSpinner);

        salvar = findViewById(R.id.salvarButton);
        voltar = findViewById(R.id.voltarPrincipalButton);



        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pTreino = new PlanoDeTreino();
                pTreino.setSequencia( sequencia.getText().toString() );
                pTreino.setExercicio( repeticao.getText().toString() );
                pTreino.setTipoTreino( tipoTreino.getText().toString() );
                pTreino.setNumeroExercicio( numeroExercicio.getText().toString() );
                pTreino.setPersonalTrainig( professorLogado() );
                pTreino.setEmail( email.getText().toString() );

                identificadorUsuario = Codification.codificacaoData( pTreino.getEmail() );
                refenreciaDados = ConfiguracaoFirabase.getFirebase().child("usuario").child( identificadorUsuario );
                Log.i("Professor","Professor"+professorLogado());
                refenreciaDados.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i("Professor", "Entrou 1");
                        if (dataSnapshot.getValue() != null){
                            Log.i("Professor", "Entrou 2");
                            cadastrarTreino();
                        }else {
                            Toast.makeText(PlanoTreino.this,
                                    "Usuario não tem cadastro",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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

    public String professorLogado(){
        Preferencias preferencias = new Preferencias(PlanoTreino.this);
        return preferencias.getIdentificador();
    }
}
