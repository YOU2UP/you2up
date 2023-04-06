package sptech.school.login02221047matheus;

public class UsuarioDTO {

    private String usuario;
    private String nome;
    private boolean autenticado;

    public UsuarioDTO(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.nome = usuario.getNome();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
