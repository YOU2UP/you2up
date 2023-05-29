package school.sptech.loginormyou2up.dto.notificacao;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.Usuario;

public class NotificacaoDtoCriacao {
    @Schema(description = "Conteúdo da notificação")
    private String conteudo;

    @Schema(description = "Usuário associado à notificação")
    private Usuario usuario;

    public NotificacaoDtoCriacao(String conteudo, Usuario usuario) {
        this.conteudo = conteudo;
        this.usuario = usuario;
    }

    public NotificacaoDtoCriacao() {
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
