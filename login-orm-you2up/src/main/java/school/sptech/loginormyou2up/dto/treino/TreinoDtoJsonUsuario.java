package school.sptech.loginormyou2up.dto.treino;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class TreinoDtoJsonUsuario { // essa classe mostra seu treino sem mostrar a lista de usuarios
    @Schema(description = "Id do treino", example = "10")
    private Integer id;
    @Schema(description = "Período do treino", example = "Manhã")
    private String periodo;

    @Schema(description = "Data e hora do treino")
    private LocalDateTime dataHora;

    @Schema(description = "Indica se o treino foi realizado ou não", example = "true")
    private boolean isRealizado;

    public TreinoDtoJsonUsuario(Integer id, String periodo, LocalDateTime dataHora, boolean isRealizado) {
        this.id = id;
        this.periodo = periodo;
        this.dataHora = dataHora;
        this.isRealizado = isRealizado;
    }

    public TreinoDtoJsonUsuario() {
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
