package school.sptech.loginormyou2up.domain;

import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.loginormyou2up.service.notificacao.NotificacaoService;
import school.sptech.loginormyou2up.service.observer.Observador;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Usuario implements Observador {
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

    @ManyToOne
    private Treino treino;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacoes;


    @Override
    public void notificarTreino(Treino treino) {
        Notificacao notificacao = new Notificacao();
        notificacao.setConteudo("Seu treino foi agendado com sucesso para o dia " + treino.getInicioTreino());
        notificacao.setDataHora(LocalDateTime.now());

        notificacoes.add(notificacao);
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

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
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
}
