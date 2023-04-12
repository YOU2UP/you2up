package sptech.school.apitreinoyou2up.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @Email
    private String usuario;
    private String senha;
    @Size(min = 4)
    private String nome;
    private boolean isAutenticado;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(boolean autenticado) {
        isAutenticado = autenticado;
    }

}
