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
import com.example.academia.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

public class MainActivity extends AppCompatActivity {

    private Button entrar;
    private TextView cpf;
    private TextView senha;
    private Usuario user;
    private FirebaseAuth logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpf = findViewById(R.id.cpfEditText);
        senha = findViewById(R.id.senhaEditText);
        entrar = findViewById(R.id.entrarButton);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 user = new Usuario();
                 user.setEmail( cpf.getText().toString());
                 user.setSenha( senha.getText().toString());
                 validarLogin();
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
                    abrirTelaPrincipal();
                    Toast.makeText(MainActivity.this, "Sucesso ao Logar", Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(MainActivity.this, "Não foi possível logar", Toast.LENGTH_SHORT);
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
    }

    public void novoUsuario (View view){
        Intent intent = new Intent(MainActivity.this, CadastroUsuario.class);
        startActivity(intent);
    }


}
