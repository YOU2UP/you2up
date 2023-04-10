package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
