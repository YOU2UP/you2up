package sptech.school.apitreinoyou2up.domain;

import sptech.school.apitreinoyou2up.models.observer.Observado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notificacao implements Observado {

    private String userRecebe;
    private String userEnvia;
    private LocalDateTime dataHora;
    private String assunto;
    private String texto;

    public Notificacao(String userRecebe, String userEnvia, LocalDateTime dataHora, String assunto, String texto) {
        this.userRecebe = userRecebe;
        this.userEnvia = userEnvia;
        this.dataHora = dataHora;
        this.assunto = assunto;
        this.texto = texto;
    }

    public String getUserRecebe() {
        return userRecebe;
    }

    public void setUserRecebe(String userRecebe) {
        this.userRecebe = userRecebe;
    }

    public String getUserEnvia() {
        return userEnvia;
    }

    public void setUserEnvia(String userEnvia) {
        this.userEnvia = userEnvia;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    private List<Notificacao> notificacoes = new ArrayList<>();

    @Override
    public void notificarUsuario(Notificacao novaNotificacao) {
        notificacoes.add(novaNotificacao);
    }

    @Override
    public String toString() {
        return "Notificacao {" +
                "\nuserRecebe='" + userRecebe + '\'' +
                ", \nuserEnvia='" + userEnvia + '\'' +
                ", \ndataHora=" + dataHora +
                ", \nassunto='" + assunto + '\'' +
                ", \ntexto='" + texto +
                '}';
    }
}
