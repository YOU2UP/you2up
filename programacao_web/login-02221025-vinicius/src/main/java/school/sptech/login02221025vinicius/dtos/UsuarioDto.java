package school.sptech.login02221025vinicius.dtos;

import school.sptech.login02221025vinicius.classes.Usuario;

public class UsuarioDto {
    private String usuario;
    private String nome;

    private boolean isAutenticado;

    public UsuarioDto(Usuario user) {

        this.usuario = user.getUsuario();
        this.nome = user.getNome();
        this.isAutenticado = user.isAutenticado();
    }


    public String getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAutenticado() {
        return isAutenticado;
    }
}
