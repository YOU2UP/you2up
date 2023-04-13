package school.sptech.loginormyou2up.models.dto;

import school.sptech.loginormyou2up.domain.Usuario;

import java.time.LocalDate;

public class UsuarioDto {
    private String nome;
    private String usuario;

    public UsuarioDto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
