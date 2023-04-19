package school.sptech.loginormyou2up.service.dto.notificacao;

import school.sptech.loginormyou2up.domain.Usuario;

public class NotificacaoDtoCriacao {
    private String conteudo;
    private Usuario usuario;



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
