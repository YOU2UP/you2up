package school.sptech.loginormyou2up.dto.treino;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoJson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreinoDtoResposta {
    private Integer id;

    @Schema(description = "Período do treino", example = "Manhã")
    private String periodo;


    @Schema(description = "Data e hora de início do treino")
    private LocalDateTime inicioTreino;

    @Schema(description = "Lista de usuários associados ao treino")
    private List<UsuarioDtoJson> usuarios;

    public TreinoDtoResposta(Integer id, String periodo, LocalDateTime inicioTreino, List<UsuarioDtoJson> usuarios) {
        this.id = id;
        this.periodo = periodo;
        this.inicioTreino = inicioTreino;
        this.usuarios = usuarios;
    }

    public TreinoDtoResposta() {
    }

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

    public LocalDateTime getInicioTreino() {
        return inicioTreino;
    }

    public void setInicioTreino(LocalDateTime inicioTreino) {
        this.inicioTreino = inicioTreino;
    }

    public List<UsuarioDtoJson> getUsuarios() {
        List<UsuarioDtoJson> listaRetorno = new ArrayList<>();

        for (UsuarioDtoJson u: usuarios) {
            listaRetorno.add(u);
        }

        return listaRetorno;
    }

    public void setUsuarios(List<UsuarioDtoJson> usuarios) {
        this.usuarios = usuarios;
    }
}
