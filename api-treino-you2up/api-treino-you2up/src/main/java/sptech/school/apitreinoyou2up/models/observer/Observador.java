package sptech.school.apitreinoyou2up.models.observer;

import sptech.school.apitreinoyou2up.domain.Notificacao;

public interface Observador {
    void exibirNotificacao(Notificacao notificacao);
}
