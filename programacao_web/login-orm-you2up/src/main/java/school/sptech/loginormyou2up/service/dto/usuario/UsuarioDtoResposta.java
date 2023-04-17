package school.sptech.loginormyou2up.service.dto.usuario;

import school.sptech.loginormyou2up.domain.Notificacao;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.service.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoJsonUsuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDtoResposta {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Treino treino;
    private List<Notificacao> notificacoes;


    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public TreinoDtoJsonUsuario getTreino() {
        return TreinoMapper.convertToTreinoDtoJsonUsuario(treino);
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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


}
