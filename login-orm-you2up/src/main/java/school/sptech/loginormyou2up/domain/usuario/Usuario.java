package school.sptech.loginormyou2up.domain.usuario;

import school.sptech.loginormyou2up.domain.foto.Foto;
import school.sptech.loginormyou2up.domain.foto.FotoPerfil;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.avaliacao.Avaliacao;
import school.sptech.loginormyou2up.domain.notificacao.Notificacao;

import javax.persistence.*;
import javax.swing.event.ListDataEvent;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String descricao;
    private String estagio; // iniciante, intermediário, avançado
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

    @OneToMany(mappedBy = "usuario")
    private List<Foto> feedFotos;

    @OneToOne
    private FotoPerfil fotoPerfil;



    public Usuario(Integer id, String nome, String email, String senha, LocalDate dataNascimento, String descricao, String estagio, int metaTreinos, List<TreinoHasUsuario> treinos, List<Notificacao> notificacoes) {
        this.idUsuario = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.descricao = descricao;
        this.estagio = estagio;
        this.metaTreinos = metaTreinos;
        this.treinos = treinos;
        this.notificacoes = notificacoes;
    }

    public Usuario() {
    }

    
    public int getMetaTreinos() {
        return metaTreinos;
    }

    public void setMetaTreinos(int metaTreinos) {
        this.metaTreinos = metaTreinos;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<Foto> getFeedFotos() {
        return feedFotos;
    }

    public void setFeedFotos(List<Foto> feedFotos) {
        this.feedFotos = feedFotos;
    }

    public FotoPerfil getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(FotoPerfil fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

}
