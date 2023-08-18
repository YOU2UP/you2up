package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.dto.notificacao.NotificacaoDtoResposta;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDtoResposta {

    @Schema(description = "Id do usuário", example = "1")
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

    @Schema(description = "Meta de treinos do usuário", example = "5")
    private int metaTreinos;

    @Schema(description = "Lista de notificações do usuário")
    private List<NotificacaoDtoResposta> notificacoes;

    @Schema(description = "Lista de treinos do usuário")
    private List<TreinoDtoJsonUsuario> treinos;

    @Schema(description = "Local de treino do usuário")
    private LocalTreinoUsuario localTreino;

    @Schema(description = "Lista de matches do usuário")
    private List<MatchDtoResposta> matches;

    public UsuarioDtoResposta(Integer id, String nome, String email, LocalDate dataNascimento, Double notaMedia, String estagio, int metaTreinos, List<NotificacaoDtoResposta> notificacoes, List<TreinoDtoJsonUsuario> treinos, LocalTreinoUsuario localTreino, List<MatchDtoResposta> matches) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.notaMedia = notaMedia;
        this.estagio = estagio;
        this.metaTreinos = metaTreinos;
        this.notificacoes = notificacoes;
        this.treinos = treinos;
        this.localTreino = localTreino;
        this.matches = matches;
    }

    public UsuarioDtoResposta() {
    }

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

    public List<MatchDtoResposta> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDtoResposta> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "UsuarioDtoResposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", notaMedia=" + notaMedia +
                ", estagio='" + estagio + '\'' +
                ", metaTreinos=" + metaTreinos +
                ", notificacoes=" + notificacoes +
                ", treinos=" + treinos +
                ", localTreino=" + localTreino +
                ", matches=" + matches +
                '}';
    }

}
