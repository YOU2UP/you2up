package school.sptech.loginormyou2up.service.dto.notificacao;

import java.time.LocalDateTime;

public class NotificacaoDtoResposta {

    private Integer id;

    private String conteudo;

    private LocalDateTime dataHora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
