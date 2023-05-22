package school.sptech.loginormyou2up.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private Double notaMedia;
    private String descricao;

    private String estagio; // iniciante, intermediário, avançado

    private String sexo;

    private int metaTreinos;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<TreinoHasUsuario> treinos;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacoes;

    @OneToOne
    private LocalTreinoUsuario localTreino;

    @OneToMany(mappedBy = "avaliado")
    private List<Avaliacao> avaliacoesRecebidas;

    @OneToMany(mappedBy = "avaliador")
    private List<Avaliacao> avaliacoesDadas;


    
    public int getMetaTreinos() {
        return metaTreinos;
    }

    public void setMetaTreinos(int metaTreinos) {
        this.metaTreinos = metaTreinos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<TreinoHasUsuario> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<TreinoHasUsuario> treinos) {
        this.treinos = treinos;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalTreinoUsuario getLocalTreino() {
        return localTreino;
    }

    public void setLocalTreino(LocalTreinoUsuario locaTreino) {
        this.localTreino = locaTreino;
    }
}