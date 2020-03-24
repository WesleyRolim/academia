package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.FichaDoAluno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MinhaFicha extends AppCompatActivity {

    private TextView objetivo;
    private TextView frequencia;
    private TextView duracao;
    private TextView descanco;
    private TextView inicio;
    private TextView fim;
    private TextView peso;
    private TextView altura;

    private DatabaseReference ficha;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_ficha);

        objetivo = findViewById(R.id.objetivoTextView);
        frequencia = findViewById(R.id.frequenciaTextView);
        duracao = findViewById(R.id.duracaoTextView);
        descanco = findViewById(R.id.descancoTextView);
        inicio = findViewById(R.id.dataInicioTextView);
        fim = findViewById(R.id.dataFimTextView);
        peso = findViewById(R.id.pesoTextView);
        altura = findViewById(R.id.alturaTextView);
        voltar = findViewById(R.id.voltarFicha);

        ficha = ConfiguracaoFirabase.getFirebase().
                child("ficha").child(usuarioLogado());

        ficha.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    FichaDoAluno fichaDoAluno = dataSnapshot.getValue(FichaDoAluno.class);
                    objetivo.setText(fichaDoAluno.getObjetivo());
                    frequencia.setText(fichaDoAluno.getFrequancia());
                    duracao.setText(fichaDoAluno.getDuracao());
                    descanco.setText(fichaDoAluno.getDescanco());
                    inicio.setText(fichaDoAluno.getDataInicio());
                    fim.setText(fichaDoAluno.getDataFim());
                    peso.setText(fichaDoAluno.getPeso());
                    altura.setText(fichaDoAluno.getAltura());
                }else{
                    Toast toast = Toast.makeText(MinhaFicha.this,
                            "Você não tem ficha, \nPeça para seu professo criar sua ficha",Toast.LENGTH_LONG);
                    toast.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }

    public String usuarioLogado(){
        Preferencias preferencias = new Preferencias(MinhaFicha.this);
        return preferencias.getIdentificador();
    }

    public void voltar (){
        Intent intent = new Intent(MinhaFicha.this, Principal.class);
        startActivity(intent);
        finish();
    }
}
