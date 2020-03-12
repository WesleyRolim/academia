package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.AlunoProfessor;
import com.example.academia.model.PlanoDeTreino;
import com.example.academia.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeuTreino extends AppCompatActivity {

    String [] exercicio = {"Puxa C", "Puxa F", "Remada B", "Remada Curvada",
            "Bicepes 1", "Bicepes 2", "Bicepes 3", "Tricepes Supnado", "Tricepes Corda", "Tricepes Frences", "Tricepes Testa"};
    String [] sequencia = {"3", "4", "4", "3", "4", "4", "3", "4", "3", "4", "4"};
    String [] repeticao = {"10", "8-12", "15", "10", "10", "8-10", "12-15", "8-10", "12-15", "8-10", "8"};

    private DatabaseReference reference = ConfiguracaoFirabase.getFirebase();
    private DatabaseReference dadosTreino;
    private Usuario user;
    private DatabaseReference professor;
    private PlanoDeTreino pTreino;
    private ListView listaExercicios;
    private String professorDoAluno = "";
    private DatabaseReference treino = reference.child("treino");
    private DatabaseReference mostraEcercicio = reference.child("treino");
    private DatabaseReference mostraTreinos = reference.child("treino");
    private Spinner tipoDeTreino;
    private ArrayList<String> meusTreinos = new ArrayList<>();
    private ArrayList<String> meusQtdExercicios = new ArrayList<>();
    private ArrayAdapter<CharSequence> adapter= null;
    private Button voltar;

    // variaveis de teste
    int cont = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_treino);
        tipoDeTreino = findViewById(R.id.treinoSpinner);
        listaExercicios = findViewById(R.id.listaTreino);
        voltar = findViewById(R.id.btnVoltar);


        pTreino = new PlanoDeTreino();
        // Pegar o professor do aluno -- Cria um metodo sepadado depois, para deixar melhor intendivel
        professor = ConfiguracaoFirabase.getFirebase().child("relacao").child( usuarioLogado() );
        professor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AlunoProfessor alProf = dataSnapshot.getValue(AlunoProfessor.class);
                professorDoAluno = alProf.getProfessor();

                // Busca qual o trieno
                treino = treino.child(professorDoAluno).child(usuarioLogado());
                treino.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dados : dataSnapshot.getChildren()){
                            dados.getValue();
                            meusTreinos.add(dados.getKey());
                        }
                        adapter = new ArrayAdapter(MeuTreino.this,
                                android.R.layout.simple_list_item_1,
                                meusTreinos);
                        tipoDeTreino.setAdapter(adapter);



                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("KEY", "Não foi possivel obter a Key");
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tipoDeTreino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positon, long l) {
                Log.i("Dado:","Dado do Spinner: "+ adapterView.getItemAtPosition(positon).toString());
                CustonAdapter custonAdapter = new CustonAdapter();
                String treinoSelecionado = adapterView.getItemAtPosition(positon).toString();

                mostraEcercicio = mostraEcercicio.child(professorDoAluno).child(usuarioLogado()).child(treinoSelecionado);
                mostraEcercicio.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            Log.i("Exer","Dados"+data.getValue());

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                if (treinoSelecionado.equals("B")){
//                    listaExercicios.setAdapter(custonAdapter);
//                }else{
//                    listaExercicios.setAdapter(null);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPrincipal();
            }
        });

    }

    // Put data inside of ListView
    class CustonAdapter extends BaseAdapter{
        @Override
        public int getCount() {
             return exercicio.length;
        }

        @Override

        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.custon_list_exercicios, null);

            TextView exercicioText = view.findViewById(R.id.exercicioTextView);
            TextView sequenciaText = view.findViewById(R.id.sequenciaTextView);
            TextView repeticaoText = view.findViewById(R.id.repeticaoTextView);

            exercicioText.setText(exercicio[position]);
            sequenciaText.setText(sequencia[position]);
            repeticaoText.setText(repeticao[position]);
            return view;
        }
    }

    public String usuarioLogado(){
        Preferencias preferencias = new Preferencias(MeuTreino.this);
        return preferencias.getIdentificador();
    }

    public void abrirPrincipal (){
        Intent intent = new Intent(MeuTreino.this, Principal.class);
        startActivity(intent);
        finish();
    }

}
