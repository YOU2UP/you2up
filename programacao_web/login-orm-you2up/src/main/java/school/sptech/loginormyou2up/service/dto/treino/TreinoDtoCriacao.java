package school.sptech.loginormyou2up.service.dto.treino;

import school.sptech.loginormyou2up.domain.Usuario;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class TreinoDtoCriacao {

    @NotBlank
    private String periodo;

    @FutureOrPresent
    private LocalDateTime inicioTreino;

    private List<Usuario> usuarios;


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
