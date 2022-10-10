package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.rlds.agendacontatos.R;
import com.rlds.agendacontatos.databinding.ActivityListaContatosBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.AdapterContatos;
import helpe.ConfiguracaoFirebase;
import helpe.RecyclerItemClickListener;
import model.Contatos;

public class ListaContatos extends AppCompatActivity {
    private ActivityListaContatosBinding binding;
    private List<Contatos> listaContatos = new ArrayList<>();
    private DatabaseReference contatosUarioRef;
    private RecyclerView recyclerViewContatos;
    private AdapterContatos adapterContatos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaContatosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contatosUarioRef = ConfiguracaoFirebase.getDatabaseReference().child("contatos").child(ConfiguracaoFirebase.getIdUsuario());
        recyclerViewContatos = findViewById(R.id.recyclerViewContatos);
        recyclerViewContatos.setLayoutManager( new LinearLayoutManager(this));
        recyclerViewContatos.setHasFixedSize(true);
        adapterContatos = new AdapterContatos(listaContatos,this);
        recyclerViewContatos.setAdapter(adapterContatos);
        recuperarContatos();
        // adicionar evento clique recyclerView
        recyclerViewContatos.addOnItemTouchListener(
                 new RecyclerItemClickListener(
                         this,
                         recyclerViewContatos,
                         new RecyclerItemClickListener.OnItemClickListener() {
                             @Override
                             public void onItemClick(View view, int position) {

                             }

                             @Override
                             public void onLongItemClick(View view, int position) {
                                 Contatos contatosSelecionado = listaContatos.get(position);
                                 contatosSelecionado.revomer();
                                 adapterContatos.notifyDataSetChanged();


                             }

                             @Override
                             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                             }
                         }
                 )
        );



    }

    public  void recuperarContatos(){
        contatosUarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaContatos.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    listaContatos.add(ds.getValue(Contatos.class));
                }
                Collections.reverse(listaContatos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}