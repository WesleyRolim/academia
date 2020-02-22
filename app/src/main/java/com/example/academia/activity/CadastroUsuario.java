package com.example.academia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUsuario extends AppCompatActivity {

    private TextView nome;
    private TextView cpf;
    private TextView email;
    private TextView telefone;
    private TextView objetivo;
    private TextView criarSenha;
    private TextView confimarSenha;
    private TextView idade;
    private TextView dataNascimento;

    private Button voltar;
    private Button cadastrar;

    private Usuario user;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.nomeEditText);
        cpf = findViewById(R.id.cpfEditText);
        email = findViewById(R.id.emailEditText);
        telefone = findViewById(R.id.telefoneEditText);
        objetivo = findViewById(R.id.objetivoEditText);
        voltar = findViewById(R.id.voltarButton);
        cadastrar = findViewById(R.id.cadastrarButton);
        criarSenha = findViewById(R.id.senhaEditText);
        confimarSenha = findViewById(R.id.confirmaSenhaEditText);
        idade = findViewById(R.id.idadeEditText);
        dataNascimento = findViewById(R.id.dataNascimentoEditText);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    user = new Usuario();
                    user.setEmail( email.getText().toString() );
                    user.setSenha( criarSenha.getText().toString());
                    user.setNome( nome.getText().toString() );
                    user.setCpf( cpf.getText().toString() );
                    user.setEmail( email.getText().toString() );
                    user.setTelefone(telefone.getText().toString() );
                    user.setObjetivo( objetivo.getText().toString() );
                    user.setDataNascimento( dataNascimento.getText().toString() );
                    user.setIdade( idade.getText().toString() );
                    cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirabase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast cadastrado = Toast.makeText(getApplicationContext(), "Usuário cadastrado", Toast.LENGTH_SHORT);
                    cadastrado.show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    user.setId(usuarioFirebase.getUid());
                    user.salvar();
                    autenticacao.signOut();
                    finish();
                }else {
                    String msgErro = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        msgErro = "Necessário melhorar sua senha";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        msgErro = "O email digitado é inválido, necessário dogitar novamente";
                    }catch (FirebaseAuthUserCollisionException e){
                        msgErro = "Usuário já cadastrado";
                    }catch (Exception e){
                        msgErro = "Erro não identificado";
                    }
                    Toast naoCadastrado = Toast.makeText(getApplicationContext(), "Usuário não cadastrado", Toast.LENGTH_SHORT);
                    naoCadastrado.show();
                }
            }
        });
    }
}
