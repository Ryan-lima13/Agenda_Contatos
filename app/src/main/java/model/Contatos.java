package model;

import com.google.firebase.database.DatabaseReference;

import helpe.ConfiguracaoFirebase;

public class Contatos {
    private  String idContatos;
    private  String nome;
    private  String email;
    private  String telefone;

    public Contatos() {
        DatabaseReference contatosRef = ConfiguracaoFirebase.getDatabaseReference()
                .child("contatos");
        setIdContatos(contatosRef.push().getKey());
    }

    public  void salvar(){
        String idUsuario = ConfiguracaoFirebase.getIdUsuario();

        DatabaseReference contatosRef = ConfiguracaoFirebase.getDatabaseReference()
                .child("contatos");
        contatosRef.child(idUsuario)
                .child(getIdContatos())
                .setValue(this);
    }
    public  void revomer(){
        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference contatosRef = ConfiguracaoFirebase.getDatabaseReference()
                .child("contatos")
                .child(idUsuario)
                .child(getIdContatos());
        contatosRef.removeValue();

    }

    public String getIdContatos() {
        return idContatos;
    }

    public void setIdContatos(String idContatos) {
        this.idContatos = idContatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
