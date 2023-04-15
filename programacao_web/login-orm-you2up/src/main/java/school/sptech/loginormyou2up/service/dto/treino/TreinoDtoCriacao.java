package school.sptech.loginormyou2up.service.dto.treino;

import school.sptech.loginormyou2up.domain.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TreinoDtoCriacao {
    private Integer id;

    private String periodo;

    private String preferencia;

    private LocalDateTime inicioTreino;

    private List<Usuario> usuarios;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
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
