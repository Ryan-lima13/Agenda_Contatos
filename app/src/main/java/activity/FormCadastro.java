package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import model.Usuario;

public class FormCadastro extends AppCompatActivity {
    private ActivityFormCadastroBinding binding;
    private Usuario usuario;
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
                String nome = binding.editNome.getText().toString();
                String email = binding.editEmail.getText().toString();
                String senha = binding.editSenha.getText().toString();
                if( nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    binding.txtErro.setText("Preencha todos os campos!");
                    binding.txtErro.setTextColor(getColor(R.color.red));

                }else {
                    binding.txtErro.setText("");
                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    // cadastrar
                    cadastrarusario();

                }
            }
        });
    }
    private  void cadastrarusario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FormCadastro.this, "usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

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