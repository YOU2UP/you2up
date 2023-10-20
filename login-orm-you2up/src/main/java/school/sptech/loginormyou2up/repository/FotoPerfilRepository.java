package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.loginormyou2up.domain.foto.FotoPerfil;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FotoPerfilRepository extends JpaRepository<FotoPerfil, Integer> {
    public Optional<FotoPerfil> findByUsuarioIdUsuario(int idUsuario);

    public boolean existsByUsuarioIdUsuario(int idUsuario);
}
