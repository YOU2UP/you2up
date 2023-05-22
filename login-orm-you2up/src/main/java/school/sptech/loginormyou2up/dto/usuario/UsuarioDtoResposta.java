package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.LocalTreinoUsuario;
import school.sptech.loginormyou2up.dto.notificacao.NotificacaoDtoResposta;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDtoResposta {

    private Integer id;

    @Schema(description = "Nome do usuário", example = "Natalia")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private String email;

    @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Nota média do usuário", example = "7.5")
    private Double notaMedia;

    @Schema(description = "Estágio do usuário (iniciante, intermediário, avançado)", example = "iniciante")
    private String estagio;

    @Schema(description = "Sexo do usuário", example = "M")
    private String sexo;

    @Schema(description = "Meta de treinos do usuário", example = "5")
    private int metaTreinos;

    @Schema(description = "Lista de notificações do usuário")
    private List<NotificacaoDtoResposta> notificacoes;

    @Schema(description = "Lista de treinos do usuário")
    private List<TreinoDtoJsonUsuario> treinos;

    private LocalTreinoUsuario localTreino;


    public LocalTreinoUsuario getLocalTreino() {
        return localTreino;
    }

    public void setLocalTreino(LocalTreinoUsuario localTreino) {
        this.localTreino = localTreino;
    }

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