package school.sptech.loginormyou2up.service.observer;

import school.sptech.loginormyou2up.domain.Notificacao;
import school.sptech.loginormyou2up.domain.Treino;

public interface Observador {
    void notificarTreino(Treino treino);

}
