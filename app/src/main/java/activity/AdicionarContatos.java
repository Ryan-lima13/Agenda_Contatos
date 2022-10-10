package activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityAdicionarContatosBinding;
import com.santalu.maskara.widget.MaskEditText;

import model.Contatos;

public class AdicionarContatos extends AppCompatActivity {
    private ActivityAdicionarContatosBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarContatosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = binding.contatoNome.getText().toString();
                String email = binding.contatoEmail.getText().toString();
                String telefone = binding.editTelefone.getText().toString();

                if (nome.isEmpty()|| email.isEmpty() || telefone.isEmpty()){
                    binding.txtErro.setText("Preecha todos os campos!");
                    binding.txtErro.setTextColor(getColor(R.color.red));

                }else {
                    binding.txtErro.setText("");
                    // salvar contatos
                    Contatos contatos = new Contatos();
                    contatos.setNome(nome);
                    contatos.setEmail(email);
                    contatos.setTelefone(telefone);
                    contatos.salvar();
                    Toast.makeText(AdicionarContatos.this, "Contato salvo com sucesso!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdicionarContatos.this, TelaPrincipal.class);
                    startActivity(intent);

                }
            }
        });
    }



}