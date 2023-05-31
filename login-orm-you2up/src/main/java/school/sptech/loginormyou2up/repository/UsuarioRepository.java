package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
