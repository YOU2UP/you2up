package br.com.sptech.login02221036rafaella.dto;

import br.com.sptech.login02221036rafaella.models.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    private String nome;
    private String usuario;
    private Boolean isAutenticado;

    public UsuarioDto() { }

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.usuario = usuario.getUsuario();
        this.isAutenticado = usuario.getAutenticado();
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public Boolean getAutenticado() {
        return isAutenticado;
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarioList) {
        return usuarioList.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
