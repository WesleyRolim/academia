package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Codification;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.AlunoProfessor;
import com.example.academia.model.PlanoDeTreino;

import com.example.academia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlanoTreino extends AppCompatActivity {

    private DatabaseReference reference = ConfiguracaoFirabase.getFirebase();
    private TextView email;
    private TextView sequencia;
    private TextView repeticao;
    private TextView tipoTreino;
    private TextView numeroExercicio;
    private PlanoDeTreino pTreino;
    private AlunoProfessor aluProfessor;
    private String identificadorUsuario;

    private DatabaseReference refenreciaDados;
    private DatabaseReference refenreciaExercicio;

    private Spinner exercicio;
    private Spinner grupoMusculares;

    private Button salvar;
    private Button voltar;

    private ArrayList<String> listMusculos = new ArrayList<>();
    private ArrayList<String> listExercicio = new ArrayList<>();
    private ArrayAdapter<CharSequence> adapter = null;
    private ArrayAdapter<CharSequence> adapterExercicio = null;

    private String grupoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_treino);
        pTreino = new PlanoDeTreino();
        aluProfessor = new AlunoProfessor();

        email = findViewById(R.id.emailTreinoEditText);
        sequencia = findViewById(R.id.sequenciaTreinoEditText);
        repeticao = findViewById(R.id.repeticaoTreinoEditText);
        tipoTreino = findViewById(R.id.tipoTreinoEditText);
        numeroExercicio = findViewById(R.id.numeroExercicioEditText);

        exercicio = findViewById(R.id.exercicioTreinoSpinner);
        grupoMusculares = findViewById(R.id.grupoMusculosTreinoSpinner);

        salvar = findViewById(R.id.salvarButton);
        voltar = findViewById(R.id.voltarPrincipalButton);

        refenreciaDados = ConfiguracaoFirabase.getFirebase().child("exercicio");
        refenreciaDados.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Log.i("Dado:", "Dado"+data.getKey());
                    listMusculos.add(data.getKey());
                }
                adapter = new ArrayAdapter(PlanoTreino.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        listMusculos);
                grupoMusculares.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        grupoMusculares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                grupoSelecionado = adapterView.getItemAtPosition(position).toString();
                pTreino.setGrupoMuscularea(grupoSelecionado);
                listExercicio.clear();

                refenreciaExercicio = ConfiguracaoFirabase.getFirebase().child("exercicio").child(grupoSelecionado);
                refenreciaExercicio.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dado : dataSnapshot.getChildren()){
                            listExercicio.add(dado.getKey());
                        }
                        adapterExercicio = new ArrayAdapter(PlanoTreino.this,
                                R.layout.support_simple_spinner_dropdown_item,
                                listExercicio);
                        exercicio.setAdapter(adapterExercicio);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        exercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positon, long l) {
                Log.i("Dado:","Ecercicio: "+ adapterView.getItemAtPosition(positon).toString());
                pTreino.setExercicio(adapterView.getItemAtPosition(positon).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camposVazios()){
                    pTreino.setEmail( email.getText().toString() );
                    pTreino.setSequencia( sequencia.getText().toString() );
                    pTreino.setRepeticao( repeticao.getText().toString() );
                    pTreino.setTipoTreino( tipoTreino.getText().toString() );
                    pTreino.setNumeroExercicio( numeroExercicio.getText().toString() );
                    pTreino.setPersonalTrainig( professorLogado() );
                    identificadorUsuario = Codification.codificacaoData( pTreino.getEmail() );
                    aluProfessor.setAluno( identificadorUsuario );
                    aluProfessor.setProfessor( professorLogado() );

                    refenreciaDados = ConfiguracaoFirabase.getFirebase().child("usuario").child( identificadorUsuario );
                    refenreciaDados.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){
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
                }else{
                    Toast camposPreenchidos = Toast.makeText(getApplicationContext(), "Preecha todos os campos", Toast.LENGTH_SHORT);
                    camposPreenchidos.show();
                }
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
        pTreino.salvarTreino();
        pTreino.salvarExercicio();
        aluProfessor.salvar();

    }
    public boolean camposVazios(){
        if(email.getText().toString().isEmpty() || sequencia.getText().toString().isEmpty() || repeticao.getText().toString().isEmpty() ||
        tipoTreino.getText().toString().isEmpty() || numeroExercicio.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void voltar(){
        Intent integer = new Intent(PlanoTreino.this, ProfessorPrincipal.class);
        startActivity(integer);
        finish();
    }

    public String professorLogado(){
        Preferencias preferencias = new Preferencias(PlanoTreino.this);
        return preferencias.getIdentificador();
    }
}
