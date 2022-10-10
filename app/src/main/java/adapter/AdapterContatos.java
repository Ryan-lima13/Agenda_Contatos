package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rlds.agendacontatos.R;

import java.util.List;

import model.Contatos;

public class AdapterContatos extends RecyclerView.Adapter<AdapterContatos.MyViewHolder> {
    private List<Contatos>listaContatos;
    private Context context;

    public AdapterContatos(List<Contatos> listaContatos, Context context) {
        this.listaContatos = listaContatos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_contatos,parent,false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contatos contatos = listaContatos.get(position);
        holder.txtNome.setText(contatos.getNome());
        holder.txtEmail.setText(contatos.getEmail());
        holder.txtTelefone.setText(contatos.getTelefone());

    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNome, txtEmail, txtTelefone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.email_contato);
            txtNome = itemView.findViewById(R.id.nome_contato);
            txtTelefone = itemView.findViewById(R.id.telefone_contato);
        }
    }
}
