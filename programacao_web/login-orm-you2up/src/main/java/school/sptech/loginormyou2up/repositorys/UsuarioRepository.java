package school.sptech.loginormyou2up.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.classes.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
