package school.sptech.loginormyou2up.service.dto.treino;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class TreinoDtoJsonUsuario { // essa classe mostra seu treino sem mostrar a lista de usuarios
    private Integer id;
    @Schema(description = "Período do treino", example = "Manhã")
    private String periodo;

    @Schema(description = "Data e hora do treino")
    private LocalDateTime dataHora;

    @Schema(description = "Quantidade de treinos realizados", example = "10")
    private Integer quantidadeTreinos;

    @Schema(description = "Indica se o treino foi realizado ou não", example = "true")
    private boolean isRealizado;


    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getQuantidadeTreinos() {
        return quantidadeTreinos;
    }

    public void setQuantidadeTreinos(Integer quantidadeTreinos) {
        this.quantidadeTreinos = quantidadeTreinos;
    }

    public boolean isRealizado() {
        return isRealizado;
    }

    public void setRealizado(boolean realizado) {
        isRealizado = realizado;
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

}
