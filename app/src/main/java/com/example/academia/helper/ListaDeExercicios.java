package com.example.academia.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.academia.R;
import com.example.academia.model.ListaTreino;
import com.example.academia.model.PlanoDeTreino;

import java.util.ArrayList;

public class ListaDeExercicios extends ArrayAdapter<ListaTreino> {
    private Context context;
    private ArrayList<ListaTreino> lista;

    public ListaDeExercicios(Context context, ArrayList<ListaTreino> lista){
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListaTreino listaTreino = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.custon_list_exercicios, null);

        TextView exercicio = convertView.findViewById(R.id.exercicioTextView);
        exercicio.setText(lista.get(position).getExercicio());

        TextView sequencia = convertView.findViewById(R.id.sequenciaTextView);
        sequencia.setText(lista.get(position).getSequencia());

        TextView repeticao = convertView.findViewById(R.id.repeticaoTextView);
        repeticao.setText(lista.get(position).getRepeticao());

        return convertView;
    }
}
