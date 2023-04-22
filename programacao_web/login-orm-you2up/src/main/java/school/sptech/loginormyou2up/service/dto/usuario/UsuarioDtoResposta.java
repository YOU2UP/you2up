package school.sptech.loginormyou2up.service.dto.usuario;

import school.sptech.loginormyou2up.domain.Notificacao;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.service.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.service.dto.notificacao.NotificacaoDtoResposta;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoJsonUsuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDtoResposta {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Double notaMedia;
    private String estagio;
    private String sexo;
    private int metaTreinos;
    private List<NotificacaoDtoResposta> notificacoes;
    private List<TreinoDtoJsonUsuario> treinos;


    public List<TreinoDtoJsonUsuario> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<TreinoDtoJsonUsuario> treinos) {
        this.treinos = treinos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getMetaTreinos() {
        return metaTreinos;
    }

    public void setMetaTreinos(int metaTreinos) {
        this.metaTreinos = metaTreinos;
    }

    public List<NotificacaoDtoResposta> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<NotificacaoDtoResposta> notificacoes) {
        this.notificacoes = notificacoes;
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

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }
}
