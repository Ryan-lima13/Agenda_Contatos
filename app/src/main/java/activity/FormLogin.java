package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityFormLoginBinding;

public class FormLogin extends AppCompatActivity {
    private ActivityFormLoginBinding binding;
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
                }
            }
        });
    }
}