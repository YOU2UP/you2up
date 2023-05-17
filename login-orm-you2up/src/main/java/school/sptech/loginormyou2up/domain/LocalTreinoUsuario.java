package school.sptech.loginormyou2up.domain;

import javax.persistence.*;

@Entity
public class LocalTreinoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocalTreino;
    private String nome;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean isAcademia;
    @OneToOne
    private Usuario usuario;

    public Integer getIdLocalTreino() {
        return idLocalTreino;
    }

    public void setIdLocalTreino(Integer idLocalTreino) {
        this.idLocalTreino = idLocalTreino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isAcademia() {
        return isAcademia;
    }

    public void setAcademia(boolean academia) {
        isAcademia = academia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
