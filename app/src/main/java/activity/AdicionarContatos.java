package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityAdicionarContatosBinding;

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
                }
            }
        });
    }
}