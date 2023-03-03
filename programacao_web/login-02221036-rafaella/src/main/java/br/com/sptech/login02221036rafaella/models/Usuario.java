package br.com.sptech.login02221036rafaella.models;

public class Usuario {

    private String usuario;
    private String nome;
    private String senha;
    private Boolean isAutenticado;

    public Usuario(String usuario, String nome, String senha) {
        this.usuario = usuario;
        this.nome = nome;
        this.senha = senha;
        this.isAutenticado = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        isAutenticado = autenticado;
    }
}
