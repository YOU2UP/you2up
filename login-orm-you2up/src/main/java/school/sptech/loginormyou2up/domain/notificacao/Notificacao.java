package school.sptech.loginormyou2up.domain.notificacao;

import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacao;

    private String conteudo;

    private LocalDateTime dataHora;

    @ManyToOne
    private Usuario usuario;

    public Notificacao(Integer id, String conteudo, LocalDateTime dataHora, Usuario usuario) {
        this.idNotificacao = id;
        this.conteudo = conteudo;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    public Notificacao() {
    }

    public Integer getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
