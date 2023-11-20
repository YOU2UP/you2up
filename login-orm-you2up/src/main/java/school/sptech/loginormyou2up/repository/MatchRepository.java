package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.match.TbMatch;

import java.util.List;

public interface MatchRepository extends JpaRepository<TbMatch, Integer> {

    @Query("SELECT m FROM TbMatch m WHERE m.usuario1.id = :id OR m.usuario2.id = :id")
    List<TbMatch> getAllMatchsByUsuarioId(Integer id);

    @Query("SELECT m FROM TbMatch m WHERE (m.usuario1.id = :id1 OR m.usuario2.id = :id1) AND (m.usuario1.id = :id2 OR m.usuario2.id = :id2)")
    List<TbMatch> getMatchEntreUsuarios(Integer id1, Integer id2);

    @Query("SELECT COUNT(m) FROM TbMatch m WHERE (m.usuario1.id = :id1 OR m.usuario2.id = :id1) AND (m.usuario1.id = :id2 OR m.usuario2.id = :id2)")
    int countMatches(int id1, int id2);
}
