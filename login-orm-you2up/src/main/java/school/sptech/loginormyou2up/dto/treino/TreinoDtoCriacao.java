package school.sptech.loginormyou2up.dto.treino;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class TreinoDtoCriacao {

    @NotBlank
    @Schema(description = "Período do treino", example = "Manhã")
    private String periodo;

    @FutureOrPresent
    @Schema(description = "Data e hora de início do treino", example = "2023-04-24T10:00:00")
    private LocalDateTime inicioTreino;

    @Schema(description = "Lista de usuários associados ao treino")
    private List<Usuario> usuarios;

    public TreinoDtoCriacao(String periodo, LocalDateTime inicioTreino, List<Usuario> usuarios) {
        this.periodo = periodo;
        this.inicioTreino = inicioTreino;
        this.usuarios = usuarios;
    }

    public TreinoDtoCriacao() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDateTime getInicioTreino() {
        return inicioTreino;
    }

    public void setInicioTreino(LocalDateTime inicioTreino) {
        this.inicioTreino = inicioTreino;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
