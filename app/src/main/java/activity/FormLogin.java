package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityFormLoginBinding;

import helpe.ConfiguracaoFirebase;

public class FormLogin extends AppCompatActivity {
    private ActivityFormLoginBinding binding;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        // abrir tela cadastro
        binding.txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        binding.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtEmail.getText().toString();
                String senha = binding.txtSenha.getText().toString();
                if(email.isEmpty() || senha.isEmpty()){
                    binding.txtErro.setText("Preencha todos os campos");
                    binding.txtErro.setTextColor(getColor(R.color.red));

                }else {
                    binding.txtErro.setText("");
                    // fazer login
                    logarUsuario();
                }
            }
        });
    }
    private  void logarUsuario(){
        String email = binding.txtEmail.getText().toString();
        String senha = binding.txtSenha.getText().toString();
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    abrirTelaPrincipal();
                }else {
                    Toast.makeText(FormLogin.this, " não entrou",Toast.LENGTH_SHORT).show();


                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        usuarioLogado();
    }

    private  void abrirTelaPrincipal(){
        Intent intent = new Intent(FormLogin.this, TelaPrincipal.class);
        startActivity(intent);
        finish();
    }
    private void usuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        if( autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }
}