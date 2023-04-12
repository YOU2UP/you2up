package sptech.school.apitreinoyou2up.models.observer;

import sptech.school.apitreinoyou2up.domain.Notificacao;

public interface Observado {
    void notificarUsuario(Notificacao novaNotificacao);
}
