package sptech.school.apitreinoyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.apitreinoyou2up.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
