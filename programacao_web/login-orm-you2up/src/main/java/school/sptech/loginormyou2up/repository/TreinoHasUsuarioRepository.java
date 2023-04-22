package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.domain.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.TreinoHasUsuarioId;

public interface TreinoHasUsuarioRepository extends JpaRepository<TreinoHasUsuario, TreinoHasUsuarioId> {
}
