package school.sptech.loginormyou2up.models.observer;

import school.sptech.loginormyou2up.domain.Treino;

public interface Observado {
    void notificarUsuario(Treino treino);
}
