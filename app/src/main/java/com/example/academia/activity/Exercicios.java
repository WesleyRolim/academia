package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.academia.R;
import com.example.academia.model.Execicios;


public class Exercicios extends AppCompatActivity {

    private Spinner musculos;
    private TextView exercicio;
    private Button cadastrarExercicio;
    private Button voltar;
    private Execicios execicios;
    private String codificationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        musculos = findViewById(R.id.gupoMuscularesSpinner);
        exercicio = findViewById(R.id.exercicioEditText);
        cadastrarExercicio = findViewById(R.id.cadastrarExercicioButton);
        voltar = findViewById(R.id.voltarFicha);
        execicios = new Execicios();

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gurpoMusculos,
                android.R.layout.simple_spinner_item);
        musculos.setAdapter(adapter);

        musculos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positon, long l) {
                execicios.setGrupoExercicio(adapterView.getItemAtPosition(positon).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cadastrarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                execicios.setExercicio( exercicio.getText().toString() );
                cadastrarExercicio();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercicios.this, ProfessorPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void cadastrarExercicio(){
        execicios.salvar();
    }
}
