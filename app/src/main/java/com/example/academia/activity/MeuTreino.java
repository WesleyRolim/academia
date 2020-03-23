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
import android.widget.Toast;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.ListaDeExercicios;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.AlunoProfessor;
import com.example.academia.model.ListaTreino;
import com.example.academia.model.PlanoDeTreino;
import com.example.academia.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeuTreino extends AppCompatActivity {

    private ArrayList<ListaTreino> exercicioList = new ArrayList<>();

    private DatabaseReference reference = ConfiguracaoFirabase.getFirebase();
    private DatabaseReference dadosTreino;
    private Usuario user;
    private DatabaseReference professor;
    private PlanoDeTreino pTreino;
    private ListView listaExercicios;
    private String professorDoAluno = "";
    private DatabaseReference treino = reference.child("treino");
    private DatabaseReference mostraEcercicio;// = reference.child("treino");
    private DatabaseReference mostraTreinos = reference.child("treino");
    private Spinner tipoDeTreino;
    private ArrayList<String> meusTreinos = new ArrayList<>();
    private ArrayList<String> meusQtdExercicios = new ArrayList<>();
    private ArrayAdapter<CharSequence> adapter= null;
    private Button voltar;
    int contExercicio = 0;

    // variaveis de teste
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_treino);
        tipoDeTreino = findViewById(R.id.treinoSpinner);
        listaExercicios = findViewById(R.id.listaTreino);
        voltar = findViewById(R.id.btnVoltar);

        // Pegar o professor do aluno -- Cria um metodo sepadado depois, para deixar melhor intendivel
        professor = ConfiguracaoFirabase.getFirebase().child("relacao").child( usuarioLogado() );
        professor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
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
                }else{
                    Toast toast = Toast.makeText(MeuTreino.this, "Você não tem um professor relacionado", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tipoDeTreino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positon, long l) {
                Log.i("Dado:","Dado do Spinner: "+ adapterView.getItemAtPosition(positon).toString());
                String treinoSelecionado = adapterView.getItemAtPosition(positon).toString();
                mostraEcercicio = ConfiguracaoFirabase.getFirebase();
                mostraEcercicio = mostraEcercicio.child("treino").child(professorDoAluno).child(usuarioLogado()).child(treinoSelecionado);
                mostraEcercicio.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            PlanoDeTreino listaTreino = data.getValue(PlanoDeTreino.class);
                            addExercicio(listaTreino.getExercicio(),
                                    listaTreino.getSequencia(),
                                    listaTreino.getRepeticao());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                ArrayAdapter adapter = new ListaDeExercicios(MeuTreino.this, exercicioList);
                listaExercicios.setAdapter(adapter);
                exercicioList.clear();

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

    public String usuarioLogado(){
        Preferencias preferencias = new Preferencias(MeuTreino.this);
        return preferencias.getIdentificador();
    }

    public void abrirPrincipal (){
        Intent intent = new Intent(MeuTreino.this, Principal.class);
        startActivity(intent);
        finish();
    }

    private void addExercicio(String exercicio, String sequencia, String repeticao){
        ListaTreino lt = new ListaTreino (exercicio, sequencia, repeticao);
        exercicioList.add(lt);
    }


    // Put data inside of ListView
//    class CustonAdapter extends BaseAdapter{
//        @Override
//        public int getCount() {
//            return exercicio.length;
//        }
//
//        @Override
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = getLayoutInflater().inflate(R.layout.custon_list_exercicios, null);
//
//            TextView exercicioText = view.findViewById(R.id.exercicioTextView);
//            TextView sequenciaText = view.findViewById(R.id.sequenciaTextView);
//            TextView repeticaoText = view.findViewById(R.id.repeticaoTextView);
//
//            exercicioText.setText(exercicioList.get(contExercicio));
//            sequenciaText.setText(sequenciaList.get(contExercicio));
//            repeticaoText.setText(repeticaoList.get(contExercicio));
//            contExercicio ++;
//            return view;
//        }
//    }


}
