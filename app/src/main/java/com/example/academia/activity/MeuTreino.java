package com.example.academia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.model.PlanoDeTreino;
import com.google.firebase.database.DatabaseReference;

public class MeuTreino extends AppCompatActivity {

    String [] exercicio = {"Puxa C", "Puxa F", "Remada B", "Remada Curvada",
            "Bicepes 1", "Bicepes 2", "Bicepes 3", "Tricepes Supnado", "Tricepes Corda", "Tricepes Frences", "Tricepes Testa"};
    String [] sequencia = {"3", "4", "4", "3", "4", "4", "3", "4", "3", "4", "4"};
    String [] repeticao = {"10", "8-12", "15", "10", "10", "8-10", "12-15", "8-10", "12-15", "8-10", "8"};

    private DatabaseReference dadosTreino;
    private DatabaseReference professor;
    private PlanoDeTreino pTreino;



    private ListView listaExercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_treino);
        pTreino = new PlanoDeTreino();


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
}
