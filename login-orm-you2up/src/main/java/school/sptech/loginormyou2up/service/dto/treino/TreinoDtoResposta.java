package school.sptech.loginormyou2up.service.dto.treino;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.service.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoJson;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoResposta;

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