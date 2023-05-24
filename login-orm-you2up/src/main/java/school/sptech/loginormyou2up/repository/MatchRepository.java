package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.match.Match;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("SELECT m FROM Match m WHERE m.usuario1.id = :id OR m.usuario2.id = :id")
    List<Match> getAllMatchsByUsuarioId(Integer id);

    @Query("SELECT m FROM Match m WHERE (m.usuario1.id = :id1 OR m.usuario2.id = :id1) AND (m.usuario1.id = :id2 OR m.usuario2.id = :id2)")
    Optional<Match> getMatchEntreUsuarios(Integer id1, Integer id2);
}
