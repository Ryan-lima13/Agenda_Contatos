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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityFormCadastroBinding;

import helpe.ConfiguracaoFirebase;

public class FormCadastro extends AppCompatActivity {
    private ActivityFormCadastroBinding binding;
    
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        // fazer cadastro
        binding.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editEmail.getText().toString();
                String senha = binding.editSenha.getText().toString();
                if( email.isEmpty() || senha.isEmpty()){
                    binding.txtErro.setText("Preencha todos os campos!");
                    binding.txtErro.setTextColor(getColor(R.color.red));

                }else {
                    binding.txtErro.setText("");
                    // cadastrar
                    cadastrarusario();

                }
            }
        });
    }
    private  void cadastrarusario(){
        String email = binding.editEmail.getText().toString();
        String senha = binding.editSenha.getText().toString();

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(FormCadastro.this, "usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FormCadastro.this, TelaPrincipal.class);
                    startActivity(intent);
                    finish();

                }else {
                    String erro = "";

                    try {
                        throw  task.getException();

                    }catch (FirebaseAuthWeakPasswordException e ){
                        erro = "Digite senha com mais de 6 caracteres!";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Digite um email válido!";

                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "conta já cadastrada!";

                    }catch (Exception e){
                        erro = "Erro ao fazer login:" + e.getMessage();
                        e.printStackTrace();
                    }

                    binding.txtErro.setText(erro);


                }

            }
        });


    }
}