package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.PlanoDeTreino;
import com.example.academia.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MeuTreino extends AppCompatActivity {

    String [] exercicio = {"Puxa C", "Puxa F", "Remada B", "Remada Curvada",
            "Bicepes 1", "Bicepes 2", "Bicepes 3", "Tricepes Supnado", "Tricepes Corda", "Tricepes Frences", "Tricepes Testa"};
    String [] sequencia = {"3", "4", "4", "3", "4", "4", "3", "4", "3", "4", "4"};
    String [] repeticao = {"10", "8-12", "15", "10", "10", "8-10", "12-15", "8-10", "12-15", "8-10", "8"};

    private DatabaseReference dadosTreino;
    private Usuario user;
    private DatabaseReference professor;
    private PlanoDeTreino pTreino;
    private ListView listaExercicios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_treino);
        pTreino = new PlanoDeTreino();


        professor = ConfiguracaoFirabase.getFirebase().child("relacao").child( usuarioLogado() );

        professor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listaExercicios = findViewById(R.id.listaTreino);
        CustonAdapter custonAdapter = new CustonAdapter();
        listaExercicios.setAdapter(custonAdapter);

    }

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
}
