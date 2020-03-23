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
import com.example.academia.helper.Codification;
import com.example.academia.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private DatabaseReference dados;
    private Button entrar;
    private TextView login;
    private TextView senha;
    private Usuario user = new Usuario();
    private FirebaseAuth logar;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginEditText);
        senha = findViewById(R.id.senhaEditText);
        entrar = findViewById(R.id.entrarButton);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 user.setCpf( login.getText().toString());
                 user.setSenha( senha.getText().toString());

                dados = ConfiguracaoFirabase.getFirebase().
                        child("usuario").
                        child(Codification.codificacaoData(user.getCpf()));
                 validarLoginCPF();
            }
        });
    }

    private void validarLoginCPF(){
        dados.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                        Usuario dataFirebase = dataSnapshot.getValue(Usuario.class);
                        if (dataFirebase.getSenha().equals(user.getSenha())){
                            Log.i("Tipo",dataFirebase.getTipo());
                            if (dataFirebase.getTipo().equals("aluno")){
                                Preferencias preferencias = new Preferencias(MainActivity.this);
                                String idUserLogado = Codification.codificacaoData(user.getCpf());
                                preferencias.salvarDados(idUserLogado);
                                abrirTelaPrincipal();
                            }else {
                                abrirTelaPrincipalProfessor();
                            }
                            Toast toast = Toast.makeText(MainActivity.this, "Entrou", Toast.LENGTH_SHORT);
                            toast.show();

                        }else{
                            Toast toast = Toast.makeText(MainActivity.this, "Senha Incorreta", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "Usuário não cadastrado", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void validarLogin(){
        logar = ConfiguracaoFirabase.getFirebaseAutenticacao();
        logar.signInWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Preferencias preferencias = new Preferencias(MainActivity.this);
                    String idUserLogado = Codification.codificacaoData(user.getEmail());
                    preferencias.salvarDados(idUserLogado);
                    abrirTelaPrincipal();
                    Toast toast = Toast.makeText(MainActivity.this, "Sucesso ao Logar", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(MainActivity.this, "Não foi possível logar", Toast.LENGTH_SHORT);
                    toast.show();
                    String msgErro = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthEmailException e){
                        msgErro = "Erro na autenticação do usuário";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(MainActivity.this, Principal.class);
        startActivity(intent);
        finish();
    }

    public void abrirTelaPrincipalProfessor(){
        Intent intent = new Intent(MainActivity.this, ProfessorPrincipal.class);
        startActivity(intent);
        finish();
    }

    public void novoUsuario (View view){
        Intent intent = new Intent(MainActivity.this, CadastroUsuario.class);
        startActivity(intent);
    }


}
