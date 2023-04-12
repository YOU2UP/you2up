package sptech.school.apitreinoyou2up.controller;

import sptech.school.apitreinoyou2up.domain.Notificacao;
import sptech.school.apitreinoyou2up.models.observer.Observador;

public class NotificacaoController implements Observador {

    public NotificacaoController() {
    }

    @Override
    public void exibirNotificacao(Notificacao notificacao) {
        System.out.println(notificacao);
    }

}
