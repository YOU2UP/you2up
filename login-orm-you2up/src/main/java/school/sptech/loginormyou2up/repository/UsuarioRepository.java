package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.estagio = :estagio AND u.localTreino.cidade = :cidade")
    List<Usuario> findByEstagioAndCidade(String estagio, String cidade);

    List<Usuario> findByLocalTreinoUfAndEstagio(String uf, String estagio);

    List<Usuario> findByEstagio(String estagio);

    List<Usuario> findByLocalTreinoUfIgnoreCase(String uf);

    List<Usuario> findByLocalTreinoCidadeIgnoreCase(String cidade);
}
