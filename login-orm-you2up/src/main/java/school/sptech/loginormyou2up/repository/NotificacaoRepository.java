package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.domain.notificacao.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {
}
