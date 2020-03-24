package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.R;
import com.example.academia.config.ConfiguracaoFirabase;
import com.example.academia.helper.Preferencias;
import com.example.academia.model.FichaDoAluno;
import com.example.academia.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MeusDados extends AppCompatActivity {

    private Button voltar;
    private DatabaseReference dados;
    private TextView nome;
    private TextView dtNascimento;
    private TextView idade;
    private TextView telefone;
    private TextView email;
    private TextView cpf;
    private Usuario userData = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        voltar = findViewById(R.id.btnVoltar);
        nome = findViewById(R.id.nomeTextView);
        dtNascimento = findViewById(R.id.dtNascTextView);
        idade = findViewById(R.id.idadeTextView);
        telefone = findViewById(R.id.telTextView);
        email = findViewById(R.id.emailTextView);
        cpf = findViewById(R.id.cpfTextView);

        dados = ConfiguracaoFirabase.getFirebase().
                child("usuario").
                child(usuarioLogado());

        dados.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario user = dataSnapshot.getValue(Usuario.class);
                nome.setText(user.getNome());
                dtNascimento.setText(user.getDataNascimento());
                idade.setText(user.getIdade());
                telefone.setText(user.getTelefone());
                email.setText(user.getEmail());
                cpf.setText(user.getCpf());
                userData.setTipo(user.getTipo());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    public void voltar(){
        if (userData.getTipo().equals("aluno")){
            Intent intent = new Intent(MeusDados.this, Principal.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MeusDados.this, ProfessorPrincipal.class);
            startActivity(intent);
            finish();
        }
    }

    public String usuarioLogado(){
        Preferencias preferencias = new Preferencias(MeusDados.this);
        return preferencias.getIdentificador();
    }
}
