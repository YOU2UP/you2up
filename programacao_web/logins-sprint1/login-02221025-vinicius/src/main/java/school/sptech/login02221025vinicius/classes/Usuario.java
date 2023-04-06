package school.sptech.login02221025vinicius.classes;

public class Usuario {
    private String usuario;
    private String senha;
    private String nome;
    private boolean isAutenticado;


    public Usuario() {

    }

    public Usuario(String usuario, String senha, String nome) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

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
